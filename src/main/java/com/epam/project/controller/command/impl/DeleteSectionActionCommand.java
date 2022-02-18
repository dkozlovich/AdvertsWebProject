package com.epam.project.controller.command.impl;

import com.epam.project.ConfigurationManager;
import com.epam.project.InstanceProvider;
import com.epam.project.controller.command.ActionCommand;
import com.epam.project.exception.ServiceException;
import com.epam.project.service.SectionService;

import jakarta.servlet.http.HttpServletRequest;

public class DeleteSectionActionCommand implements ActionCommand {

    private SectionService sectionService = InstanceProvider.getSectionServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            if (request.getSession().getAttribute("currentUser") != null) {
                sectionService.deleteSection(id);
                request.getSession().setAttribute("sections", sectionService.getAll());
                page = ConfigurationManager.getProperty("path.page.admin");
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
