package com.hloong.newtech.designmodel;

/**
 * Created by hl on 2017/8/3.
 * 默认
 */
public class SingleModel {
    // android默认生成
    private static SingleModel ourInstance = new SingleModel();

    public static SingleModel getInstance() {
        return ourInstance;
    }

    private SingleModel() {
    }

    //引用父类的静态字段会不会初始化子类？ 通过自定义对象数组类型会不会加载该类型


}
