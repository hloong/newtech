package com.hloong.newtech.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.hloong.newtech.R;

/**
 * Created by hl
 * Created Time 08/08/2017.
 * Descrition：
 */

public class MaskFilterView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public MaskFilterView(Context context) {
        super(context);
    }

    public MaskFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaskFilterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);//关闭硬件加速，没关就没效果
        paint.setColor(Color.RED);
        RectF rectF = new RectF(100,100,300,300);
        //颜色矩阵，去色
        ColorMatrix matrix = new ColorMatrix(new float[]{
                0.213f,0.715f,0.072f,0,0,
                0.213f,0.715f,0.072f,0,0,
                0.213f,0.715f,0.072f,0,0,
                0,0,0,1f,0,
        });
        ColorMatrix matrix1 = new ColorMatrix();
//        matrix1.setScale(1,1,1.5f,1);//表示设置RGB中的蓝色加强1.5倍
//        matrix1.setScale(1,1.5f,1,1);//表示设置RGB中的绿色加强1.5倍
//        matrix1.setScale(1.5f,1,1,1);//表示设置RGB中的红色加强1.5倍
        paint.setColorFilter(new ColorMatrixColorFilter(matrix1));
//        paint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.NORMAL));
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.skycity);
        canvas.drawBitmap(bitmap,100,300,paint);


    }
}
