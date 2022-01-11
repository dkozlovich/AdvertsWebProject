package com.epam.project.exception;

public class EntityAlreadyExistException extends ServiceException {

    public EntityAlreadyExistException() {
    }

    public EntityAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
