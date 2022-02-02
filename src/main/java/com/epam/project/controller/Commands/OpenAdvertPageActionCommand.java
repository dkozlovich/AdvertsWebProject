package com.epam.project.controller.Commands;

import com.epam.project.ConfigurationManager;
import com.epam.project.InstanceProvider;
import com.epam.project.controller.ActionCommand;
import com.epam.project.dto.MessageDTO;
import com.epam.project.exception.ServiceException;
import com.epam.project.service.AdvertService;
import com.epam.project.service.MessageService;
import com.epam.project.service.SectionService;
import com.epam.project.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class OpenAdvertPageActionCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger();

    private AdvertService advertService = InstanceProvider.getAdvertServiceImpl();

    private SectionService sectionService = InstanceProvider.getSectionServiceImpl();

    private UserService userService = InstanceProvider.getUserServiceImpl();

    private MessageService messageService = InstanceProvider.getMessageServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
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
