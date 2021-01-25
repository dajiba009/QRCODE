package com.example.flyaudioqrcode.utils;

import android.content.Context;

public class UIUtils {
    public static int dp2px(Context context, int dpValue){
        float density = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * density + 0.5f);
    }
}
