package com.hloong.newtech.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hl
 * Created Time 10/08/2017.
 * Descrition：
 */

public class PathView extends View{

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public PathView(Context context) {
        this(context,null);
    }

    public PathView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画一个正弦曲线
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        Path path = new Path();
        path.moveTo(100,300);
        path.quadTo(200,150,300,300);
        path.quadTo(400,450,500,300);
        canvas.drawPath(path,paint);

    }
}
