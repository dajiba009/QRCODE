package com.example.flyaudioqrcode.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.flyaudioqrcode.R;
import com.example.flyaudioqrcode.base.BaseFragment;
import com.example.flyaudioqrcode.custom.InnerVIew;
import com.example.flyaudioqrcode.model.domain.UserInfo;
import com.example.flyaudioqrcode.presenter.imp.QrCodePresenterImp;
import com.example.flyaudioqrcode.utils.Constants;
import com.example.flyaudioqrcode.utils.LogUtils;
import com.example.flyaudioqrcode.utils.PresenterManager;
import com.example.flyaudioqrcode.utils.QrUtils;
import com.example.flyaudioqrcode.view.IQrCodeCallback;

import androidx.annotation.NonNull;

public class QrCodeFragment extends BaseFragment implements IQrCodeCallback {

    private static State mCurrentState = State.NONE;
    private static final int DELAY_CHECK = 1;

    private FrameLayout mFrameLayout;
    private View mLoadingView;
    private View mErrorView;
    private View mScaningView;
    private View mLoginView;
    private View mQrCodeView;
    private QrCodePresenterImp mQrCodePresenterImp;
    private InnerVIew mInnerVIew;
    private TextView mQrcodeInfoTv;
    private View mErrorFragment;
    private String mTagUid;
    private String uuid;
    private ImageView mHeardIv;
    private TextView mUserNameTv;
    private TextView mLogoutTv;
    private UserInfo usrInfo;


    private enum State{
        NONE,LOADING,ERROR,QRCODE,SCANING,LOGIN,UNLOGIN,
    }

    private Handler mHandle = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case DELAY_CHECK:
                    if(mQrCodePresenterImp != null){
                        mQrCodePresenterImp.checkServer(uuid);
                    }
            }
        }
    };

    @Override
    protected void loadStateView(View rootView, LayoutInflater inflater, ViewGroup container) {
        mFrameLayout = rootView.findViewById(R.id.base_container);
        View fragmentItemView = inflater.inflate(getFragmentItemViewResId(), container, false);
        //NONE的View
        //mFrameLayout.addView(fragmentItemView);
        //loading的View
        mLoadingView = loadLoadingView(inflater, container);
        mFrameLayout.addView(mLoadingView);
        //Fail的View
        mErrorView = loadErrorView(inflater, container);
        mFrameLayout.addView(mErrorView);
        //Scaning的View
        mScaningView = loadScaningView(inflater, container);
        mFrameLayout.addView(mScaningView);
        //Login的View
        mLoginView = loadLoginView(inflater,container);
        mFrameLayout.addView(mLoginView);
        //QrCode的View
        //Unlogin的View
        //这里我共用一个View
        mQrCodeView = loadQRCodeView(inflater,container);
        mFrameLayout.addView(mQrCodeView);
        //setUpState(State.NONE);
    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected void initListener() {
        mErrorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重新获取
                if(mQrCodePresenterImp != null){
                  mQrCodePresenterImp.getTagAndUid();
                }
            }
        });

        mLogoutTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpState(State.QRCODE);
                Toast.makeText(v.getContext(),"您已退出登录",Toast.LENGTH_SHORT).show();
                mQrcodeInfoTv.setText("请使用手机APP\n扫描二维码绑定账号");
                if(mQrCodePresenterImp != null){
                    mQrCodePresenterImp.getTagAndUid();
                }
            }
        });
    }

    @Override
    protected void initPresenter() {
        mQrCodePresenterImp = PresenterManager.outInstance.getQrCodePresenterImp();
        mQrCodePresenterImp.registerViewCallback(this);
    }

    @Override
    protected void loadData() {
        if(mQrCodePresenterImp != null){
            mQrCodePresenterImp.getTagAndUid();
        }
    }

    @Override
    protected int getFragmentItemViewResId() {
        return R.layout.fragment_qrcode_item_view;
    }

    @Override
    protected View loadRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_qrcode,container,false);
    }

    //=======================给FragmentLayout插入各个状态的View=======================================

    private View loadLoadingView(LayoutInflater inflater, ViewGroup container) {
        View loadingView = inflater.inflate(R.layout.fragment_loading_qrcode, container, false);
        return loadingView;
    }

    private View loadErrorView(LayoutInflater inflater, ViewGroup container) {
        View errorView = inflater.inflate(R.layout.fragment_error_qrcode,container,false);
        mErrorFragment = errorView.findViewById(R.id.error_fragment);
        return errorView;
    }

    private View loadScaningView(LayoutInflater inflater, ViewGroup container) {
        View scaningView = inflater.inflate(R.layout.fragment_scaning_qrcode,container,false);
        return scaningView;
    }

    private View loadLoginView(LayoutInflater inflater, ViewGroup container) {
        View loginView = inflater.inflate(R.layout.fragment_login_qrcode,container,false);
        mHeardIv = loginView.findViewById(R.id.loginIconIV);
        mUserNameTv = loginView.findViewById(R.id.loginNameTv);
        mLogoutTv = loginView.findViewById(R.id.logoutTv);
        return loginView;
    }

    private View loadQRCodeView(LayoutInflater inflater, ViewGroup container) {
        View qrCodeView = inflater.inflate(R.layout.fragment_unlogin_qrcode,container,false);
        mInnerVIew = qrCodeView.findViewById(R.id.inner_view);
        mQrcodeInfoTv = qrCodeView.findViewById(R.id.qrcodeInfoTv);
        return qrCodeView;
    }

    //==============================================================================================

    /**
     * 使用这个方法来切换状态
     * @param state
     */
    public void setUpState(State state){
        mCurrentState = state;
        mErrorView.setVisibility(mCurrentState == State.ERROR? View.VISIBLE:View.GONE);
        mLoadingView.setVisibility(mCurrentState == State.LOADING? View.VISIBLE:View.GONE);
        mLoginView.setVisibility(mCurrentState == State.LOGIN? View.VISIBLE:View.GONE);
        mScaningView.setVisibility(mCurrentState == State.SCANING? View.VISIBLE:View.GONE);
        mQrCodeView.setVisibility(mCurrentState == State.UNLOGIN? View.VISIBLE:View.GONE);
        mQrCodeView.setVisibility(mCurrentState == State.QRCODE? View.VISIBLE:View.GONE);
    }

    @Override
    protected void release() {
        //解除presenter的绑定
        if(mQrCodePresenterImp != null){
            mQrCodePresenterImp.unregisterViewCallback(this);
        }
    }

    //=============================Callback=========================================================
    @Override
    public void onError() {
        setUpState(State.ERROR);
    }

    @Override
    public void onLoading() {
        setUpState(State.LOADING);
    }

    @Override
    public void onQRCodeSuccess(String tag, String uuid) {
        setUpState(State.QRCODE);
        mTagUid = "tag:"+tag+";uuid:"+uuid;
        LogUtils.d(QrCodeFragment.class,mTagUid);
        this.uuid = uuid;
//        Log.d("TAG","width ===> " + mInnerVIew.getHeight());
//        Bitmap logoBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.qrcode_2,null);
//        Bitmap contentLogoBitmap = QrUtils.createQRCodeWithLogo(tagUid,
//                UIUtils.dp2px(mInnerVIew.getContext(),100),
//                logoBitmap, 1);
//        mInnerVIew.setImage(logoBitmap,contentLogoBitmap);
        //创建二维码
        mInnerVIew.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mInnerVIew.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap logoBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.qrcode_2,null);
                        Bitmap contentLogoBitmap = QrUtils.createQRCodeWithLogo(mTagUid,
                                mInnerVIew.getHeight()-Constants.ROUNDRECT_RADIUS*2,
                                logoBitmap, 1);
                        Log.d("TAG","width ===> " + mInnerVIew.getHeight());
                        mInnerVIew.setImage(logoBitmap,contentLogoBitmap);
                    }
                },300);
            }
        });
        if(mQrCodePresenterImp != null){
            //3s后重新发起验证
            delayRechecke(3000);
        }
    }

    @Override
    public void onScaning() {
        setUpState(State.SCANING);
        delayRechecke(3000);
    }

    @Override
    public void onUnLogin() {
        setUpState(State.UNLOGIN);
        if(mQrCodePresenterImp != null){
            mQrCodePresenterImp.getTagAndUid();
        }
        //改变提示信息
        mQrcodeInfoTv.setText("您已取消绑定账号\n您可再次扫码绑定账号");
    }

    @Override
    public void onLoginSuccess(UserInfo userInfo) {
        setUpState(State.LOGIN);
        //获取用户信息
        this.usrInfo = userInfo;
        if(userInfo.getAvatar() != null ||!TextUtils.isEmpty(userInfo.getAvatar())){
            Glide.with(QrCodeFragment.this)
                    .load("http://cqfg-carinternet.oss-cn-chengdu.aliyuncs.com/"+usrInfo.getAvatar())
                    .into(mHeardIv);
        }
        mUserNameTv.setText(userInfo.getName());
    }

    @Override
    public void checkDelay() {
        delayRechecke(3000);
    }
    //==============================================================================================

    //使用Handle来实现延迟发送
    private void delayRechecke(int time) {
        Message message = new Message();
        message.what = DELAY_CHECK;
        mHandle.sendMessageDelayed(message, time);
    }
}
