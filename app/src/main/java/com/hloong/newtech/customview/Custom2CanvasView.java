package com.hloong.newtech.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hl
 * Created Time 12/08/2017.
 * Descrition：
 */

public class Custom2CanvasView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public Custom2CanvasView(Context context) {
        this(context,null);
    }

    public Custom2CanvasView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Custom2CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);




    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        RectF rect = new RectF(100,100,500,500);
//        canvas.drawRect(rect,paint);
//        paint.setColor(Color.GREEN);
//
//        canvas.drawArc(rect,0,90,true,paint);
//
//        paint.setColor(Color.BLUE);
//        Path path = new Path();
//        path.lineTo(100,100);
//        path.cubicTo(100,100,300,0,500,200);
//        canvas.drawPath(path,paint);

        Path path1 = new Path();
        path1.addOval(rect, Path.Direction.CCW);

        Region region = new Region(100,100,500,500);
        Region region1 = new Region();
        region.setPath(path1,region);

        RegionIterator regionIterator = new RegionIterator(region1);
        Rect rect2 = new Rect();
        while (regionIterator.next(rect2)){
            canvas.drawRect(rect2,paint);
        }

        RectF r = new RectF(200, 200, 400, 500);
        canvas.drawRect(r, paint);
        paint.setColor(Color.BLUE);
//        canvas.clipRect(new Rect(250, 250, 300, 400));
//        canvas.restore();
//        canvas.drawColor(Color.YELLOW);
//        canvas.translate(55,55);//平移画布，不可逆，所以绘制一次后就固定了，必须重置
        canvas.drawRect(r,paint);
        canvas.rotate(30f);
        canvas.drawRect(r,paint);

    }
}
