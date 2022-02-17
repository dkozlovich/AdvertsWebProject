package com.epam.project.controller.command.impl;

import com.epam.project.controller.ActionCommand;
import com.epam.project.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class ChangeSortTypeActionCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws ServiceException, ServletException, IOException {
        int sectionId = Integer.parseInt(request.getParameter("sectionID"));
        String sortType = request.getParameter("sortType");
        request.getSession().setAttribute("sortType", sortType);
        return "/Controller?command=OPEN_SECTION&sectionID="+sectionId;
    }
}
