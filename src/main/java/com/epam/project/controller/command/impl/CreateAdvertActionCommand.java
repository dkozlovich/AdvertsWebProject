package com.epam.project.controller.command.impl;

import com.epam.project.InstanceProvider;
import com.epam.project.controller.command.ActionCommand;
import com.epam.project.dto.AdvertCreateDTO;
import com.epam.project.exception.ServiceException;
import com.epam.project.service.AdvertService;
import jakarta.servlet.http.HttpServletRequest;

public class CreateAdvertActionCommand implements ActionCommand {

    private AdvertService advertService = InstanceProvider.getAdvertServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        int sectionId;
        double cost;
        sectionId = Integer.parseInt(request.getParameter("sectionID"));
        cost = Double.parseDouble(request.getParameter("cost"));
        String name = request.getParameter("name");
        String content = request.getParameter("content");
        int userId = Integer.parseInt(request.getParameter("userID"));
        AdvertCreateDTO dto = new AdvertCreateDTO();
        dto.setSectionId(sectionId).setName(name).setContent(content).setCost(cost).setUserId(userId);
        if (request.getSession().getAttribute("currentUser") != null) {
            advertService.createAdvert(dto);
            page = "/Controller?command=OPEN_SECTION&sectionID=" + sectionId;
        }
        return page;
    }
}
