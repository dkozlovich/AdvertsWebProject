package com.epam.project.controller.command.impl;

import com.epam.project.ConfigurationManager;
import com.epam.project.InstanceProvider;
import com.epam.project.controller.command.ActionCommand;
import com.epam.project.exception.ServiceException;
import com.epam.project.service.AdvertService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SearchAdvertsActionCommand implements ActionCommand {

    AdvertService advertService = InstanceProvider.getAdvertServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws ServiceException, ServletException, IOException {
        int page = 1;
        int recordsPerPage = 10;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        String key = request.getParameter("searchKey");
        String dateFrom = request.getParameter("dateFrom");
        String dateTo = request.getParameter("dateTo");
        String sectionId = request.getParameter("sectionID");
        Map<String, String> searchParameters = new HashMap<>();
        searchParameters.put("key", key);
        searchParameters.put("dateFrom", dateFrom);
        searchParameters.put("dateTo", dateTo);
        searchParameters.put("sectionId", sectionId);
        int totalAdvertsNumber = advertService.search(key, dateFrom, dateTo, sectionId,0, Integer.MAX_VALUE).size();
        int totalPagesNumber = (int) Math.ceil(totalAdvertsNumber * 1.0 / recordsPerPage);
        try {
            request.setAttribute("searchParameters", searchParameters);
            request.setAttribute("totalPagesNumber", totalPagesNumber);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalAdvertsNumber", totalAdvertsNumber);
            request.setAttribute("advertsSearch", advertService.search(key, dateFrom, dateTo, sectionId,(page-1) * recordsPerPage, recordsPerPage));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return ConfigurationManager.getProperty("path.page.advertsSearch");
    }
}
