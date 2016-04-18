package org.bookStore.Resource;

import org.bookStore.errorhandling.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.bookStore.service.BookService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by csaba.szekely on 4/12/2016.
 */
@Component
@Path("/books")
public class BookResource {
    @Autowired
    private BookService bookService;

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.TEXT_HTML })
    public Response createBook(Book book) throws AppException {

        Long createBookId = bookService.createBook(book);

        return Response.status(Response.Status.CREATED)
                .entity("A new book has been created")
                .header("Location",
                        "http://localhost:8080/BookStore/books/"
                                + String.valueOf(createBookId)).build();
    }

    @POST
    @Path("list")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response createBooks(List<Book> books) throws AppException {

        bookService.createboks(books);

        return Response.status(Response.Status.CREATED)
                .entity("List of books was successfully created").build();
    }


    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Book> getBooks(@QueryParam("title") String title)throws IOException,	AppException {
        List<Book> result = new ArrayList<Book>();


        result = bookService.getBooks(title);

        return result;
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getBookById(@PathParam("id") Long id, @QueryParam("detailed") boolean detailed)
            throws IOException,	AppException {

        Book bookById = bookService.getBookById(id);

        return Response.status(200)
                .entity(bookById, detailed ? new Annotation[]{BookDetailedView.Factory.get()} : new Annotation[0])
                .header("Access-Control-Allow-Headers", "X-extra-header")
                .allow("OPTIONS").build();
    }


    @PUT
    @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.TEXT_HTML })
    public Response putBookById(@PathParam("id") Long id, Book book)
            throws AppException {

        Book bookById = bookService.verifyBookExistenceById(id);

        if (bookById == null) {

            Long createPodcastId = bookService.createBook(book);
            return Response
                    .status(Response.Status.CREATED)
                    .entity("A new book has been created AT THE LOCATION you specified")
                    .header("Location",
                            "http://localhost:8888/demo-rest-jersey-spring/books/"
                                    + String.valueOf(createPodcastId)).build();
        } else {

            bookService.updateFullyBook(book);

            return Response
                    .status(Response.Status.OK)
                    .entity("The book you specified has been fully updated created AT THE LOCATION you specified")
                    .header("Location",
                            "http://localhost:8888/demo-rest-jersey-spring/books/"
                                    + String.valueOf(id)).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces({ MediaType.TEXT_HTML })
    public Response deleteBookById(@PathParam("id") Long id) {

        bookService.deleteBookById(id);

        return Response.status(Response.Status.NO_CONTENT)
                .entity("Book successfully removed from database").build();
    }

}
