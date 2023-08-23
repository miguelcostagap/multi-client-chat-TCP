package org.thestudio.server.commands;

import org.thestudio.server.ClientConnection;
import org.thestudio.server.Server;

public class Help implements SpecialCommands {
    private ClientConnection clientConnection;

    public Help(ClientConnection clientConnection) {
        this.clientConnection = clientConnection;
    }

    @Override
    public void run() {
        clientConnection.send("You can perform the following commands:\n");
        for (CommandsList command : CommandsList.values()) {
            clientConnection.send(command.getCommand());
            clientConnection.send(command.getDescription());
        }
    }
}
