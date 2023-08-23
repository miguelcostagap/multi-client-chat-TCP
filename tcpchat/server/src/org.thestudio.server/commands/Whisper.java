package org.thestudio.server.commands;

import org.thestudio.server.ClientConnection;
import org.thestudio.server.Server;

import java.io.IOException;

public class Whisper implements SpecialCommands {
    private Server server;
    private ClientConnection clientConnection;
    private ClientConnection clientToWhisperTo;

    public Whisper(ClientConnection connection, Server server) {
        this.server = server;
        this.clientConnection = connection;
    }

    private boolean identifyWhisperClient(String client) {
        for (ClientConnection clients : server.getConnections()) {
            if (clients.getClientName().equals(client)) {
                clientToWhisperTo = clients;
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {
        try {
            clientConnection.send("Please insert the name of the person you wish to send a private message:");
            String client = clientConnection.getReader().readLine();
            if (identifyWhisperClient(client)) {
                clientConnection.send("Enter the message to send:");
                clientToWhisperTo.send("Private Message from: " + clientConnection.getClientName());
                String whisperMessage = clientConnection.getReader().readLine();
                clientToWhisperTo.send(whisperMessage);
                return;
            }
            clientConnection.send("The name you entered does not exist, or it is misspelled!");
            clientConnection.send("(you can enter '/list' to see the available persons)");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
