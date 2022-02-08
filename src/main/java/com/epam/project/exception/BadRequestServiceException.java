package com.epam.project.exception;

public class BadRequestServiceException extends ServiceException{
    public BadRequestServiceException() {
    }

    public BadRequestServiceException(String message) {
        super(message);
    }

    public BadRequestServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestServiceException(Throwable cause) {
        super(cause);
    }
}
