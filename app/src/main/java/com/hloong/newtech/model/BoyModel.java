package com.hloong.newtech.model;

import com.hloong.newtech.bean.Boy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hl on 2017/7/31.
 */

public class BoyModel implements IBoyModel {
    @Override
    public void loadBoy(LoadBoyInterface loadBoyInterface) {

        List<Boy> boys = new ArrayList<>();
        boys.add(new Boy("Boy1"));
        boys.add(new Boy("Boy2"));
        boys.add(new Boy("Boy3"));
        boys.add(new Boy("Boy3"));
        loadBoyInterface.loadBoy(boys);
    }
}
