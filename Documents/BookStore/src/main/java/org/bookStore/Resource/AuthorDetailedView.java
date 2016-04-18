package org.bookStore.Resource;

import org.glassfish.hk2.api.AnnotationLiteral;
import org.glassfish.jersey.message.filtering.EntityFiltering;

import java.lang.annotation.*;

/**
 * Created by csaba.szekely on 4/13/2016.
 */

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EntityFiltering
public @interface AuthorDetailedView {

    public static class Factory extends AnnotationLiteral<AuthorDetailedView> implements AuthorDetailedView {

        private Factory() {
        }

        public static AuthorDetailedView get() {
            return new Factory();
        }
    }
}
