package com.example.flyaudioqrcode.model.domain.dbdata;

import org.litepal.crud.DataSupport;

public class User extends DataSupport {
    private int id;
    private String userId;
    private String name;
    private boolean isLogin;
    private byte[] headByte;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public byte[] getHeadByte() {
        return headByte;
    }

    public void setHeadByte(byte[] headByte) {
        this.headByte = headByte;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
