package com.epam.project.controller.command.impl;

import com.epam.project.ConfigurationManager;
import com.epam.project.controller.command.ActionCommand;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Locale;

public class ChangeLocaleActionCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.main");
        String locale = request.getParameter("locale");
        request.getSession().setAttribute("locale", Locale.forLanguageTag(locale));
        return page;
    }
}
