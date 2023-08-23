package org.thestudio.server.commands;

import org.thestudio.server.ClientConnection;
import org.thestudio.server.Server;

public class List implements SpecialCommands {
    private ClientConnection clientConnection;
    private Server server;

    public List(ClientConnection connection, Server server) {
        this.clientConnection = connection;
        this.server = server;
    }

    @Override
    public void run() {
        clientConnection.send("Current connected clients:");
        for (ClientConnection clients : server.getConnections()) {
            clientConnection.send(clients.getClientName());
        }
    }
}
