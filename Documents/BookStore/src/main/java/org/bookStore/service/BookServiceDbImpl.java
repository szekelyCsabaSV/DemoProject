package org.bookStore.service;

import org.bookStore.Resource.Book;
import org.bookStore.dao.BookEntity;
import org.bookStore.dao.BookDao;
import org.bookStore.errorhandling.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by csaba.szekely on 4/12/2016.
 */
public class BookServiceDbImpl implements BookService {

    @Autowired
    BookDao bookDao;

    @Transactional("transactionManager")
    public Long createBook(Book book) {

       return bookDao.createBook(new BookEntity(book));
    }

    @Transactional("transactionManager")
    public void createboks(List<Book> books) throws AppException {
        for (Book book : books) {
            createBook(book);
        }
    }

    public List<Book> getBooks() throws AppException {

        return getBooksFromEntities( bookDao.getBooks());
    }

    public Book getBookById(Long id) throws AppException {
        BookEntity bookId = bookDao.getBookById(id);
        if(bookId == null){
            throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
                    404,
                    "The podcast you requested with id " + id + " was not found in the database",
                    "Verify the existence of the podcast with the id " + id + " in the database","");
        }

        return new Book(bookDao.getBookById(id));
    }

    @Transactional("transactionManager")
    public void updateFullyBook(Book book) throws AppException {
        if(isFullUpdate(book)){
            throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(),
                    400,
                    "Please specify all properties for Full UPDATE",
                    "required properties - id, title, feed, lnkOnPodcastpedia, description" ,
                    "");
        }

        Book verifyPodcastExistenceById = verifyBookExistenceById(book.getId());
        if(verifyPodcastExistenceById == null){
            throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
                    404,
                    "The resource you are trying to update does not exist in the database",
                    "Please verify existence of data in the database for the id - " + book.getId(),
                    "");
        }

        bookDao.updateBook(new BookEntity(book));
    }

    @Transactional("transactionManager")
    public void deleteBookById(Long id) {

        bookDao.deleteBook(id);
    }

    @Transactional("transactionManager")
    public void deleteBooks() {

        bookDao.deleteBooks();
    }

    public Book verifyBookExistenceById(Long id) {

            Book result = null;
            BookEntity bookById = bookDao.getBookById(id);

            if(bookById != null){
                result = new Book(bookById);
            }
        return result;
    }


    private List<Book> getBooksFromEntities(List<BookEntity> bookEntities) {
        List<Book> response = new ArrayList<Book>();

        for(BookEntity bookEntity : bookEntities){
            response.add(new Book(bookEntity));
        }

        return response;
    }

    private boolean isFullUpdate(Book book) {
        return book.getId() == null
                || book.getTitle() == null
                || book.getDescription() == null;
    }
}
