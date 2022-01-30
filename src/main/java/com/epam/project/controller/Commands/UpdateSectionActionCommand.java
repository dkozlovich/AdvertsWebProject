package com.epam.project.controller.Commands;

import com.epam.project.ConfigurationManager;
import com.epam.project.InstanceProvider;
import com.epam.project.controller.ActionCommand;
import com.epam.project.dto.SectionUpdateDTO;
import com.epam.project.exception.ServiceException;
import com.epam.project.service.SectionService;

import jakarta.servlet.http.HttpServletRequest;

public class UpdateSectionActionCommand implements ActionCommand {

    private SectionService sectionService = InstanceProvider.getSectionServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        SectionUpdateDTO dto = new SectionUpdateDTO();
        dto.setId(Integer.parseInt(request.getParameter("id")));
        dto.setName(request.getParameter("name"));
        try {
            sectionService.update(dto);
            request.getSession().setAttribute("sections", sectionService.getAll());
            page = ConfigurationManager.getProperty("path.page.admin");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
