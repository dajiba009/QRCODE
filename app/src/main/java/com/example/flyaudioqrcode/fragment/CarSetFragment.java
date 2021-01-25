package com.example.flyaudioqrcode.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flyaudioqrcode.R;
import com.example.flyaudioqrcode.base.BaseFragment;

public class CarSetFragment extends BaseFragment {

    @Override
    protected View loadRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_car_set,container,false);
    }

    @Override
    protected int getFragmentItemViewResId() {
        return R.layout.fragment_car_set_item_view;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
    }
}
