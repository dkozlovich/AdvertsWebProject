package com.epam.project.controller.Commands;

import com.epam.project.ConfigurationManager;
import com.epam.project.InstanceProvider;
import com.epam.project.controller.ActionCommand;
import com.epam.project.dto.MessageDTO;
import com.epam.project.exception.ServiceException;
import com.epam.project.model.Image;
import com.epam.project.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class OpenAdvertPageActionCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger();

    private AdvertService advertService = InstanceProvider.getAdvertServiceImpl();

    private SectionService sectionService = InstanceProvider.getSectionServiceImpl();

    private UserService userService = InstanceProvider.getUserServiceImpl();

    private MessageService messageService = InstanceProvider.getMessageServiceImpl();

    private ImageService imageService = InstanceProvider.getImageServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws IOException {
        int page = 1;
        int recordsPerPage = 3;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            List<MessageDTO> messages = messageService.findByAdvertId(id,(page-1) * recordsPerPage, recordsPerPage);
            int totalMessagesNumber = messageService.getTotalMessagesNumber(id);
            int totalPagesNumber = (int) Math.ceil(totalMessagesNumber * 1.0 / recordsPerPage);
            List<Image> images = imageService.getByAdvertId(id);
            List<String> imagesAsBase64 = new ArrayList<>();
            for (Image item : images) {
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                ImageIO.write(item.getAdvertImage(), "jpg", output);
                String imageAsBase64 = Base64.getEncoder().encodeToString(output.toByteArray());
                imagesAsBase64.add(imageAsBase64);
            }
            request.setAttribute("images", imagesAsBase64);
            request.setAttribute("advert", advertService.getById(id));
            request.setAttribute("sectionName", sectionService.getById(advertService.getById(id).getSectionId()).get().getName());
            request.setAttribute("userName", userService.getById(advertService.getById(id).getUserId()).getUsername());
            request.setAttribute("messages", messages);
            request.setAttribute("totalPagesNumber", totalPagesNumber);
            request.setAttribute("currentPage", page);
        } catch (ServiceException e) {
            LOGGER.error(e);
            e.printStackTrace();
        }
        return ConfigurationManager.getProperty("path.page.advert");
    }
}
