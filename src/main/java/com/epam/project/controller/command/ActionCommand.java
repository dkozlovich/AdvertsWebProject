package com.epam.project.controller.command;

import com.epam.project.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public interface ActionCommand {
    String execute(HttpServletRequest request) throws ServiceException, ServletException, IOException;
}
