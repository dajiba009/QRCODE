package com.example.flyaudioqrcode.presenter.imp;

import android.util.Log;

import com.example.flyaudioqrcode.model.dao.UserDao;
import com.example.flyaudioqrcode.model.domain.dbdata.User;
import com.example.flyaudioqrcode.presenter.IQrCodeDaoPresenter;
import com.example.flyaudioqrcode.view.IQrCodeDBCallback;

import org.litepal.crud.DataSupport;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class QrCodeDaoPresenter implements IQrCodeDaoPresenter {

    private IQrCodeDBCallback callback = null;
    private UserDao mUserDao;

    public QrCodeDaoPresenter(){
        mUserDao =new UserDao();
    }

    @Override
    public void checkUserStates() {

        Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<User> emitter) throws Throwable {
                User userByLogin = mUserDao.selectUserByLogin();
                if(userByLogin == null){
                    emitter.onNext(new User());
                }else {
                    emitter.onNext(userByLogin);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<User>() {
            @Override
            public void accept(User user) throws Throwable {
                callback.checkUserStatus(user);
            }
        });


    }

    @Override
    public void checkOutUser(User user) {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
                mUserDao.updataUserStatus(user);
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    @Override
    public void updateUserState(User user) {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
                mUserDao.updataUserStatus(user);
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    @Override
    public void insertUser(User user) {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
                mUserDao.addUser(user);
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    @Override
    public void chekUserIdAndUpdata(User user) {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
                //先搜索用户先
                User userById = mUserDao.selectUserById(user);
                if(userById != null){
                    //有则更新用户的信息
                    mUserDao.updataUserInfo(user);
                }else {
                    //没有则把用户信息添加进去
                    mUserDao.addUser(user);
                }
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    @Override
    public void registerViewCallback(IQrCodeDBCallback callback) {
        this.callback = callback;
    }

    @Override
    public void unregisterViewCallback(IQrCodeDBCallback callback) {
        this.callback = null;
    }
}
