package com.hloong.newtech.activity;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hloong.newtech.R;
import com.hloong.newtech.bean.Boy;
import com.hloong.newtech.view.IBoyView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static android.content.ContentValues.TAG;


public class MainActivity extends Activity implements IBoyView {
    ListView listView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv_view);

        listView = (ListView) findViewById(R.id.listview);
        textView.setText(getApplicationMetaValue("UMENG_CHANNEL"));
        //ceshi
        initObservable();
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AnimationActivity.class));
            }
        });
        findViewById(R.id.tv_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CustomViewActivity.class));
            }
        });
        findViewById(R.id.tvAni).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,EffectActivity.class));
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });
    }

    private String getApplicationMetaValue(String umeng_channel) {
        String value = "";
        try{
            ApplicationInfo appInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            value = appInfo.metaData.getString(umeng_channel);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }


    private void initObservable() {
        //创建被观察者
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("test1");
                e.onNext("test2");
                e.onNext("test3");

                e.onComplete();
//                e.onError(new Throwable("onError-->"));
            }
        });
        //创建观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG,"subscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG,s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG,e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e(TAG,"onCompleted");
            }
        };
        //注册，将观察者和被观察者关联，将会出发OnSubscribe.call方法
        observable.subscribe(observer);
    }

    @Override
    public void showLoading() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.show();

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void getData(List<Boy> boys) {
        listView.setAdapter(new ArrayAdapter<Boy>(MainActivity.this,android.R.layout.simple_list_item_1,boys));
    }




    class MyTask extends AsyncTask<Void,Integer,Void>{


        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }
    }
}
