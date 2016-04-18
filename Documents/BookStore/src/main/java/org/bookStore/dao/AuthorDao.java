package org.bookStore.dao;

import java.util.List;

/**
 * Created by csaba.szekely on 4/13/2016.
 */
public interface AuthorDao {

    public List<AuthorEntity> getAuthors();

    public void deleteAuthor(long id);

    public AuthorEntity getAuthorById(Long id);

    public long createAuthor(AuthorEntity authorEntity);

    public void updateAuthor(AuthorEntity authorEntity);

    public void deleteAuthor();
}
