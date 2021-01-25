package com.example.flyaudioqrcode.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.flyaudioqrcode.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = loadRootView(inflater,container);
        //用于给各个Fragmnet初始化各个状态，这里我们只写用户登录界面就不抽出来写了，在扫描界面自己实现
        loadStateView(rootView,inflater,container);
        initView(rootView);
        initListener();
        initPresenter();
        //用于给各个Fragment加载数据
        loadData();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        release();
    }

    /**
     * 加载每个Fragment的RootView，抽出来的原因是因为，每个Fragment的RootView都不一样，有自己的titel或是bar
     * @param inflater
     * @param container
     * @return
     */
    protected View loadRootView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.base_fragment_layout,container,false);
        return view;
    }

    protected void loadStateView(View rootView, LayoutInflater inflater, ViewGroup container) {
        FrameLayout frameLayout = rootView.findViewById(R.id.base_container);
        View fragmentItemView = inflater.inflate(getFragmentItemViewResId(), container, false);
        frameLayout.addView(fragmentItemView);
    }

    protected void initView(View rootView) {
    }

    protected void initListener() {
    }

    protected void initPresenter() {
    }

    protected void loadData() {
    }

    /**
     * 释放资源和解除绑定
     */
    protected void release(){

    }

    /**
     * 用于给子Fragment来加载自己的item View
     * @return
     */
    protected abstract int getFragmentItemViewResId();
}
