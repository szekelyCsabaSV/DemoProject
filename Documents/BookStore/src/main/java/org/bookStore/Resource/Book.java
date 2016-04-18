package org.bookStore.Resource;

import org.bookStore.dao.AuthorEntity;
import org.bookStore.dao.BookEntity;
import org.bookStore.helpers.DateISO8601Adapter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.beans.BeanUtils;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by csaba.szekely on 4/12/2016.
 */

@SuppressWarnings("restriction")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Book implements Serializable {

    private static final long serialVersionUID = -753465561483011877L;
    @XmlElement(name = "book_id")
    private Long book_id;

    @XmlElement(name = "title")
    private String title;

    @XmlElement(name = "description")
    private String description;

    @XmlElement(name = "writtenDate")
    @XmlJavaTypeAdapter(DateISO8601Adapter.class)
    private Date writtenDate;

    private Set<Author> authors = new HashSet<Author>();

    public Set<Author> getAuthors() {

        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Book(BookEntity bookEntity) {

        this.book_id = bookEntity.getBook_id();
        this.title = bookEntity.getTitle();
        this.description = bookEntity.getDescription();
        this.writtenDate = bookEntity.getWrittenDate();


        for (AuthorEntity ae : bookEntity.getAuthors()) {
            this.getAuthors().add(new Author(ae.getAuthor_id(), ae.getName(),ae.getAdress(),ae.getBornDate()));
        }
    }

    public Book(String title, String description, Date writtenDate) {

        this.title = title;
        this.description = description;
        this.writtenDate = writtenDate;
    }

    public Book(long book_id ,String title, String description, Date writtenDate) {

        this.book_id = book_id;
        this.title = title;
        this.description = description;
        this.writtenDate = writtenDate;
    }

    public Book() {
    }

    public Date getWrittenDate() {

        return writtenDate;
    }

    public void setWrittenDate(Date writtenDate) {

        this.writtenDate = writtenDate;
    }

    public Long getBook_id() {

        return book_id;
    }

    public void setBook_id(Long book_id) {

        this.book_id = book_id;
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

}
