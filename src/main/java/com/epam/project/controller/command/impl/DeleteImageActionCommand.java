package com.epam.project.controller.command.impl;

import com.epam.project.InstanceProvider;
import com.epam.project.controller.ActionCommand;
import com.epam.project.exception.ServiceException;
import com.epam.project.service.ImageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class DeleteImageActionCommand implements ActionCommand {

    ImageService imageService = InstanceProvider.getImageServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws ServiceException, ServletException, IOException {
        int advertId = Integer.parseInt(request.getParameter("advertID"));
        int imageId = Integer.parseInt(request.getParameter("imageID"));
        imageService.delete(imageId);
        return "/Controller?command=OPEN_ADVERT&id="+advertId;
    }
}
