package org.thestudio.server.commands;

import org.thestudio.server.ClientConnection;
import org.thestudio.server.Server;

public class IdentifyCommand {

    public static boolean identifyCommand(String command, Server server, ClientConnection connection) {

        String readCommand = command;
        if (readCommand == null) {
            readCommand = "/quit";
        }

        switch (readCommand) {
            case "/quit":
                Exit exit = new Exit(server, connection);
                exit.run();
                return true;
            case "/help":
                Help help = new Help(connection);
                help.run();
                return true;
            case "/rename":
                Rename rename = new Rename(connection);
                rename.run();
                return true;
            case "/list":
                List list = new List(connection, server);
                list.run();
                return true;
            case "/whisper":
                Whisper whisper = new Whisper(connection, server);
                whisper.run();
                return true;
            default:
                return false;
        }

    }
}
