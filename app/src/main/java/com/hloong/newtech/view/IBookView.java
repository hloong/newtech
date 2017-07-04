package com.hloong.newtech.view;

import com.hloong.newtech.bean.Book;

import java.util.List;

/**
 * Created by hl on 2017/7/29.
 */

public interface IBookView {

    void showLoading();

    void showBooks(List<Book> books);
}
