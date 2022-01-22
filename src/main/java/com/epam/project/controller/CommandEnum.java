package com.epam.project.controller;

import com.epam.project.controller.Commands.*;
import com.epam.project.controller.Commands.*;

public enum CommandEnum {

    LOGIN(new LoginActionCommand()),
    LOGOUT(new LogoutActionCommand()),
    SIGN_UP(new RegisterActionCommand()),
    OPEN_ADMIN_PAGE(new OpenAdminPageActionCommand()),
    OPEN_MAIN_PAGE(new OpenMainPageActionCommand()),
    DELETE_SECTION(new DeleteSectionActionCommand()),
    CREATE_SECTION(new CreateSectionActionCommand()),
    UPDATE_SECTION(new UpdateSectionActionCommand()),
    CREATE_ADVERT(new CreateAdvertActionCommand()),
    DELETE_ADVERT(new DeleteAdvertActionCommand()),
    UPDATE_ADVERT(new UpdateAdvertActionCommand()),
    OPEN_ADVERT(new OpenAdvertPageActionCommand()),
    OPEN_ADVERT_CREATE_PAGE(new OpenAdvertCreatePageActionCommand()),
    OPEN_ADVERTS_OF_USER(new OpenAdvertsOfUserActionCommand()),
    OPEN_SECTION(new OpenSectionPageActionCommand());


    ActionCommand command;

    CommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCurrentCommand() {
        return command;
    }

}
