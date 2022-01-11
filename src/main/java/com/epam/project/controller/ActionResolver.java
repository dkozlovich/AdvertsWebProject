package com.epam.project.controller;

public class ActionResolver {

    public static ActionCommand defineCommand(String command) {
        return CommandEnum.valueOf(command).getCurrentCommand();
    }

}
