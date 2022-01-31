package com.epam.project.controller.Commands;

import com.epam.project.ConfigurationManager;
import com.epam.project.InstanceProvider;
import com.epam.project.MessageManager;
import com.epam.project.controller.ActionCommand;
import com.epam.project.dto.SectionDTO;
import com.epam.project.exception.IncorrectLoginOrPassException;
import com.epam.project.exception.ServiceException;
import com.epam.project.model.Section;
import com.epam.project.model.User;
import com.epam.project.service.SectionService;
import com.epam.project.service.UserService;

import com.epam.project.util.DTOMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

public class LoginActionCommand implements ActionCommand {

    private UserService userService = InstanceProvider.getUserServiceImpl();

    private SectionService sectionService = InstanceProvider.getSectionServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        try {
            userService.loginUser(request,login,password);
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("currentUser");
            session.setAttribute("currentUser", user);
            List<SectionDTO> sectionsDTO = new ArrayList<>();
            List<Section> sections = sectionService.getAll();
            for (Section section : sections){
                sectionsDTO.add(DTOMapper.mapSection(section));
            }
            session.setAttribute("sections", sectionsDTO);
            if (user.isAdmin()) {
                page = "/Controller?command=OPEN_ADMIN_PAGE";

            } else {
                page = "/Controller?command=OPEN_MAIN_PAGE";
            }
        } catch (IncorrectLoginOrPassException e) {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
