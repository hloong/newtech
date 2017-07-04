package com.hloong.newtech.model;

import com.hloong.newtech.bean.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hl on 2017/7/29.
 */

public class BookModel implements IBookModel {

    @Override
    public void loadBook(OnBookListener onBookListener) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("ce","12","33"));
        books.add(new Book("ce2","123","334"));
        books.add(new Book("ce3","125","335"));
        books.add(new Book("ce4","126","336"));

        onBookListener.onComplete(books);//回调
    }
}
