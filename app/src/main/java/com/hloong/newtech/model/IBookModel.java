package com.hloong.newtech.model;

import com.hloong.newtech.bean.Book;

import java.util.List;

/**
 * Created by hl on 2017/7/29.
 */

public interface IBookModel {

    void loadBook(OnBookListener onBookListener);

    interface OnBookListener{
        void onComplete(List<Book> books);
    }
}
