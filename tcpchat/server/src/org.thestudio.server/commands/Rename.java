package org.thestudio.server.commands;

import org.thestudio.server.ClientConnection;
import org.thestudio.server.Server;

import java.io.IOException;

public class Rename implements SpecialCommands {
    private ClientConnection clientConnection;

    public Rename(ClientConnection clientConnection) {
        this.clientConnection = clientConnection;
    }

    @Override
    public void run() {
        try {
            clientConnection.send("Please insert the desired new name:");
            clientConnection.setClientName(clientConnection.getReader().readLine());
            while (clientConnection.getClientName().isEmpty()) {
                clientConnection.send("You have entered an empty name,please insert a valid name:");
                clientConnection.setClientName(clientConnection.getReader().readLine());
                if (!clientConnection.getClientName().isEmpty()) {
                    break;
                }
            }
        clientConnection.send("Congratulations, you have successfully changed your name to " + clientConnection.getClientName() +"!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
