package com.hloong.newtech;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.hloong.newtech.bean.Book;
import com.hloong.newtech.databinding.ActivityMvvmBinding;

public class MvvmActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_mvvm);
        ActivityMvvmBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_mvvm);
        binding.setBook(new Book("databind","1000",""));


    }
}
