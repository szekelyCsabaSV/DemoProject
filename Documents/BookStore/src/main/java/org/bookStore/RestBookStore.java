package org.bookStore;

import org.glassfish.jersey.message.GZipEncoder;
import org.glassfish.jersey.message.filtering.EntityFilteringFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.EncodingFilter;

/**
 * Created by csaba.szekely on 4/12/2016.
 */
public class RestBookStore extends ResourceConfig {

    public RestBookStore() {

        packages("org.bookStore");
        register(EntityFilteringFeature.class);
        EncodingFilter.enableFor(this, GZipEncoder.class);
    }
}
