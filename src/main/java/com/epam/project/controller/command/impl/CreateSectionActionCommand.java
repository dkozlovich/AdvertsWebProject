package com.epam.project.controller.command.impl;

import com.epam.project.ConfigurationManager;
import com.epam.project.InstanceProvider;
import com.epam.project.controller.command.ActionCommand;
import com.epam.project.dto.SectionCreateDTO;
import com.epam.project.exception.ServiceException;
import com.epam.project.service.SectionService;

import jakarta.servlet.http.HttpServletRequest;

public class CreateSectionActionCommand implements ActionCommand {

    private SectionService sectionService = InstanceProvider.getSectionServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        String name = request.getParameter("name");
        SectionCreateDTO dto = new SectionCreateDTO();
        dto.setName(name);
        if (request.getSession().getAttribute("currentUser") != null) {
            sectionService.createSection(dto);
            request.getSession().setAttribute("sections", sectionService.getAll());
            page = ConfigurationManager.getProperty("path.page.admin");

        }
        return page;
    }
}
