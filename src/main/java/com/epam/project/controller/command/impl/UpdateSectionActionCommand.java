package com.epam.project.controller.command.impl;

import com.epam.project.ConfigurationManager;
import com.epam.project.InstanceProvider;
import com.epam.project.controller.command.ActionCommand;
import com.epam.project.dto.SectionUpdateDTO;
import com.epam.project.exception.ServiceException;
import com.epam.project.service.SectionService;

import jakarta.servlet.http.HttpServletRequest;

public class UpdateSectionActionCommand implements ActionCommand {

    private SectionService sectionService = InstanceProvider.getSectionServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page;
        SectionUpdateDTO dto = new SectionUpdateDTO();
        dto.setId(Integer.parseInt(request.getParameter("id")));
        dto.setName(request.getParameter("name"));
        sectionService.update(dto);
        request.getSession().setAttribute("sections", sectionService.getAll());
        page = ConfigurationManager.getProperty("path.page.admin");
        return page;
    }
}
