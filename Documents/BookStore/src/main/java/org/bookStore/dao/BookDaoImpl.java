package org.bookStore.dao;

import javax.persistence.*;
import java.util.List;

/**
 * Created by csaba.szekely on 4/12/2016.
 */
public class BookDaoImpl implements BookDao {

    @PersistenceContext(unitName="bookStorePersistence")
    private EntityManager entityManager;

    public List<BookEntity> getBooks(String title) {


        String qlString = "SELECT b FROM BookEntity b ";
        if (title !=null){
            qlString += "where b.title like '%" + title + "%'";
        }
        TypedQuery<BookEntity> query = entityManager.createQuery(qlString, BookEntity.class);

        return  query.getResultList();
    }



    public void deleteBook(long id) {

        BookEntity bookEntity = entityManager.find(BookEntity.class, id);
        entityManager.remove(bookEntity);
    }

    public BookEntity getBookById(Long id){

        BookEntity result = null;
        try {

            String qlString = "SELECT p FROM BookEntity p WHERE p.book_id = ?1";
            TypedQuery<BookEntity> query = entityManager.createQuery(qlString, BookEntity.class);
            query.setParameter(1, id);

            result =  query.getSingleResult();
        } catch (NoResultException e) {
            // nothing to do
        }
        return result;
    }


    public long createBook(BookEntity bookEntity) {

        return  entityManager.merge(bookEntity).getBook_id();
    }

    public void updateBook(BookEntity bookEntity) {

        entityManager.merge(bookEntity);
    }

    public void deleteBooks() {

        Query query = entityManager.createNativeQuery("TRUNCATE TABLE books");
        query.executeUpdate();
    }


}
