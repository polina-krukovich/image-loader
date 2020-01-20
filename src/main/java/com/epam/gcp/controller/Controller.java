package com.epam.gcp.controller;

import com.epam.gcp.command.Command;
import com.epam.gcp.command.exception.CommandException;
import com.epam.gcp.command.factory.CommandFactory;
import com.epam.gcp.entity.Image;
import com.epam.gcp.service.ImageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Controller", urlPatterns = {"/create", "/list", "/delete"})
public class Controller extends HttpServlet {
    private final Logger logger = LogManager.getLogger(getClass());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        Command command = CommandFactory.createCommand(request.getRequestURI());
        if (null != command) {
            try {
                command.execute(request, response);
            } catch (CommandException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
