package org.bookStore.interceptors;

import javax.ws.rs.NameBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by csaba.szekely on 4/12/2016.
 */
@NameBinding
@Retention(RetentionPolicy.RUNTIME)
public @interface Compress {}


