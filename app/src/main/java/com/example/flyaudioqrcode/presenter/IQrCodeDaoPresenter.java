package com.example.flyaudioqrcode.presenter;

import com.example.flyaudioqrcode.base.IBasePresenter;
import com.example.flyaudioqrcode.model.domain.dbdata.User;
import com.example.flyaudioqrcode.view.IQrCodeDBCallback;

public interface IQrCodeDaoPresenter extends IBasePresenter<IQrCodeDBCallback> {

    //查看用户是否有登录
    //还是在登录中，直接使用他的数据

    //没有登录过查询并插入数据
    //登出了则也是重新获取
    void checkUserStates();

    //退出登录
    void checkOutUser(User user);

    //修改用户的登录状态
    void updateUserState(User user);

    //新增用户
    void insertUser(User user);

    //检查根据id检查用户是否存在
    // 存在就用修改数据
    //不存在就新增数据
    void chekUserIdAndUpdata(User user);

}
