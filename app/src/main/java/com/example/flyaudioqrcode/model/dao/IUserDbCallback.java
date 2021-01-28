package com.example.flyaudioqrcode.model.dao;

import com.example.flyaudioqrcode.model.domain.dbdata.User;

public interface IUserDbCallback {

    void findSuccess(User user);

    void updataSuccess();

    void insertSuccess();

    void deleteSuccess();
}
