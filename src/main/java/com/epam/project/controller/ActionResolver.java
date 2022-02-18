package com.epam.project.controller;

import com.epam.project.controller.command.ActionCommand;

public class ActionResolver {

    public static ActionCommand defineCommand(String command) {
        return CommandEnum.valueOf(command).getCurrentCommand();
    }

}
