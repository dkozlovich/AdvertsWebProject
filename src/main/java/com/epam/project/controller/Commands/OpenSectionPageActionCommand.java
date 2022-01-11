package com.epam.project.controller.Commands;

import com.epam.project.ConfigurationManager;
import com.epam.project.InstanceProvider;
import com.epam.project.controller.ActionCommand;
import com.epam.project.exception.ServiceException;
import com.epam.project.service.AdvertService;
import com.epam.project.service.SectionService;
import com.epam.project.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

public class OpenSectionPageActionCommand implements ActionCommand {

    private UserService userService = InstanceProvider.getUserServiceImpl();

    private AdvertService advertService = InstanceProvider.getAdvertServiceImpl();

    private SectionService sectionService = InstanceProvider.getSectionServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        int sectionID = Integer.parseInt(request.getParameter("sectionID"));
        try {
            request.getSession().setAttribute("advertsOfSection", advertService.getBySectionId(sectionID));
            request.getSession().setAttribute("sectionName", sectionService.getById(sectionID).get().getName());
//            userService.setSessionAttributes(request, login);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return ConfigurationManager.getProperty("path.page.section");
    }
}
