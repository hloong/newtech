package com.hloong.newtech.presenter;

import com.hloong.newtech.bean.Book;
import com.hloong.newtech.model.BookModel;
import com.hloong.newtech.model.IBookModel;
import com.hloong.newtech.view.IBookView;

import java.util.List;

/**
 * Created by hl on 2017/7/29.
 */

public class BookPresenter {
    IBookView bookView;

    IBookModel bookModel = new BookModel();

    public BookPresenter(IBookView bookView){
        this.bookView = bookView;
    }

    public void getData(){
        bookView.showLoading();
        if (bookModel!= null){
            bookModel.loadBook(new IBookModel.OnBookListener() {
                @Override
                public void onComplete(List<Book> books) {
                    bookView.showBooks(books);
                }
            });
        }
    }
}
