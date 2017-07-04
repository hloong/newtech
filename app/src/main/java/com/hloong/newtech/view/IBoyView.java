package com.hloong.newtech.view;

import com.hloong.newtech.bean.Boy;

import java.util.List;

/**
 * Created by hl on 2017/7/31.
 */

public interface IBoyView {
    void showLoading();
    void stopLoading();
    void getData(List<Boy> boys);
}
