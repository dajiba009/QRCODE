package com.example.flyaudioqrcode.model.dao;

import android.content.ContentValues;

import com.example.flyaudioqrcode.model.domain.dbdata.User;
import com.example.flyaudioqrcode.utils.LogUtils;

import org.litepal.crud.DataSupport;

public class UserDao implements IUserDao {
    private IUserDbCallback callback;

    @Override
    public void setCallback(IUserDbCallback callback) {
        this.callback = callback;
    }

    @Override
    public void addUser(User user) {
        user.save();
        LogUtils.d(UserDao.class,"添加成功");
    }

    @Override
    public User selectUserById(User user) {
        LogUtils.d(UserDao.class,"开始通过Id查找用户");
        User userById = DataSupport.where("userId = ?", user.getUserId()).findFirst(User.class);
        //注意可能会为空
        return userById;
    }

    @Override
    public User selectUserByLogin() {
        LogUtils.d(UserDao.class,"开始通过登录状态查找用户");
        User userBylogin = DataSupport.where("islogin = ?","1").findFirst(User.class);
        return userBylogin;
    }

    @Override
    public void deleteUser() {
        LogUtils.d(UserDao.class,"开始删除");
        DataSupport.deleteAll(User.class);
    }

    @Override
    public void updataUserStatus(User user) {
        LogUtils.d(UserDao.class,"开始修改状态");
        ContentValues values = new ContentValues();
        values.put("islogin",user.isLogin());
        int result = DataSupport.updateAll(User.class,values,"userId = ?",user.getUserId());
    }

    @Override
    public void updataUserInfo(User user) {
        LogUtils.d(UserDao.class,"开始修改用户信息");
        ContentValues values = new ContentValues();
        values.put("name",user.getName());
        values.put("islogin",user.isLogin());
        values.put("headbyte",user.getHeadByte());
        int result = DataSupport.updateAll(User.class,values,"userId = ?",user.getUserId());
    }


}
