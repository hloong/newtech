package com.hloong.newtech.designmodel.factory;

/**
 * Created by hl
 * Created Time 03/08/2017.
 * Descrition：
 */

public class FactoryModel{
    public static Api create(int type){
        switch (type){
            case 1:
                return new InterfA();
            case 2:
                return new InterfB();
            default:
                return new InterfB();
        }
    }

    public <T extends Api> T createFactory(Class<T> clz){
        Api api = null;
        try {
            api = (Api)Class.forName(clz.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (T)api;
    }
}
