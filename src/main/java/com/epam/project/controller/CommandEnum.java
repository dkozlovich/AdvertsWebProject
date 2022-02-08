package com.epam.project.controller;

import com.epam.project.controller.Commands.*;
import com.epam.project.controller.Commands.*;

public enum CommandEnum {

    LOGIN(new LoginActionCommand()),
    LOGOUT(new LogoutActionCommand()),
    SIGN_UP(new RegisterActionCommand()),
    OPEN_ADMIN_PAGE(new OpenAdminPageActionCommand()),
    OPEN_MAIN_PAGE(new OpenMainPageActionCommand()),
    OPEN_LOGIN_PAGE(new OpenLoginPageActionCommand()),
    OPEN_SIGNUP_PAGE(new OpenSignUpPageActionCommand()),
    DELETE_SECTION(new DeleteSectionActionCommand()),
    CREATE_SECTION(new CreateSectionActionCommand()),
    UPDATE_SECTION(new UpdateSectionActionCommand()),
    CREATE_ADVERT(new CreateAdvertActionCommand()),
    DELETE_ADVERT(new DeleteAdvertActionCommand()),
    UPDATE_ADVERT(new UpdateAdvertActionCommand()),
    OPEN_ADVERT(new OpenAdvertPageActionCommand()),
    OPEN_ADVERT_CREATE_PAGE(new OpenAdvertCreatePageActionCommand()),
    OPEN_ADVERT_UPDATE_PAGE(new OpenAdvertUpdatePageActionCommand()),
    OPEN_ADVERTS_OF_USER(new OpenAdvertsOfUserActionCommand()),
    OPEN_SECTION(new OpenSectionPageActionCommand()),
    CREATE_MESSAGE(new CreateMessageActionCommand()),
    SAVE_IMAGE(new SaveImageActionCommand()),
    CHANGE_LOCALE(new ChangeLocaleActionCommand());

    ActionCommand command;

    CommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCurrentCommand() {
        return command;
    }

}
