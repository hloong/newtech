package com.hloong.newtech.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.hloong.newtech.R;
import com.hloong.newtech.customview.CustomProgressBarView;

public class CustomViewActivity extends Activity {
    private CustomProgressBarView progressBarView;
    private int progress=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        progressBarView = (CustomProgressBarView) findViewById(R.id.cview);
        progressBarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        while (progress <= 100) {
                            progress += 2;
                            progressBarView.setProgress(progress);
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });

    }
}
