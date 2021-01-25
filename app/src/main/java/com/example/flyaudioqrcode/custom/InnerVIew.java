package com.example.flyaudioqrcode.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.flyaudioqrcode.utils.Constants;

import androidx.annotation.Nullable;

public class InnerVIew extends View {
    private Bitmap mBitmap;
    private Bitmap mOtherBitmap;

    public InnerVIew(Context context) {
        this(context,null);
    }

    public InnerVIew(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public InnerVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawInner(canvas);
    }

    public void setImage(Bitmap innerBitmap,Bitmap QrBitmap){
        this.mBitmap = innerBitmap;
        this.mOtherBitmap = QrBitmap;
        invalidate();
    }

    //画二维码框，先画最外围的白色圆角矩形，画大二维码，画里面的白色小圆角矩阵，画灰色圆角矩形，画内部Icon图案
    private void drawInner(Canvas canvas){
        if(mBitmap != null && mOtherBitmap != null){
            Paint paint =new Paint(Paint.ANTI_ALIAS_FLAG);
//            int width = mBitmap.getWidth();
//            int height = mBitmap.getHeight();
            int finalWidth = getMeasuredWidth();
            int finalHeight = getMeasuredHeight();
//            int localWidth = finalWidth/2-width/2;
//            int localHeight = finalHeight/2-height/2;
            int dv = finalHeight/20;

            //===================压缩大二维码图案=========================
            int IMAGE_HALFWIDTH = 50;
            IMAGE_HALFWIDTH = mOtherBitmap.getWidth()/10;
            int halfW = finalWidth / 2;
            int halfH = finalHeight / 2;
            Matrix m = new Matrix();
            float sx = (float) 2 * IMAGE_HALFWIDTH / mBitmap.getWidth();
            float sy = (float) 2 * IMAGE_HALFWIDTH / mBitmap.getHeight();
            m.setScale(sx, sy);
            mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), m, false);
            int localWidth = finalWidth/2-mBitmap.getWidth()/2;
            int localHeight = finalWidth/2-mBitmap.getHeight()/2;
            int width = mBitmap.getWidth();
            int height = mBitmap.getHeight();
            //===================压缩inner图案=========================

            //画大圆矩形
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRoundRect(0,0,finalWidth,finalHeight,Constants.ROUNDRECT_RADIUS,Constants.ROUNDRECT_RADIUS,paint);

            //画二维码
            canvas.drawBitmap(mOtherBitmap,Constants.ROUNDRECT_RADIUS,Constants.ROUNDRECT_RADIUS,paint);

            //画内白框
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRoundRect(localWidth-dv,localHeight-dv,localWidth+width+dv,localHeight+height+dv,
                    Constants.ROUNDRECT_RADIUS,Constants.ROUNDRECT_RADIUS,paint);

            //画内灰框
            paint.setColor(Color.GRAY);
            paint.setStrokeWidth(3);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawRoundRect(localWidth-dv/2,localHeight-dv/2,localWidth+width+dv/2,localHeight+height+dv/2,
                    Constants.ROUNDRECT_RADIUS,Constants.ROUNDRECT_RADIUS,paint);

            //画inner内图案
            canvas.drawBitmap(mBitmap,localWidth,localHeight,paint);
        }
    }
}
