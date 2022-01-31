package com.epam.project.controller.Commands;

import com.epam.project.ConfigurationManager;
import com.epam.project.InstanceProvider;
import com.epam.project.controller.ActionCommand;
import com.epam.project.dto.AdvertCreateDTO;
import com.epam.project.dto.SectionDTO;
import com.epam.project.exception.ServiceException;
import com.epam.project.model.Section;
import com.epam.project.service.AdvertService;
import com.epam.project.service.SectionService;
import com.epam.project.service.UserService;
import com.epam.project.util.DTOMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

public class CreateAdvertActionCommand implements ActionCommand {

    private AdvertService advertService = InstanceProvider.getAdvertServiceImpl();

    private UserService userService = InstanceProvider.getUserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        int sectionId = Integer.parseInt(request.getParameter("sectionID"));
        String name = request.getParameter("name");
        String content = request.getParameter("content");
        double cost = Double.parseDouble(request.getParameter("cost"));
        int userId = Integer.parseInt(request.getParameter("userID"));
        AdvertCreateDTO dto = new AdvertCreateDTO();
        dto.setSectionId(sectionId).setName(name).setContent(content).setCost(cost).setUserId(userId);
        try {
            if (request.getSession().getAttribute("currentUser") != null) {
                advertService.createAdvert(dto);
                userService.setSessionAttributes(request);
                page = ConfigurationManager.getProperty("path.page.main");
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
