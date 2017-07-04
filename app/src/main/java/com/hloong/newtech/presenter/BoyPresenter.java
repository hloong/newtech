package com.hloong.newtech.presenter;

import com.hloong.newtech.bean.Boy;
import com.hloong.newtech.model.BoyModel;
import com.hloong.newtech.model.IBoyModel;
import com.hloong.newtech.view.IBoyView;

import java.util.List;

/**
 * Created by hl on 2017/7/31.
 */

public class BoyPresenter extends BasePresenter<IBoyModel>{
    BoyModel boyModel = new BoyModel();
    IBoyView boyView;
    public BoyPresenter(IBoyView boyView){
        this.boyView  = boyView;
    }

    @Override
    public void getData(){
        if (boyModel != null){
            boyModel.loadBoy(new IBoyModel.LoadBoyInterface() {
                @Override
                public void loadBoy(List<Boy> boys) {
                    boyView.getData(boys);
                }
            });
        }
    }
}
