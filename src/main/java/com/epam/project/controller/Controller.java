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
        Locale locale = (Locale) request.getSession().getAttribute("locale");
        if (locale == null) {
            request.getSession().setAttribute("locale", Locale.forLanguageTag(Locale.getDefault().getLanguage()));
        }
        String command = request.getParameter("command").toUpperCase();
        ActionCommand actionCommand = ActionResolver.defineCommand(command);
        try {
            page = actionCommand.execute(request);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(e.getMessage());
            page = ConfigurationManager.getProperty("path.page.error");
            request.getSession().setAttribute("wrongaction",
                    MessageManager.getProperty("message.wrongaction"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}