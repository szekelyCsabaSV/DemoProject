package org.bookStore.service;

import org.bookStore.Resource.Book;
import org.bookStore.errorhandling.AppException;

import java.util.List;

/**
 * Created by csaba.szekely on 4/12/2016.
 */
public interface BookService {

    public Long createBook(Book book);
    public void createboks(List<Book> books) throws AppException;
    public List<Book> getBooks() throws AppException;
    public Book getBookById(Long id) throws AppException;
    public void updateFullyBook(Book book) throws AppException;
    public void deleteBookById(Long id);
    public void deleteBooks();
    public Book verifyBookExistenceById(Long id);
}
