package com.epam.gcp.command.impl;

import com.epam.gcp.command.Command;
import com.epam.gcp.command.exception.CommandException;
import com.epam.gcp.entity.Image;
import com.epam.gcp.service.ImageService;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListCommand implements Command {
    private final Logger logger = LogManager.getLogger(getClass());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        ImageService imageService = ImageService.getInstance();
        Image[] images = imageService.list();
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            Gson gson = new Gson();
            String jsonString = gson.toJson(images);
            response.getWriter().print(jsonString);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new CommandException(e.getMessage());
        }
    }
}
