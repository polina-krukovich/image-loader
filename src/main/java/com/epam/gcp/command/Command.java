package com.epam.gcp.command;

import com.epam.gcp.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}
