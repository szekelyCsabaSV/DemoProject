package org.bookStore.dao;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by csaba.szekely on 4/12/2016.
 */
public class BookDaoImpl implements BookDao {

    @PersistenceContext(unitName="bookStorePersistence")
    private EntityManager entityManager;

    public List<BookEntity> getBooks() {

        String sql = "SELECT b FROM BookEntity b";

        TypedQuery<BookEntity> query  = entityManager.createQuery(sql, BookEntity.class);
        return query.getResultList();
    }

    public void deleteBook(long id) {

        BookEntity podcast = entityManager.find(BookEntity.class, id);
        entityManager.remove(podcast);
    }

    public BookEntity getBookById(Long id){

        BookEntity result = null;
        try {
            String qlString = "SELECT p FROM BookEntity p WHERE p.id = ?1";
            TypedQuery<BookEntity> query = entityManager.createQuery(qlString, BookEntity.class);
            query.setParameter(1, id);

            result =  query.getSingleResult();
        } catch (NoResultException e) {
            // nothing to do
        }
        return result;
    }


    public long createBook(BookEntity bookEntity) {

        bookEntity.setWrittenDate(new Date());
      //  entityManager.merge(bookEntity);
       entityManager.persist(bookEntity);


        return bookEntity.getId();
    }

    public void updateBook(BookEntity bookEntity) {

        entityManager.merge(bookEntity);
    }

    public void deleteBooks() {

        Query query = entityManager.createNativeQuery("TRUNCATE TABLE books");
        query.executeUpdate();
    }

}
