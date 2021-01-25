package com.example.flyaudioqrcode.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.flyaudioqrcode.R;

import androidx.annotation.Nullable;

public class GrayQrCodeView extends View {

    private Bitmap mQrCode;
    private Bitmap mQrCodeIcon;
    private Bitmap mQrCode2;
    private Bitmap mQrCodeIcon2;

    public GrayQrCodeView(Context context) {
        this(context, null);
    }

    public GrayQrCodeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GrayQrCodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initBitmap();
    }

    private void initBitmap() {
        mQrCode = BitmapFactory.decodeResource(getResources(), R.mipmap.qrcode_grayy, null);
        mQrCodeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.qrcode_icon_gray, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mQrCode != null && mQrCodeIcon != null) {
            int finalWidth = getMeasuredWidth();
            int finalHeight = getMeasuredHeight();
            int QrCodeWidth = mQrCode.getWidth();
            int QrCodeHeight = mQrCode.getHeight();

            //如果Qrcode大于或小于View则压缩/扩大QrCode
            Matrix matrix = new Matrix();
            float sx = (float) finalWidth / (float) QrCodeWidth;
            matrix.postScale(sx, sx);
            mQrCode2 = Bitmap.createBitmap(mQrCode, 0, 0, QrCodeWidth, QrCodeHeight, matrix, true);
            mQrCodeIcon2 = Bitmap.createBitmap(mQrCodeIcon, 0, 0, mQrCodeIcon.getWidth(), mQrCodeIcon.getHeight(), matrix, true);

            Paint paint = new Paint();
            canvas.drawBitmap(mQrCode2, 0, 0, paint);

            int iconWidth = mQrCode2.getWidth() / 2 - mQrCodeIcon2.getWidth() / 2;
            int iconHeight = mQrCode2.getHeight() / 2 - mQrCodeIcon2.getHeight() / 2;

            canvas.drawBitmap(mQrCodeIcon2, iconWidth, iconHeight, paint);
        }
    }
}
