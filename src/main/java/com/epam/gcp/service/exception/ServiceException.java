package com.epam.gcp.service.exception;

public class ServiceException extends Exception {

    static final long serialVersionUID = 949870348879250240L;

    public ServiceException(String message) {
        super(message);
    }
}
