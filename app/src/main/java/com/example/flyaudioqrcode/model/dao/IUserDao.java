package com.example.flyaudioqrcode.model.dao;

import com.example.flyaudioqrcode.model.domain.dbdata.User;

public interface IUserDao {

    //绑定回调presenter
    void setCallback(IUserDbCallback callback);

    //增加
    void addUser(User user);

    //搜索登录的用户
    User selectUserByLogin();

    //搜素指定用户
    User selectUserById(User user);

    //删除
    void deleteUser();

    //修改登录状态
    void updataUserStatus(User user);

    //修改用户信息
    void updataUserInfo(User user);

}
