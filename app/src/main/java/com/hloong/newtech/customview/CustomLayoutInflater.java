package com.hloong.newtech.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by hl on 2017/7/6.
 */

public class CustomLayoutInflater extends LayoutInflater{


    protected CustomLayoutInflater(Context context) {
        super(context);
    }

    protected CustomLayoutInflater(LayoutInflater original, Context newContext) {
        super(original, newContext);
    }

    @Override
    public LayoutInflater cloneInContext(Context newContext) {
        return new CustomLayoutInflater(this,newContext);
    }


    public class CustomFactory implements Factory{

        @Override
        public View onCreateView(String name, Context context, AttributeSet attrs) {
            View view  = null;
            if (view == null){
                view = creatView(name,context,attrs);
            }

            return null;
        }

        public View creatView(String name, Context context, AttributeSet attrs){

            return null;
        }
    }
}
