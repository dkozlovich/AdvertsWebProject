package com.epam.project.controller.command.impl;

import com.epam.project.ConfigurationManager;
import com.epam.project.InstanceProvider;
import com.epam.project.controller.command.ActionCommand;
import com.epam.project.exception.ServiceException;
import com.epam.project.service.AdvertService;

import jakarta.servlet.http.HttpServletRequest;

public class OpenAdvertsOfUserActionCommand implements ActionCommand {

    private AdvertService advertService = InstanceProvider.getAdvertServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        int userID = Integer.parseInt(request.getParameter("userID"));
        try {
            request.setAttribute("advertsOfUser", advertService.getAllByUser(userID));
            request.setAttribute("userName", request.getParameter("userName"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return ConfigurationManager.getProperty("path.page.advertsOfUser");
    }
}
