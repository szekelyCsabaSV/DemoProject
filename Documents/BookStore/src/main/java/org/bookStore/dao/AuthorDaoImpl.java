package org.bookStore.dao;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;

/**
 * Created by csaba.szekely on 4/13/2016.
 */

public class AuthorDaoImpl implements AuthorDao{

    @PersistenceContext(unitName="bookStorePersistence")
    private EntityManager entityManager;

    public List<AuthorEntity> getAuthors() {

        String sql = "SELECT a FROM AuthorEntity a";

        TypedQuery<AuthorEntity> query  = entityManager.createQuery(sql, AuthorEntity.class);
        return query.getResultList();
    }

    public void deleteAuthor(long id) {

        AuthorEntity author = entityManager.find(AuthorEntity.class, id);
        for (BookEntity book : author.getBooks()) {

            book.getAuthors().remove(author);
            entityManager.persist(book);
        }
        author.setBooks(new HashSet<BookEntity>());
        entityManager.persist(author);
        entityManager.flush();
        entityManager.remove(author);
    }

    public AuthorEntity getAuthorById(Long id) {

        AuthorEntity result = null;
        try {

            String qlString = "SELECT a FROM AuthorEntity a WHERE a.author_id = ?1";
            TypedQuery<AuthorEntity> query = entityManager.createQuery(qlString, AuthorEntity.class);
            query.setParameter(1, id);
            result =  query.getSingleResult();
        } catch (NoResultException e) {
            // nothing to do
        }
        return result;
    }

    public long createAuthor(AuthorEntity authorEntity) {

        authorEntity = entityManager.merge(authorEntity);
        for(BookEntity bookEntity : authorEntity.getBooks()){

            bookEntity.getAuthors().add(authorEntity);
            entityManager.merge(bookEntity);
        }

        return authorEntity.getAuthor_id();
    }

    public void updateAuthor(AuthorEntity authorEntity) {

        for(BookEntity bookEntity : authorEntity.getBooks()){

            bookEntity.getAuthors().add(authorEntity);
            entityManager.merge(bookEntity);
        }

        entityManager.merge(authorEntity);
    }

    public void deleteAuthor() {

        Query query = entityManager.createNativeQuery("TRUNCATE TABLE authors");
        query.executeUpdate();
    }
}
