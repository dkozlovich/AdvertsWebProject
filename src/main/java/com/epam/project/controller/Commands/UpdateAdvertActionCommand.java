package com.epam.project.controller.Commands;

import com.epam.project.ConfigurationManager;
import com.epam.project.InstanceProvider;
import com.epam.project.controller.ActionCommand;
import com.epam.project.dto.AdvertUpdateDTO;
import com.epam.project.exception.ServiceException;
import com.epam.project.service.AdvertService;

import jakarta.servlet.http.HttpServletRequest;

public class UpdateAdvertActionCommand implements ActionCommand {

    private AdvertService advertService = InstanceProvider.getAdvertServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        AdvertUpdateDTO dto = new AdvertUpdateDTO();
        dto.setId(Integer.parseInt(request.getParameter("id")));
        dto.setSectionId(Integer.parseInt(request.getParameter("sectionID")));
        dto.setName(request.getParameter("name"));
        dto.setContent(request.getParameter("content"));
        dto.setCost(Double.parseDouble(request.getParameter("cost")));
        try {
            advertService.updateAdvert(dto);
            page = ConfigurationManager.getProperty("path.page.admin");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
