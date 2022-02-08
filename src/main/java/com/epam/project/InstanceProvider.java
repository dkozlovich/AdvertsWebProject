package com.epam.project;

import com.epam.project.dao.*;
import com.epam.project.dao.impl.*;
import com.epam.project.service.*;
import com.epam.project.service.impl.*;

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
    public static ImageDAO getImageDAOImpl() {return ImageDAOImpl.getInstance(); }
    public static ImageService getImageServiceImpl() {return ImageServiceImpl.getInstance(); }
}
