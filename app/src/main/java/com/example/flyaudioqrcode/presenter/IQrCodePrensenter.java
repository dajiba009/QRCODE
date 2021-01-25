package com.example.flyaudioqrcode.presenter;

import com.example.flyaudioqrcode.base.IBasePresenter;
import com.example.flyaudioqrcode.view.IQrCodeCallback;

public interface IQrCodePrensenter extends IBasePresenter<IQrCodeCallback> {
    /**
     * 获取tag 和 UUID
     */
    void getTagAndUid();

    /**
     * 验证扫码情况
     * 1、没扫，过期->重新获取
     * 2、扫了，没动->不断向服务器获取点击信息
     * 3、扫了，登录->获取用户信息
     * 4、扫了，没登录->重新获取验证码信息
     * @param uuid
     */
    void checkServer(String uuid);

    /**
     * 获取用户的信息
     * @param userIduserId
     */
    void getUserInfo(String userIduserId);

    /**
     * 获取用户头像
     * @param avatar
     */
    void getUserHeadImage(String avatar);

}
