package com.epam.project.controller.command.impl;

import com.epam.project.InstanceProvider;
import com.epam.project.controller.ActionCommand;
import com.epam.project.exception.ServiceException;
import com.epam.project.service.ImageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class SaveImageActionCommand implements ActionCommand {

    ImageService imageService = InstanceProvider.getImageServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws ServiceException, ServletException, IOException {
        int advertId = Integer.parseInt(request.getParameter("advertID"));
        Part filePart = request.getPart("image");
        InputStream fileContent = filePart.getInputStream();
        BufferedImage bufferedImage = ImageIO.read(fileContent);
        imageService.add(bufferedImage, advertId);
        return "/Controller?command=OPEN_ADVERT&id="+advertId;
    }
}
