package com.blog.springbootwebflux.model;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : CustomException
 * @since : 24. 12. 28.
 */
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}