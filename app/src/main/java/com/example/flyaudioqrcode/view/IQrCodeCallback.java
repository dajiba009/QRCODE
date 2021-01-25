package com.example.flyaudioqrcode.view;

import com.example.flyaudioqrcode.model.domain.UserInfo;

public interface IQrCodeCallback {
    //错误界面返回
    void onError();

    //加载界面
    void onLoading();

    //获取二维码成功界面
    void onQRCodeSuccess(String tag,String uuid);

    //扫描中界面
    void onScaning();

    //取消登录界面
    void onUnLogin();

    //登录成功界面
    void onLoginSuccess(UserInfo userInfo);

    //二维码过期
    void checkDelay();
}
