package com.example.flyaudioqrcode.base;

public interface IBasePresenter<T> {
    /**
     * 注册View的接口
     * @param callback
     */
    void registerViewCallback(T callback);

    /**
     * 取消注册View的接口
     * @param callback
     */
    void unregisterViewCallback(T callback);
}
