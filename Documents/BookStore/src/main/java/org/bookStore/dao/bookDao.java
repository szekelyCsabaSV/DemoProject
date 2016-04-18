package org.bookStore.dao;

import java.util.List;

/**
 * Created by csaba.szekely on 4/12/2016.
 */
public interface BookDao {

    public List<BookEntity> getBooks(String title);

    public void deleteBook(final long id);

    public BookEntity getBookById(final Long id);

    public long createBook(final BookEntity bookEntity);

    public void updateBook(final BookEntity bookEntity);

    public void deleteBooks();

}
