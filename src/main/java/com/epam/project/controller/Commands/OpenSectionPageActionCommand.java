package com.epam.project.controller.Commands;

import com.epam.project.ConfigurationManager;
import com.epam.project.InstanceProvider;
import com.epam.project.controller.ActionCommand;
import com.epam.project.exception.ServiceException;
import com.epam.project.model.Advert;
import com.epam.project.service.AdvertService;
import com.epam.project.service.SectionService;
import com.epam.project.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class OpenSectionPageActionCommand implements ActionCommand {

    private UserService userService = InstanceProvider.getUserServiceImpl();

    private AdvertService advertService = InstanceProvider.getAdvertServiceImpl();

    private SectionService sectionService = InstanceProvider.getSectionServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        int sectionID = Integer.parseInt(request.getParameter("sectionID"));
        try {
            List<Advert> advertsOfSection = advertService.getBySectionId(sectionID, (page - 1) * recordsPerPage, recordsPerPage);
            int totalAdvertsOfSectionNumber = advertService.getTotalAdvertsOfSectionNumber(sectionID);
            int totalPagesNumber = (int) Math.ceil(totalAdvertsOfSectionNumber * 1.0 / recordsPerPage);
            request.getSession().setAttribute("advertsOfSection", advertsOfSection);
            request.getSession().setAttribute("sectionName", sectionService.getById(sectionID).get().getName());
            request.getSession().setAttribute("sectionID", sectionID);
            request.getSession().setAttribute("totalPagesNumber", totalPagesNumber);
            request.getSession().setAttribute("currentPage", page);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return ConfigurationManager.getProperty("path.page.section");
    }
}
