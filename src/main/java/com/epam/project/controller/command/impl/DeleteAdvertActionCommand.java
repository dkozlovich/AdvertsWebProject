package com.epam.project.controller.command.impl;

import com.epam.project.InstanceProvider;
import com.epam.project.controller.command.ActionCommand;
import com.epam.project.exception.ServiceException;
import com.epam.project.service.AdvertService;

import com.epam.project.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

public class DeleteAdvertActionCommand implements ActionCommand {

    private AdvertService advertService = InstanceProvider.getAdvertServiceImpl();

    private UserService userService = InstanceProvider.getUserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        int id = Integer.parseInt(request.getParameter("id"));
        String sectionID = request.getParameter("sectionID");
        try {
            if (request.getSession().getAttribute("currentUser") != null) {
                advertService.deleteAdvert(id);
                userService.setSessionAttributes(request);
                page = "/Controller?command=OPEN_SECTION&sectionID=" + sectionID;
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
