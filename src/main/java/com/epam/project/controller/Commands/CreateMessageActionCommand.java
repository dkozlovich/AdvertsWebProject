package com.epam.project.controller.Commands;

import com.epam.project.InstanceProvider;
import com.epam.project.controller.ActionCommand;
import com.epam.project.exception.ServiceException;
import com.epam.project.service.MessageService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateMessageActionCommand implements ActionCommand {

    private MessageService messageService = InstanceProvider.getMessageServiceImpl();

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        int recordsPerPage = 3;
            int advertId = Integer.parseInt(request.getParameter("advertID"));
            int userId = Integer.parseInt(request.getParameter("userID"));
            String content = request.getParameter("content");
            try {
                if (request.getSession().getAttribute("currentUser") != null) {
                    messageService.createMessage(content, userId, advertId);
                    int totalMessagesNumber = messageService.getTotalMessagesNumber(advertId);
                    int totalPagesNumber = (int) Math.ceil(totalMessagesNumber * 1.0 / recordsPerPage);
                    page = "/Controller?command=OPEN_ADVERT&id=" + advertId + "&page=" + totalPagesNumber;
                }
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        return page;
    }
}
