package org.bookStore.service;

import org.bookStore.Resource.Author;
import org.bookStore.dao.AuthorDao;
import org.bookStore.dao.AuthorEntity;
import org.bookStore.errorhandling.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by csaba.szekely on 4/13/2016.
 */
public class AuthorServicDdImpl implements AuthorService{

    @Autowired
    AuthorDao authorDao;

    @Transactional("transactionManager")
    public Long createAuthor(Author author) {

        return  authorDao.createAuthor(new AuthorEntity(author));
    }

    @Transactional("transactionManager")
    public void createAuthors(List<Author> authors) throws AppException {

        for(Author author : authors){

            createAuthor(author);
        }
    }

    public List<Author> getAuthors() throws AppException {

        return getAuthorsFromEntities(authorDao.getAuthors());
    }

    public Author getAuthorById(Long id) throws AppException {

        AuthorEntity authorById = authorDao.getAuthorById(id);
        if(authorById == null){
            throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
                    404,
                    "The author you requested with id " + id + " was not found in the database",
                    "Verify the existence of the podcast with the id " + id + " in the database","");
        }

        return new Author(authorDao.getAuthorById(id));
    }

    @Transactional("transactionManager")
    public void updateFullyAuthor(Author author) throws AppException {

        if(isFullUpdate(author)){
            throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(),
                    400,
                    "Please specify all properties for Full UPDATE",
                    "required properties - id, title, feed, lnkOnPodcastpedia, description" ,
                    "");
        }

        Author verifyAuthorExistenceById = verifyBookExistenceById(author.getAuthor_id());
        if(verifyAuthorExistenceById == null){
            throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
                    404,
                    "The resource you are trying to update does not exist in the database",
                    "Please verify existence of data in the database for the id - " + author.getAuthor_id(),
                    "");
        }

        authorDao.updateAuthor(new AuthorEntity(author));
    }

    @Transactional("transactionManager")
    public void deleteAuthorById(Long id) {

        authorDao.deleteAuthor(id);
    }

    public void deleteAuthor() {

        authorDao.deleteAuthor();
    }

    public Author verifyAuthorExistenceById(Long id) {
        Author result = null;
        AuthorEntity authorById = authorDao.getAuthorById(id);

        if(authorById != null){
            result = new Author(authorById);
        }
        return result;
    }

    private List<Author> getAuthorsFromEntities(List<AuthorEntity> authorEntitys) {

        List<Author> response = new ArrayList<Author>();

        for(AuthorEntity authorEntity : authorEntitys){

            response.add(new Author(authorEntity));
        }

        return response;
    }

    private boolean isFullUpdate(Author author) {

        return author.getAuthor_id() == null
                || author.getAdress() == null
                || author.getName() == null;
    }

    private Author verifyBookExistenceById(Long id) {

        Author result = null;
        AuthorEntity authorById = authorDao.getAuthorById(id);

        if(authorById != null){
            result = new Author(authorById);
        }
        return result;
    }
}
