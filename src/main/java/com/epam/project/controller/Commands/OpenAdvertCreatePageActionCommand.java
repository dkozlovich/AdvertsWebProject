package com.epam.project.controller.Commands;

import com.epam.project.ConfigurationManager;
import com.epam.project.InstanceProvider;
import com.epam.project.controller.ActionCommand;
import com.epam.project.exception.ServiceException;
import com.epam.project.service.SectionService;
import jakarta.servlet.http.HttpServletRequest;

public class OpenAdvertCreatePageActionCommand implements ActionCommand {

    private SectionService sectionService = InstanceProvider.getSectionServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String requestParameter = request.getParameter("sectionID");
        if (!requestParameter.equals("")) {
            int sectionID = Integer.parseInt(requestParameter);
            try {
                request.setAttribute("sectionName", sectionService.getById(sectionID).get().getName());
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("sectionName","");
        }
        return ConfigurationManager.getProperty("path.page.advertCreate");
    }
}
