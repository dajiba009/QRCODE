package com.example.flyaudioqrcode.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.flyaudioqrcode.R;
import com.example.flyaudioqrcode.base.BaseFragment;
import com.example.flyaudioqrcode.fragment.CarSetFragment;
import com.example.flyaudioqrcode.fragment.CommonFragment;
import com.example.flyaudioqrcode.fragment.QrCodeFragment;
import com.example.flyaudioqrcode.fragment.SystemtFragment;
import com.example.flyaudioqrcode.utils.LogUtils;

public class MainActivity extends AppCompatActivity {

    private RadioGroup mNationBar;
    private FrameLayout mFrameLayout;
    private CarSetFragment mCarSetFragment;
    private CommonFragment mCommonFragment;
    private FragmentManager mFm;
    private SystemtFragment mSystemtFragment;
    private QrCodeFragment mQrCodeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = this.getWindow();
            //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            window.setStatusBarColor(0x1F2E43);
        }
        initView();
        initFragment();
        initListener();
    }

    private void initFragment() {
        //加载所有的fragment
        mCarSetFragment = new CarSetFragment();
        mCommonFragment = new CommonFragment();
        mSystemtFragment = new SystemtFragment();
        mQrCodeFragment = new QrCodeFragment();
        mFm = getSupportFragmentManager();
        switchFragment(mQrCodeFragment);
    }

    private void initListener() {
        if(mNationBar != null){
            mNationBar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    LogUtils.d(MainActivity.class,"checkId ---> " + checkedId);
                    if(checkedId == R.id.carSetRb){
                        LogUtils.d(MainActivity.class,"切换到车辆设置界面");
                        switchFragment(mCarSetFragment);
                    }else if(checkedId == R.id.carCommonRb){
                        LogUtils.d(MainActivity.class,"切换到通用界面");
                        switchFragment(mCommonFragment);
                    }else if(checkedId == R.id.carSystemRb){
                        LogUtils.d(MainActivity.class,"切换到系统界面");
                        switchFragment(mSystemtFragment);
                    }else if(checkedId == R.id.carIdentifyRb){
                        LogUtils.d(MainActivity.class,"切换到账号界面");
                        switchFragment(mQrCodeFragment);
                    }
                }
            });
        }
    }

    private void initView() {
        mNationBar = findViewById(R.id.test_navigation_bar);
        mFrameLayout = findViewById(R.id.contentFragment);
    }

    private BaseFragment lastFragment = null;

    private void switchFragment(BaseFragment targetFtragment) {
        if(lastFragment == targetFtragment){
            return;
        }
        FragmentTransaction transaction = mFm.beginTransaction();
        if(!targetFtragment.isAdded()){
            transaction.add(R.id.contentFragment,targetFtragment);
        }else {
            transaction.show(targetFtragment);
        }
        if(lastFragment != null){
            transaction.hide(lastFragment);
        }
        lastFragment = targetFtragment;
        transaction.commit();
    }
}