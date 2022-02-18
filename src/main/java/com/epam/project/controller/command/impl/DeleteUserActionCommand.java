package com.epam.project.controller.command.impl;

import com.epam.project.InstanceProvider;
import com.epam.project.controller.command.ActionCommand;
import com.epam.project.exception.ServiceException;
import com.epam.project.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class DeleteUserActionCommand implements ActionCommand {

    UserService userService = InstanceProvider.getUserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws ServiceException, ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("userID"));
            userService.deleteUser(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "/Controller?command=OPEN_ADMIN_PAGE";
    }
}
