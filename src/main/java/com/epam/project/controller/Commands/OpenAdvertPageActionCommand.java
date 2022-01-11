package com.epam.project.controller.Commands;

import com.epam.project.ConfigurationManager;
import com.epam.project.InstanceProvider;
import com.epam.project.controller.ActionCommand;
import com.epam.project.exception.ServiceException;
import com.epam.project.service.AdvertService;

import com.epam.project.service.SectionService;
import com.epam.project.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

public class OpenAdvertPageActionCommand implements ActionCommand {

    private AdvertService advertService = InstanceProvider.getAdvertServiceImpl();

    private SectionService sectionService = InstanceProvider.getSectionServiceImpl();

    private UserService userService = InstanceProvider.getUserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            request.getSession().setAttribute("advert", advertService.getById(id));
            request.getSession().setAttribute("sectionName", sectionService.getById(advertService.getById(id).getSectionId()).get().getName());
            request.getSession().setAttribute("userName", userService.getById(advertService.getById(id).getUserId()).getUsername());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return ConfigurationManager.getProperty("path.page.advert");
    }
}
