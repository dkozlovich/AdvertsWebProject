package com.epam.project.controller.command.impl;

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
        int recordsPerPage = 3;
        AdvertUpdateDTO dto = new AdvertUpdateDTO();
        int id = Integer.parseInt(request.getParameter("id"));
        dto.setId(id);
        dto.setSectionId(Integer.parseInt(request.getParameter("sectionID")));
        dto.setName(request.getParameter("name"));
        dto.setContent(request.getParameter("content"));
        dto.setCost(Double.parseDouble(request.getParameter("cost")));
        try {
            advertService.updateAdvert(dto);
            int totalMessagesNumber = messageService.getTotalMessagesNumber(id);
            int totalPagesNumber = (int) Math.ceil(totalMessagesNumber * 1.0 / recordsPerPage);
            request.setAttribute("advert", advertService.getById(id));
            request.setAttribute("sectionName", sectionService.getById(advertService.getById(id).getSectionId()).get().getName());
            request.setAttribute("userName", userService.getById(advertService.getById(id).getUserId()).getUsername());
            request.setAttribute("messages", messageService.findByAdvertId(id, 1, 3));
            request.setAttribute("totalPagesNumber", totalPagesNumber);
            request.setAttribute("currentPage",1);
            page = "/Controller?command=OPEN_ADVERT&id="+id;
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
