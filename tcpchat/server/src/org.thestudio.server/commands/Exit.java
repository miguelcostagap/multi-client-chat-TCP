package org.thestudio.server.commands;

import org.thestudio.server.ClientConnection;
import org.thestudio.server.Server;

import java.io.IOException;

public class Exit implements SpecialCommands{
    private Server server;
    private ClientConnection connection;
    public Exit(Server server, ClientConnection connection){
        this.server = server;
        this.connection = connection;
    }
    @Override
    public void run() {

        try {
            server.removeClient(connection);
            server.broadcast("a client has left the chat");
            connection.getClientSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
