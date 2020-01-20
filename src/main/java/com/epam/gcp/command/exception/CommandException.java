package com.epam.gcp.command.exception;

public class CommandException extends Exception {

    static final long serialVersionUID = -9145700506227633306L;

    public CommandException(String message) {
        super(message);
    }
}
