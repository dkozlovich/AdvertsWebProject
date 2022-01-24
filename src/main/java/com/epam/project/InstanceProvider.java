package com.epam.project;

import com.epam.project.dao.AdvertDAO;
import com.epam.project.dao.MessageDAO;
import com.epam.project.dao.SectionDAO;
import com.epam.project.dao.UserDAO;
import com.epam.project.dao.impl.AdvertDAOImpl;
import com.epam.project.dao.impl.MessageDAOImpl;
import com.epam.project.dao.impl.SectionDAOImpl;
import com.epam.project.dao.impl.UserDAOImpl;
import com.epam.project.service.AdvertService;
import com.epam.project.service.MessageService;
import com.epam.project.service.SectionService;
import com.epam.project.service.UserService;
import com.epam.project.service.impl.AdvertServiceImpl;
import com.epam.project.service.impl.MessageServiceImpl;
import com.epam.project.service.impl.SectionServiceImpl;
import com.epam.project.service.impl.UserServiceImpl;

public class InstanceProvider {
    public static UserDAO getUserDAOImpl() {
        return UserDAOImpl.getInstance();
    }
    public static UserService getUserServiceImpl() {
        return UserServiceImpl.getInstance();
    }
    public static SectionDAO getSectionDAOImpl() {return SectionDAOImpl.getInstance(); }
    public static SectionService getSectionServiceImpl() {return SectionServiceImpl.getInstance(); }
    public static AdvertDAO getAdvertDAOImpl() {
        return AdvertDAOImpl.getInstance();
    }
    public static AdvertService getAdvertServiceImpl() {return AdvertServiceImpl.getInstance(); }
    public static MessageDAO getMessageDAOImpl() {return MessageDAOImpl.getInstance(); }
    public static MessageService getMessageServiceImpl() {return MessageServiceImpl.getInstance(); }
}
