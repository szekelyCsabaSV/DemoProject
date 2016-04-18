package org.bookStore.Resource;

/**
 * Created by csaba.szekely on 4/13/2016.
 */

import org.bookStore.dao.AuthorEntity;
import org.bookStore.dao.BookEntity;
import org.bookStore.helpers.DateISO8601Adapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("restriction")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Author implements Serializable {

    private static final long serialVersionUID = -2462816238647689170L;
    @XmlElement(name = "author_id")
    private Long author_id;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "adress")
    private String adress;

    @XmlElement(name = "bornDate")
    @XmlJavaTypeAdapter(DateISO8601Adapter.class)
    @AuthorDetailedView
    private Date bornDate;

    public void setBooks(Set<Book> books) {

        this.books = books;
    }

    private Set<Book> books = new HashSet<Book>();

    public Author(AuthorEntity authorEntity){

        this.setAuthor_id(authorEntity.getAuthor_id());
        this.setName(authorEntity.getName());
        this.setAdress(authorEntity.getAdress());
        this.setBornDate(authorEntity.getBornDate());
        this.setAuthor_id(authorEntity.getAuthor_id());

        for (BookEntity be : authorEntity.getBooks()) {

            this.getBooks().add(new Book(be.getBook_id(), be.getTitle(),be.getDescription(),be.getWrittenDate()));
        }
    }

    public Author(String name, String adress, Date bornDate){

        this.name = name;
        this.adress = adress;
        this.bornDate = bornDate;
    }

    public Author(long author_id,String name, String adress, Date bornDate){

        this.author_id = author_id;
        this.name = name;
        this.adress = adress;
        this.bornDate = bornDate;
    }

    public Author(){}

    public Long getAuthor_id() {

        return author_id;
    }

    public void setAuthor_id(Long author_id) {

        this.author_id = author_id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getAdress() {

        return adress;
    }

    public void setAdress(String adress) {

        this.adress = adress;
    }

    public Date getBornDate() {

        return bornDate;
    }

    public void setBornDate(Date bornDate) {

        this.bornDate = bornDate;
    }


    public Set<Book> getBooks() {

        return books;
    }
}
