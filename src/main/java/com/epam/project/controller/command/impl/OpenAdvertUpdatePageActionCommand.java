package com.epam.project.controller.command.impl;

import com.epam.project.ConfigurationManager;
import com.epam.project.InstanceProvider;
import com.epam.project.controller.ActionCommand;
import com.epam.project.exception.ServiceException;
import com.epam.project.service.AdvertService;
import com.epam.project.service.SectionService;
import jakarta.servlet.http.HttpServletRequest;

public class OpenAdvertUpdatePageActionCommand implements ActionCommand {

    private AdvertService advertService = InstanceProvider.getAdvertServiceImpl();

    private SectionService sectionService = InstanceProvider.getSectionServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("advert", advertService.getById(id));
        request.setAttribute("sectionName", sectionService.getById(advertService.getById(id).getSectionId()).get().getName());
        return ConfigurationManager.getProperty("path.page.advertUpdate");
    }
}
