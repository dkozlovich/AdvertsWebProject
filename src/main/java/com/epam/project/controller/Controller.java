package com.epam.project.controller;

import com.epam.project.ConfigurationManager;
import com.epam.project.controller.command.ActionCommand;
import com.epam.project.exception.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Locale;

public class Controller extends HttpServlet {



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page;
        Locale locale = (Locale) request.getSession().getAttribute("locale");
        String sortType = (String) request.getSession().getAttribute("sortType");
        if (locale == null) {
            request.getSession().setAttribute("locale", Locale.forLanguageTag(Locale.getDefault().getLanguage()));
        }
        if (sortType == null) {
            request.getSession().setAttribute("sortType", "MODIFIED_DESC");
        }
        String command = request.getParameter("command").toUpperCase();
        ActionCommand actionCommand = ActionResolver.defineCommand(command);
        try {
            page = actionCommand.execute(request);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } catch (ServiceException | ServletException e) {
            page = ConfigurationManager.getProperty("path.page.error");
            request.getSession().setAttribute("error", e);
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}