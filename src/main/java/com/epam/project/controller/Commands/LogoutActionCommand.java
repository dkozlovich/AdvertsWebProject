package com.epam.project.controller.Commands;

import com.epam.project.ConfigurationManager;
import com.epam.project.controller.ActionCommand;

import jakarta.servlet.http.HttpServletRequest;

public class LogoutActionCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.login");
        request.getSession().invalidate();
        return page;
    }
}
