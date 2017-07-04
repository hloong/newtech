package com.hloong.newtech.presenter;

import java.lang.ref.WeakReference;

/**
 * Created by hl on 2017/7/31.
 */

public abstract class BasePresenter<T> {
    protected WeakReference<T> mViewRef;
    public abstract void getData();
    public void attachView(T view){
        mViewRef = new WeakReference<T>(view);
    }

    public void detach(){
        if (mViewRef != null){
            mViewRef.clear();
            mViewRef = null;
        }
    }

}
