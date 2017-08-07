package com.hloong.newtech.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.hloong.newtech.R;

/**
 * Created by hl
 * Created Time 06/08/2017.
 * Descrition：
 */

public class GradientForView extends View {
    private Bitmap bitmap;
    private BitmapShader shader;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int width,height;
    private RadialGradient radialGradient;
    private SweepGradient sweepGradient;
    private ComposeShader composeShader;
    private int[] colors = {Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW};
    public GradientForView(Context context) {
        super(context);
        gradientView();
    }

    public GradientForView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gradientView();
    }

    public GradientForView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        gradientView();
    }

    private void gradientView(){
        bitmap = ((BitmapDrawable)getResources().getDrawable(R.drawable.skycity)).getBitmap();
        width = bitmap.getWidth();
        height = bitmap.getHeight();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        shader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        paint.setShader(shader);
        LinearGradient linearGradient = new LinearGradient(0,0,400,400,colors,null, Shader.TileMode.REPEAT);

        composeShader = new ComposeShader(linearGradient, shader, PorterDuff.Mode.SRC_OVER);
        paint.setShader(composeShader);
        canvas.drawRect(0, 0, 800, 1000, paint);
    }
}
