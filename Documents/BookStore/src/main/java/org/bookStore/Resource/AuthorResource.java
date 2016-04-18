package org.bookStore.Resource;

import org.bookStore.errorhandling.AppException;
import org.bookStore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Created by csaba.szekely on 4/13/2016.
 */
@Component
@Path("/authors")
public class AuthorResource {

    @Autowired
    private AuthorService authorService;

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.TEXT_HTML })
    public Response createBook(Author author) throws AppException {

        Long createAuthorId = authorService.createAuthor(author);

        return Response.status(Response.Status.CREATED)
                .entity("A new author has been created")
                .header("Location",
                        "http://localhost:8080/BookStore/authors/"
                                + String.valueOf(createAuthorId)).build();
    }

    @POST
    @Path("list")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response createAuthors(List<Author> authors) throws AppException {

        authorService.createAuthors(authors);

        return Response.status(Response.Status.CREATED)
                .entity("List of authors was successfully created").build();
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Author> getAuthors(
            @QueryParam("orderByInsertionDate") String orderByInsertionDate,
            @QueryParam("numberDaysToLookBack") Integer numberDaysToLookBack)
            throws IOException,	AppException {

        return authorService.getAuthors();
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getBookById(@PathParam("id") Long id, @QueryParam("detailed") boolean detailed)
            throws IOException,	AppException {

        Author authorById = authorService.getAuthorById(id);

        return Response.status(200)
                .entity(authorById, detailed ? new Annotation[]{BookDetailedView.Factory.get()} : new Annotation[0])
                .header("Access-Control-Allow-Headers", "X-extra-header")
                .allow("OPTIONS").build();
    }

    @PUT
    @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.TEXT_HTML })
    public Response putBookById(@PathParam("id") Long id, Author author)
            throws AppException {

        Author authorById = authorService.verifyAuthorExistenceById(id);

        if (authorById == null) {

            Long createPodcastId = authorService.createAuthor(author);
            return Response
                    .status(Response.Status.CREATED)
                    .entity("A new author has been created AT THE LOCATION you specified")
                    .header("Location",
                            "http://localhost:8888/demo-rest-jersey-spring/authors/"
                                    + String.valueOf(createPodcastId)).build();
        } else {

            authorService.updateFullyAuthor(author);

            return Response
                    .status(Response.Status.OK)
                    .entity("The author you specified has been fully updated created AT THE LOCATION you specified")
                    .header("Location",
                            "http://localhost:8888/demo-rest-jersey-spring/authors/"
                                    + String.valueOf(id)).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces({ MediaType.TEXT_HTML })
    public Response deleteAuthorById(@PathParam("id") Long id) {

        authorService.deleteAuthorById(id);

        return Response.status(Response.Status.NO_CONTENT)
                .entity("Author successfully removed from database").build();
    }
}
