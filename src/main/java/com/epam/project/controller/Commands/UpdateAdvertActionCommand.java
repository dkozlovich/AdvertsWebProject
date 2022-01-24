package com.epam.project.controller.Commands;

import com.epam.project.ConfigurationManager;
import com.epam.project.InstanceProvider;
import com.epam.project.controller.ActionCommand;
import com.epam.project.dto.AdvertUpdateDTO;
import com.epam.project.exception.ServiceException;
import com.epam.project.service.AdvertService;

import com.epam.project.service.MessageService;
import com.epam.project.service.SectionService;
import com.epam.project.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

public class UpdateAdvertActionCommand implements ActionCommand {

    private AdvertService advertService = InstanceProvider.getAdvertServiceImpl();

    private SectionService sectionService = InstanceProvider.getSectionServiceImpl();

    private UserService userService = InstanceProvider.getUserServiceImpl();

    private MessageService messageService = InstanceProvider.getMessageServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        AdvertUpdateDTO dto = new AdvertUpdateDTO();
        int id = Integer.parseInt(request.getParameter("id"));
        dto.setId(id);
        dto.setSectionId(Integer.parseInt(request.getParameter("sectionID")));
        dto.setName(request.getParameter("name"));
        dto.setContent(request.getParameter("content"));
        dto.setCost(Double.parseDouble(request.getParameter("cost")));
        try {
            advertService.updateAdvert(dto);
            request.getSession().setAttribute("advert", advertService.getById(id));
            request.getSession().setAttribute("sectionName", sectionService.getById(advertService.getById(id).getSectionId()).get().getName());
            request.getSession().setAttribute("userName", userService.getById(advertService.getById(id).getUserId()).getUsername());
            request.getSession().setAttribute("messages", messageService.getByAdvertId(id));
            page = ConfigurationManager.getProperty("path.page.advert");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
