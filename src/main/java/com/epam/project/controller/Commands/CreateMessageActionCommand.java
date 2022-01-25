package com.epam.project.controller.Commands;

import com.epam.project.ConfigurationManager;
import com.epam.project.InstanceProvider;
import com.epam.project.controller.ActionCommand;
import com.epam.project.exception.ServiceException;
import com.epam.project.model.Message;
import com.epam.project.service.MessageService;
import jakarta.servlet.http.HttpServletRequest;

public class CreateMessageActionCommand implements ActionCommand {

    private MessageService messageService = InstanceProvider.getMessageServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        int advertId = Integer.parseInt(request.getParameter("advertID"));
        int userId = Integer.parseInt(request.getParameter("userID"));
        String content = request.getParameter("content");
        try {
            if (request.getSession().getAttribute("currentUser") != null) {
                messageService.createMessage(content, userId, advertId);
                request.getSession().setAttribute("messages", messageService.findByAdvertId(advertId));
                page = ConfigurationManager.getProperty("path.page.advert");
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
