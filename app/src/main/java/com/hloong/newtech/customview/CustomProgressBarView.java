package com.hloong.newtech.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.hloong.newtech.R;

/**
 * Created by hl
 * Created Time 05/08/2017.
 * Descrition：
 */

public class CustomProgressBarView extends View {
    private int max;
    private int roundBackgroundColor;
    private int roundColor;
    private int textColor;
    private float textSize;
    private float roundWith;
    private boolean textShow;
    private int style;
    private int progress;

    public static final int STROKE = 0;
    public static final int FILL = 1;

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public CustomProgressBarView(Context context) {
        super(context);
    }

    public CustomProgressBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initType(context,attrs);
    }

    public CustomProgressBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initType(context,attrs);
    }

    private void initType(Context context,AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView);
        max = typedArray.getInteger(R.styleable.CustomView_max,100);
        roundBackgroundColor = typedArray.getColor(R.styleable.CustomView_roundBackgroundColor, Color.RED);
        roundColor = typedArray.getColor(R.styleable.CustomView_roundColor,Color.GRAY);
        textColor = typedArray.getColor(R.styleable.CustomView_textColor,Color.BLACK);
        textShow = typedArray.getBoolean(R.styleable.CustomView_textShow,true);
        textSize = typedArray.getDimension(R.styleable.CustomView_textSize,0);
        roundWith = typedArray.getDimension(R.styleable.CustomView_roundWith,0);
        style = typedArray.getInt(R.styleable.CustomView_style,0);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int center = getWidth()/2;

        float radius = center - roundWith / 2;
        paint.setColor(roundColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(roundWith);
        canvas.drawCircle(center,center,radius,paint);

        paint.setColor(textColor);
        paint.setStrokeWidth(0);
        paint.setTextSize(textSize);
        paint.setTypeface(Typeface.DEFAULT);
        int percent = (int) ((float)progress/max * 100);
        if (textShow && percent != 0 && style == STROKE){
            canvas.drawText(percent+"%",(getWidth()-paint.measureText(percent+"%"))/2f,
                    getWidth()/2f-(paint.descent()+paint.ascent())/2f,paint);
        }

        RectF oval = new RectF(center-radius, center-radius, center+radius, center+radius);
        paint.setColor(roundBackgroundColor);
        paint.setStrokeWidth(roundWith);
        switch (style) {
            case STROKE:
                paint.setStyle(Paint.Style.STROKE);
                canvas.drawArc(oval , 0, 360*progress/max, false, paint);
                break;
            case FILL:
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                if(progress!=0)
                    canvas.drawArc(oval , 0, 360*progress/max, true, paint);
                break;
        }
    }
    public synchronized int getProgress() {
        return progress;
    }

    public synchronized void setProgress(int progress){
        if (progress < 0){
            throw new IllegalArgumentException("progress 不能小于0");
        }else if (progress > max){
            progress = max;
        }else {
            this.progress = progress;
            postInvalidate();
        }
    }

    public synchronized int getMax(){
        return max;
    }
    public synchronized void setMax(int max) {
        if (max < 0){
            throw new IllegalArgumentException("max不能小于0");
        }
        this.max = max;
    }

    public int getRoundBackgroundColor() {
        return roundBackgroundColor;
    }

    public void setRoundBackgroundColor(int roundBackgroundColor) {
        this.roundBackgroundColor = roundBackgroundColor;
    }

    public int getRoundColor() {
        return roundColor;
    }

    public void setRoundColor(int roundColor) {
        this.roundColor = roundColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public float getRoundWith() {
        return roundWith;
    }

    public void setRoundWith(float roundWith) {
        this.roundWith = roundWith;
    }

    public boolean isTextShow() {
        return textShow;
    }

    public void setTextShow(boolean textShow) {
        this.textShow = textShow;
    }


}
