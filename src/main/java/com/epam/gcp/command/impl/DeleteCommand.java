package com.epam.gcp.command.impl;

import com.epam.gcp.command.Command;
import com.epam.gcp.command.exception.CommandException;
import com.epam.gcp.service.ImageService;
import com.google.cloud.storage.BlobId;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String jsonString = request.getParameter("blobId");
        Gson gson = new Gson();
        BlobId blobId = gson.fromJson(jsonString, BlobId.class);
        ImageService.getInstance().delete(blobId);
    }
}
