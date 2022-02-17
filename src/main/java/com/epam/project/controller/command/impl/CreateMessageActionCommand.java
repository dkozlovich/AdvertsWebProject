package com.epam.project.controller.command.impl;

import com.epam.project.InstanceProvider;
import com.epam.project.controller.ActionCommand;
import com.epam.project.exception.ServiceException;
import com.epam.project.service.MessageService;
import jakarta.servlet.http.HttpServletRequest;

public class CreateMessageActionCommand implements ActionCommand {

    private MessageService messageService = InstanceProvider.getMessageServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        int recordsPerPage = 3;
        int advertId = Integer.parseInt(request.getParameter("advertID"));
        int userId = Integer.parseInt(request.getParameter("userID"));
        String content = request.getParameter("content");
        if (request.getSession().getAttribute("currentUser") != null) {
            messageService.createMessage(content, userId, advertId);
            int totalMessagesNumber = messageService.getTotalMessagesNumber(advertId);
            int totalPagesNumber = (int) Math.ceil(totalMessagesNumber * 1.0 / recordsPerPage);
            page = "/Controller?command=OPEN_ADVERT&id=" + advertId + "&page=" + totalPagesNumber;
        }
        return page;
    }
}
