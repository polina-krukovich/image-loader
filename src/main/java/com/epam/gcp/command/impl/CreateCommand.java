package com.epam.gcp.command.impl;

import com.epam.gcp.command.Command;
import com.epam.gcp.command.exception.CommandException;
import com.epam.gcp.service.ImageService;
import com.epam.gcp.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateCommand implements Command {
    private final Logger logger = LogManager.getLogger(getClass());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        ImageService imageService = ImageService.getInstance();
        String url = null;
        try {
            url = imageService.create(request);
//            response.getWriter().write(String.format("<img src=\"%s\">", url));
            response.getWriter().write(url);
        } catch (ServiceException | IOException e) {
            logger.error(e.getMessage());
            throw new CommandException(e.getMessage());
        }
    }
}
