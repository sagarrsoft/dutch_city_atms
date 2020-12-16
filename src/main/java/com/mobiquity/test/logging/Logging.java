package com.mobiquity.test.logging;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;


@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface Logging {

    /**
     * set the logging message literally
     *
     * @return the logged message
     */
    String message() default "";

}
