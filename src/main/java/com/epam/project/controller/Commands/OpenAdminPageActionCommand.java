package com.epam.project.controller.Commands;

import com.epam.project.ConfigurationManager;
import com.epam.project.InstanceProvider;
import com.epam.project.controller.ActionCommand;
import com.epam.project.exception.ServiceException;
import com.epam.project.service.UserService;

import jakarta.servlet.http.HttpServletRequest;


public class OpenAdminPageActionCommand implements ActionCommand {

    private UserService userService = InstanceProvider.getUserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String page = ConfigurationManager.getProperty("path.page.admin");
        try {
            userService.setSessionAttributes(request, login);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
