package com.hloong.newtech.bean;

/**
 * Created by hl on 2017/7/29.
 */

public class Book {
    private String name;
    private String price;
    private String writer;

    public Book(String name,String price,String writer){
        this.name = name;
        this.price = price;
        this.writer = writer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}
