package com.epam.project.controller.command.impl;

import com.epam.project.ConfigurationManager;
import com.epam.project.controller.ActionCommand;

import jakarta.servlet.http.HttpServletRequest;

public class EmptyActionCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
}
