package com.example.flyaudioqrcode.model;

import com.example.flyaudioqrcode.model.domain.QrCodeInfo;
import com.example.flyaudioqrcode.model.domain.ServiceInfo;
import com.example.flyaudioqrcode.model.domain.UserInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API {

    //获取UUID和tag
    @GET("frontuser/frontapi/appUser/getQRCodeValue")
    Call<QrCodeInfo> getQrCodeInfo();

    //验证二维码
    @GET("frontuser/frontapi/appUser/getSweepCodeLoginStatus/{uuid}")
    Call<ServiceInfo> checkServiceInfo(@Path("uuid") String uuid);

    //获取用户信息
    @GET("v1/app/user/operate/user/info/fg/{userId}")
    Call<UserInfo> getUserInfo(@Path("userId") String userId);
}
