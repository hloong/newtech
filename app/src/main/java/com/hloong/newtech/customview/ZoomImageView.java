package com.hloong.newtech.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.hloong.newtech.R;

/**
 * Created by hl
 * Created Time 05/08/2017.
 * Descrition：
 */

public class ZoomImageView extends View {

    private Bitmap bitmap;
    private Bitmap bitmapScale;
    private static final int scale = 3;
    private static final int RADIUS = 120;
    private ShapeDrawable shapeDrawable;
    private Matrix matrix = new Matrix();

    public ZoomImageView(Context context) {
        super(context);
        zoomImageView();
    }



    public ZoomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        zoomImageView();
    }

    public ZoomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        zoomImageView();
    }

    private void zoomImageView(){
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.skycity);
        bitmapScale = Bitmap.createScaledBitmap(bitmap,bitmap.getWidth()*scale,bitmap.getHeight()*scale,true);
        //先画一个圆形的图片，盖在canvas上
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setShader(shader);
        //切出矩形区域--用于绘制园（内切圆）
        shapeDrawable.setBounds(0,0,RADIUS*2,RADIUS*2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,0,0,null);
        shapeDrawable.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        matrix.setTranslate(RADIUS - x*scale,RADIUS - y*scale);
        shapeDrawable.getPaint().getShader().setLocalMatrix(matrix);
        shapeDrawable.setBounds(x-RADIUS,y-RADIUS,x+RADIUS,y+RADIUS);
        invalidate();
        return true;
    }
}
