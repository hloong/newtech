package com.hloong.newtech.bean;

import android.app.Activity;

/**
 * Created by hl
 * Created Time 14/08/2017.
 * Descrition：
 */

public class Acti {
    private Activity activity;
    private String name;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Acti(Activity activity,String name){
        this.activity = activity;
        this.name = name;
    }
}
