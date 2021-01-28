package com.example.flyaudioqrcode.view;

import com.example.flyaudioqrcode.model.domain.dbdata.User;

public interface IQrCodeDBCallback {

    //查找完登录状态回来
    //1、有，直接使用User层的数据
    //2、没有,user里面是空的，需要重新获取
    void checkUserStatus(User user);

}
