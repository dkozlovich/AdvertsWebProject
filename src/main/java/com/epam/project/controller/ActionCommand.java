package com.epam.project.controller;

import com.epam.project.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;

public interface ActionCommand {
    String execute(HttpServletRequest request) throws ServiceException;
}
