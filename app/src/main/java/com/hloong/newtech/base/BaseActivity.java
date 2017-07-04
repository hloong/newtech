package com.hloong.newtech.base;

import android.app.Activity;
import android.os.Bundle;

import com.hloong.newtech.presenter.BasePresenter;

/**
 * Created by hl on 2017/7/31.
 */

public abstract class BaseActivity<V,T extends BasePresenter<V>> extends Activity {

    public T presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        presenter.attachView((V)this);
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

    protected abstract T createPresenter();
}
