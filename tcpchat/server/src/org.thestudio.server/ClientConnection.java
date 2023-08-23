package org.thestudio.server;

import org.thestudio.server.commands.IdentifyCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnection implements Runnable {
    private Socket clientSocket;
    private Server server;
    private BufferedReader reader;
    private PrintWriter writer;
    private String clientName;

    public void send(String message) {
        writer.println(message);
    }

    public void listenAndBroadcast() {

        String message;
        try {
            message = reader.readLine();
            if (IdentifyCommand.identifyCommand(message, server, this)){
            return;
            }
            server.broadcast(clientName);
            server.broadcast(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateFirstName(){
        // generate a unique number to each client, to avoid repetition as they disconnect and connect
        String[] parts = clientSocket.toString().split(",");
        String[] portParts = parts[1].split("=");
        clientName = "Client_" + portParts[1];
    }

    /**
     * Constructor & Getters & Setters
     */

    public ClientConnection(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
    }
    public void setClientName(String newName){
        clientName = newName;
    }
    public Socket getClientSocket() {
        return clientSocket;
    }
    public BufferedReader getReader(){
        return  reader;
    }
    public String getClientName() {
        return clientName;
    }

    /**
     * RUN Method
     */

    @Override
    public void run() {

        try {
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(), true);
            generateFirstName();

            send("\nwelcome to our server " + clientName + " :)");
            send("In case you need assistance, you can type '/help' to see aditional commands");
            send("\nStart chatting!");
            server.addClients(this);

            while (!clientSocket.isClosed()) {
                listenAndBroadcast();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
