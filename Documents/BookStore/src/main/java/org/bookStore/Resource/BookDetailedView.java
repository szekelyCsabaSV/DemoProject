package org.bookStore.Resource;

import org.glassfish.hk2.api.AnnotationLiteral;
import org.glassfish.jersey.message.filtering.EntityFiltering;

import java.lang.annotation.*;

/**
 * Created by csaba.szekely on 4/12/2016.
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EntityFiltering
public @interface BookDetailedView {

    public static class Factory extends AnnotationLiteral<BookDetailedView> implements BookDetailedView {

        /**
         *
         */


        private Factory() {
        }

        public static BookDetailedView get() {
            return new Factory();
        }
    }
}
