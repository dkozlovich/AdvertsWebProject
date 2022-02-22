package com.epam.project.controller.command.impl;

import com.epam.project.ConfigurationManager;
import com.epam.project.InstanceProvider;
import com.epam.project.MessageManager;
import com.epam.project.controller.command.ActionCommand;
import com.epam.project.dto.UserDTO;
import com.epam.project.exception.EntityAlreadyExistException;
import com.epam.project.exception.ServiceException;
import com.epam.project.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

public class RegisterActionCommand implements ActionCommand {
    private UserService userService = InstanceProvider.getUserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDTO dto = new UserDTO();
        dto.setUsername(username);
        dto.setPassword(password);
        try {
            userService.createUser(dto);
            page = ConfigurationManager.getProperty("path.page.login");
        } catch (EntityAlreadyExistException e) {
            request.setAttribute("errorSignUPPassMessage", MessageManager.getProperty("message.signUPerror"));
            page = ConfigurationManager.getProperty("path.page.signUp");
        }
        return page;
    }
}
