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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class CreateAdvertActionCommand implements ActionCommand {

    private AdvertService advertService = InstanceProvider.getAdvertServiceImpl();

    private UserService userService = InstanceProvider.getUserServiceImpl();

    private SectionService sectionService = InstanceProvider.getSectionServiceImpl();

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        int recordsPerPage = 5;
        int sectionId = Integer.parseInt(request.getParameter("sectionID"));
        String name = request.getParameter("name");
        String content = request.getParameter("content");
        double cost = Double.parseDouble(request.getParameter("cost"));
        int userId = Integer.parseInt(request.getParameter("userID"));
        AdvertCreateDTO dto = new AdvertCreateDTO();
        dto.setSectionId(sectionId).setName(name).setContent(content).setCost(cost).setUserId(userId);
        if (request.getSession().getAttribute("currentUser") != null) {
            advertService.createAdvert(dto);
            userService.setSessionAttributes(request);
            int totalAdvertsOfSectionNumber = sectionService.getTotalAdvertsOfSectionNumber(sectionId);
            int totalPagesNumber = (int) Math.ceil(totalAdvertsOfSectionNumber * 1.0 / recordsPerPage);
            page = "/Controller?command=OPEN_SECTION&sectionID=" + sectionId + "&page=" + totalPagesNumber;
        }
        return page;
    }
}
