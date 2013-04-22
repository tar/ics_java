package ru.spbstu.ics.java.network.Chat;

import java.io.Serializable;

/**
 * User: vlastachu
 * Date: 21.04.13
 * Time: 20:30
 */
public class SocketCommand implements Serializable {

    public static enum Command {SEND, JOIN, EXIT, USERLIST, VOID, SUCCESS, ERROR, KICK}
    private Command command;
    private String[] params;
    SocketCommand(Command command, String[] parameters){
        this.command = command;
        params = parameters;
    }

    public String[] getParams() {
        return params;
    }

    public Command getCommand() {
        return command;
    }
}
