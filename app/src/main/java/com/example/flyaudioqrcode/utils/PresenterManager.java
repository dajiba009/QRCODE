package com.example.flyaudioqrcode.utils;

import com.example.flyaudioqrcode.presenter.imp.QrCodeDaoPresenter;
import com.example.flyaudioqrcode.presenter.imp.QrCodePresenterImp;

public class PresenterManager {
    public static final PresenterManager outInstance = new PresenterManager();

    private final QrCodePresenterImp mQrCodePresenterImp;
    private final QrCodeDaoPresenter mQrCodeDaoPresenter;

    private PresenterManager(){
        mQrCodePresenterImp = new QrCodePresenterImp();
        mQrCodeDaoPresenter = new QrCodeDaoPresenter();
    }

    //所有的presenter在这里管理
    public QrCodePresenterImp getQrCodePresenterImp(){
        return mQrCodePresenterImp;
    }

    public QrCodeDaoPresenter getQrCodeDaoPresenter() {
        return mQrCodeDaoPresenter;
    }
}
