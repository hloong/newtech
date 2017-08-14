package com.hloong.newtech.activity;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;

import com.hloong.newtech.R;

public class EffectActivity extends Activity {
    private Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_effect);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Animator animator = ViewAnimationUtils.createCircularReveal(btn1,btn1.getWidth()/2,btn1.getHeight()/2,0,btn1.getWidth());
                animator.setDuration(200);
                animator.setInterpolator(new AccelerateInterpolator());
                animator.start();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Animator animator = ViewAnimationUtils.createCircularReveal(btn2,0,0,0,btn2.getHeight());
                animator.setInterpolator(new AccelerateInterpolator());
                animator.setDuration(200);
                animator.start();
            }
        });


    }
}
