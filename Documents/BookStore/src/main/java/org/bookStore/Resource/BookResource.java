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
        Long createPodcastId = bookService.createBook(book);

        return Response.status(Response.Status.CREATED)// 201
                .entity("A new book has been created")
                .header("Location",
                        "http://localhost:8080/BookStore/books/"
                                + String.valueOf(createPodcastId)).build();
    }


    @GET
    //@Compress //can be used only if you want to SELECTIVELY enable compression at the method level. By using the EncodingFilter everything is compressed now.
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Book> getPodcasts(
            @QueryParam("orderByInsertionDate") String orderByInsertionDate,
            @QueryParam("numberDaysToLookBack") Integer numberDaysToLookBack)
            throws IOException,	AppException {
        List<Book> podcasts = bookService.getBooks();
        return podcasts;
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


}
