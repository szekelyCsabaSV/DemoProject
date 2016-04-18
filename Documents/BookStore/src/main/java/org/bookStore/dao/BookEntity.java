package org.bookStore.dao;

import org.apache.commons.beanutils.BeanUtils;
import org.bookStore.Resource.Author;
import org.bookStore.Resource.Book;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by csaba.szekely on 4/12/2016.
 */
@Entity
@Table(name="books")
public class BookEntity implements Serializable {

    private static final long serialVersionUID = -5215523204352873154L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    private Long book_id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="written_date")
    private Date writtenDate;


    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name="book_id",nullable = false,updatable = false,unique = true),
            inverseJoinColumns = @JoinColumn(name = "author_id",nullable = false,updatable = false,unique = true))
    private Set<AuthorEntity> authors = new HashSet<AuthorEntity>();


    public Set<AuthorEntity> getAuthors() {

        return authors;
    }

    public void setAuthors(Set<AuthorEntity> authors) {

        this.authors = authors;
    }

    public BookEntity(){}

    public BookEntity( String title, String description, Date writtenDate){

        this.title = title;
        this.description = description;
        this.writtenDate = writtenDate;
    }

    public BookEntity( long book_id, String title, String description, Date writtenDate){

        this.book_id = book_id;
        this.title = title;
        this.description = description;
        this.writtenDate = writtenDate;
    }

    public BookEntity(Book book){

        this.book_id = book.getBook_id();
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.writtenDate = book.getWrittenDate();

        for (Author bu : book.getAuthors() ){

            this.getAuthors().add(new AuthorEntity(bu.getAuthor_id() , bu.getName(),bu.getAdress(),bu.getBornDate()));
        }
    }

    public Long getBook_id() {

        return  book_id;
    }

    public void setBook_id(Long id) {

        this.book_id = id;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public Date getWrittenDate() {

        return writtenDate;
    }

    public void setWrittenDate(Date writtenDate) {

        this.writtenDate = writtenDate;
    }
}
