package com.hloong.newtech.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hl on 2017/7/30.
 */

public class CustomView extends View{
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private String str = "CustomView";

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
//        paint.setStrokeWidth(20);
//        paint.setStrokeCap(Paint.Cap.ROUND);
//        canvas.drawLine(100,10,100,500,paint);
//        canvas.drawCircle(200,300,150,paint);

//        drawPath(canvas);
        drawText(canvas);


    }

    /**
     * 绘制文字
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        paint.setTextSkewX(-0.25f);
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(50);
        canvas.drawText("gshabing",16,36,paint);

        int top = 100;
        int baselineX = 400;


        paint.setColor(Color.RED);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float baselineY = top - fontMetrics.top;
        canvas.drawText(str, baselineX, baselineY, paint);

        paint.setColor(Color.GREEN);
        canvas.drawText(str, baselineX, top, paint);

        paint.setColor(Color.YELLOW);
        baselineY = top + (fontMetrics.bottom-fontMetrics.top)/2 - fontMetrics.bottom;
        canvas.drawText(str, baselineX, baselineY, paint);

    }

    /**
     * 绘制path路径
     * @param canvas
     */
    private void drawPath(Canvas canvas) {

        Path path = new Path();
        path.moveTo(100,100);
        path.lineTo(300,100);
        path.lineTo(100,300);
        paint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawPath(path,paint);

        path = new Path();
        path.moveTo(100,400);
        path.lineTo(300,400);
        path.lineTo(100,600);
        paint.setStrokeJoin(Paint.Join.BEVEL);
        canvas.drawPath(path,paint);

        path = new Path();
        path.moveTo(100,700);
        path.lineTo(300,700);
        path.lineTo(100,900);
        paint.setStrokeJoin(Paint.Join.MITER);
        canvas.drawPath(path,paint);
    }
}
