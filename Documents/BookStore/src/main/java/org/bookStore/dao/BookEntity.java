package org.bookStore.dao;

import org.apache.commons.beanutils.BeanUtils;
import org.bookStore.Resource.Book;

import javax.persistence.*;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * Created by csaba.szekely on 4/12/2016.
 */
@Entity
@Table(name="books")
public class BookEntity implements Serializable {

    @Id
    @GeneratedValue()
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="written_date")
    private Date writtenDate;

    public BookEntity(){}

    public BookEntity(long id, String title, String description, Date writtenDate){

        this.id = id;
        this.title = title;
        this.description = description;
        this.writtenDate = writtenDate;
    }

    public BookEntity(Book book){
        try {
            BeanUtils.copyProperties(this, book);
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
