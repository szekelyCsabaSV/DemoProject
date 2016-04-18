package org.bookStore.service;

import org.bookStore.Resource.Author;
import org.bookStore.Resource.Book;
import org.bookStore.errorhandling.AppException;

import java.util.List;

/**
 * Created by csaba.szekely on 4/13/2016.
 */
public interface AuthorService {

    public Long createAuthor(Author author);

    public void createAuthors(List<Author> authors) throws AppException;

    public List<Author> getAuthors() throws AppException;

    public Author getAuthorById(Long id) throws AppException;

    public void updateFullyAuthor(Author author) throws AppException;

    public void deleteAuthorById(Long id);

    public void deleteAuthor();

    public Author verifyAuthorExistenceById(Long id);
}
