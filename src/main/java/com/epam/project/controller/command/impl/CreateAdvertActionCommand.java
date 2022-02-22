package com.epam.project.controller.command.impl;

import com.epam.project.InstanceProvider;
import com.epam.project.controller.command.ActionCommand;
import com.epam.project.dto.AdvertCreateDTO;
import com.epam.project.exception.BadRequestServiceException;
import com.epam.project.exception.ServiceException;
import com.epam.project.service.AdvertService;
import com.epam.project.service.SectionService;
import com.epam.project.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateAdvertActionCommand implements ActionCommand {

    private AdvertService advertService = InstanceProvider.getAdvertServiceImpl();

    private static final Logger LOGGER = LogManager.getLogger();

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
