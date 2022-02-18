package com.epam.project.controller.command.impl;

import com.epam.project.ConfigurationManager;
import com.epam.project.InstanceProvider;
import com.epam.project.controller.command.ActionCommand;
import com.epam.project.exception.ServiceException;
import com.epam.project.service.UserService;

import jakarta.servlet.http.HttpServletRequest;


public class OpenAdminPageActionCommand implements ActionCommand {

    private UserService userService = InstanceProvider.getUserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.admin");
        try {
            userService.setSessionAttributes(request);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
