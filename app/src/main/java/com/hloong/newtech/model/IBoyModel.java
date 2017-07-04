package com.hloong.newtech.model;

import com.hloong.newtech.bean.Boy;

import java.util.List;

/**
 * Created by hl on 2017/7/31.
 */

public interface IBoyModel{
    void loadBoy(LoadBoyInterface loadBoyInterface);

    interface LoadBoyInterface{
        void loadBoy(List<Boy> boys);
    }
}
