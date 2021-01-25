package com.example.flyaudioqrcode.presenter.imp;

import android.text.TextUtils;

import com.example.flyaudioqrcode.model.API;
import com.example.flyaudioqrcode.model.domain.QrCodeInfo;
import com.example.flyaudioqrcode.model.domain.ServiceInfo;
import com.example.flyaudioqrcode.model.domain.UserInfo;
import com.example.flyaudioqrcode.presenter.IQrCodePrensenter;
import com.example.flyaudioqrcode.utils.LogUtils;
import com.example.flyaudioqrcode.utils.RetrofitManager;
import com.example.flyaudioqrcode.view.IQrCodeCallback;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QrCodePresenterImp implements IQrCodePrensenter {

    private IQrCodeCallback mIQrCodeCallback = null;

    @Override
    public void getTagAndUid() {
        if(mIQrCodeCallback != null){
            mIQrCodeCallback.onLoading();
            API api = getTask();
            Call<QrCodeInfo> task = api.getQrCodeInfo();
            task.enqueue(new Callback<QrCodeInfo>() {
                @Override
                public void onResponse(Call<QrCodeInfo> call, Response<QrCodeInfo> response) {
                    int code = response.code();
                    LogUtils.d(QrCodePresenterImp.class,"code ====> " + code);
                    if(code == HttpURLConnection.HTTP_OK){
                        QrCodeInfo qrCodeInfo = response.body();
                        String tag = qrCodeInfo.getData().getTag();
                        String value = qrCodeInfo.getData().getValue();
                        if(!TextUtils.isEmpty(tag) && !TextUtils.isEmpty(value)){
                            mIQrCodeCallback.onQRCodeSuccess(tag,value);
                        }else {
                            mIQrCodeCallback.onError();
                        }
                    }else {
                        mIQrCodeCallback.onError();
                    }
                }

                @Override
                public void onFailure(Call<QrCodeInfo> call, Throwable t) {
                    mIQrCodeCallback.onError();
                }
            });
        }
    }

    private API getTask() {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        return retrofit.create(API.class);
    }

    @Override
    public void checkServer(String uuid) {
        if(mIQrCodeCallback != null){
            API api = getTask();
            Call<ServiceInfo> task = api.checkServiceInfo(uuid);
            task.enqueue(new Callback<ServiceInfo>() {
                @Override
                public void onResponse(Call<ServiceInfo> call, Response<ServiceInfo> response) {
                    if(response.code() == HttpURLConnection.HTTP_OK){
                        //根据返回的flag来选择要做什么
                        switchFlag(response.body().getData().getFlag(),response.body().getData().getUserId());
                    }else {
                        mIQrCodeCallback.onError();
                    }
                }

                @Override
                public void onFailure(Call<ServiceInfo> call, Throwable t) {
                    mIQrCodeCallback.onError();
                }
            });
        }
    }

    private void switchFlag(String flag,String userId) {
        switch(flag){
            case "10005":
                //过期，重新获取验证码
                LogUtils.d(QrCodePresenterImp.class,"验证码过期了...");
                this.getTagAndUid();
                break;
            case "40001":
                //正在等待扫描
                LogUtils.d(QrCodePresenterImp.class,"等待验证...");
                mIQrCodeCallback.checkDelay();
                break;
            case "40004":
                //用户扫描了，还没有点击
                LogUtils.d(QrCodePresenterImp.class,"扫描了，等待用户确定...");
                mIQrCodeCallback.onScaning();
                break;
            case "40003":
                LogUtils.d(QrCodePresenterImp.class,"用户取消登录...");
                mIQrCodeCallback.onUnLogin();
                break;
            case "40002":
                LogUtils.d(QrCodePresenterImp.class,"用户同意登录...");
                //注意这里userId可能为空
                if(TextUtils.isEmpty(userId)){
                    mIQrCodeCallback.onError();
                }else {
                    mIQrCodeCallback.onLoading();
                    getUserInfo(userId);
                }
                break;
        }
    }

    @Override
    public void getUserInfo(String userIduserId) {
        API task = getTask();
        task.getUserInfo(userIduserId).enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if(response.code() == HttpURLConnection.HTTP_OK){
                    mIQrCodeCallback.onLoginSuccess(response.body());
                }else {
                    mIQrCodeCallback.onError();
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                mIQrCodeCallback.onError();
            }
        });
    }

    @Override
    public void getUserHeadImage(String avatar) {

    }

    @Override
    public void registerViewCallback(IQrCodeCallback callback) {
        this.mIQrCodeCallback = callback;
    }

    @Override
    public void unregisterViewCallback(IQrCodeCallback callback) {
        this.mIQrCodeCallback = null;
    }
}
