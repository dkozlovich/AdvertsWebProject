package com.epam.project.controller;

import com.epam.project.ConfigurationManager;
import com.epam.project.MessageManager;
import jakarta.servlet.RequestDispatcher;
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
        String page = null;
        request.getSession().setAttribute("locale", Locale.forLanguageTag("ru"));
        String command = request.getParameter("command").toUpperCase();
        ActionCommand actionCommand = ActionResolver.defineCommand(command);
        try {
            page = actionCommand.execute(request);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            page = ConfigurationManager.getProperty("path.page.error");
            request.getSession().setAttribute("wrongaction",
                    MessageManager.getProperty("message.wrongaction"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}