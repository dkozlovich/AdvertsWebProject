package com.epam.project.controller.Commands;

import com.epam.project.ConfigurationManager;
import com.epam.project.InstanceProvider;
import com.epam.project.controller.ActionCommand;
import com.epam.project.exception.ServiceException;
import com.epam.project.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;

public class OpenMainPageActionCommand implements ActionCommand {

    private UserService userService = InstanceProvider.getUserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {

        String page = ConfigurationManager.getProperty("path.page.main");

        try {
            userService.setSessionAttributes(request);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
