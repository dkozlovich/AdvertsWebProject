package com.epam.project.controller.Commands;

import com.epam.project.ConfigurationManager;
import com.epam.project.InstanceProvider;
import com.epam.project.controller.ActionCommand;
import com.epam.project.dto.ImageDTO;
import com.epam.project.dto.MessageDTO;
import com.epam.project.exception.ServiceException;
import com.epam.project.model.Advert;
import com.epam.project.model.Image;
import com.epam.project.service.*;
import com.epam.project.util.DTOMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
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
            List<ImageDTO> imagesDTO = new ArrayList<>();
            for (Image item : images) {
                ImageDTO imageDTO = DTOMapper.mapImage(item);
                imagesDTO.add(imageDTO);
            }
            Advert advert = advertService.getById(id);
            request.setAttribute("imagesDTO", imagesDTO);
            request.setAttribute("advert", advert);
            request.setAttribute("sectionName", sectionService.getById(advert.getSectionId()).get().getName());
            request.setAttribute("userName", userService.getById(advert.getUserId()).getUsername());
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
