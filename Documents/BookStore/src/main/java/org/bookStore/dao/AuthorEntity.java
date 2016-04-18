package org.bookStore.dao;

import org.bookStore.Resource.Author;
import org.bookStore.Resource.Book;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="authors")
public class AuthorEntity implements Serializable {

    private static final long serialVersionUID = 6465113631863669016L;

    @Id
    @GeneratedValue
    @Column(name="author_id")
    private Long author_id;

    @Column(name="name")
    private String name;

    @Column(name="adress")
    private String adress;

    @Column(name="born_date")
    private Date bornDate;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "authors")
    private Set<BookEntity> books = new HashSet<BookEntity>();



    public Set<BookEntity> getBooks() {

        return books;
    }


    public void setBooks(Set<BookEntity> books) {

        this.books = books;
    }

    public AuthorEntity(String name, String adress, Date bornDate){

        this.name = name;
        this.adress = adress;
        this.bornDate = bornDate;
    }

    public AuthorEntity(long author_id,String name, String adress, Date bornDate){

        this.author_id = author_id;
        this.name = name;
        this.adress = adress;
        this.bornDate = bornDate;
    }
    public AuthorEntity(Author author){

        this.author_id = author.getAuthor_id();
        this.name = author.getName();
        this.adress = author.getAdress();
        this.bornDate = author.getBornDate();
        for (Book book : author.getBooks() ){

            this.getBooks().add(new BookEntity( book.getBook_id(), book.getTitle(), book.getDescription(), book.getWrittenDate()));
        }
    }

    public AuthorEntity(){}

    public Date getBornDate() {

        return bornDate;
    }

    public void setBornDate(Date bornDate) {

        this.bornDate = bornDate;
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

    public Long getAuthor_id() {

        return author_id;
    }

    public void setAuthor_id(Long id) {

        this.author_id = id;
    }
}
