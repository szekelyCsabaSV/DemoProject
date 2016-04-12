package org.bookStore.dao;

import java.util.List;

/**
 * Created by csaba.szekely on 4/12/2016.
 */
public interface BookDao {

    public List<BookEntity> getBooks();
    public void deleteBook(long id);
    public BookEntity getBookById(Long id);
    public long createBook(BookEntity bookEntity);
    public void updateBook(BookEntity bookEntity);
    public void deleteBooks();
}
