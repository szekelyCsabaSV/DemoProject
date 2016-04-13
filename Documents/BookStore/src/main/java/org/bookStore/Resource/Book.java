package org.bookStore.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.bookStore.dao.BookEntity;
import org.bookStore.helpers.DateISO8601Adapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * Created by csaba.szekely on 4/12/2016.
 */

@SuppressWarnings("restriction")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Book implements Serializable {

    @XmlElement(name = "id")
    private Long id;

    @XmlElement(name = "title")
    private String title;

    @XmlElement(name = "description")
    @BookDetailedView
    private String description;

    @XmlElement(name = "writtenDate")
    @XmlJavaTypeAdapter(DateISO8601Adapter.class)
    @BookDetailedView
    private Date writtenDate;

    public Book(BookEntity bookEntity){
        try {
            BeanUtils.copyProperties(this, bookEntity);
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Book(String title, String description){

        this.title = title;
        this.description = description;
    }

    public Book(){}

    public Date getWrittenDate() {

        return writtenDate;
    }

    public void setWrittenDate(Date writtenDate) {

        this.writtenDate = writtenDate;
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

}
