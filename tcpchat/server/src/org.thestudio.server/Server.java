package org.thestudio.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * accepts client connections
 * receives message from client and broadcast message to other clients
 * thread1: awaits for client connections
 * thread2: reads incoming message and sends to other clients
 */
public class Server {

    private Socket clientSocket;
    private ServerSocket serverSocket;

    private List<ClientConnection> connections;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        connections = Collections.synchronizedList(new ArrayList<>());
    }

    public void start() throws IOException {

        System.out.println("A server started at " + serverSocket);

        while (true) {
            waitConnection();
        }
    }

    private void waitConnection() throws IOException {
        // wait for client connection
        clientSocket = serverSocket.accept();

        System.out.println("A new user has connected to the server");

        // create a new thread for the new client
        Thread thread = new Thread(new ClientConnection(clientSocket, this));
        thread.start();

    }

    public void broadcast(String message) {

        synchronized (connections) {
            for (ClientConnection connection : connections) {
                connection.send(message);
            }
        }
    }

    public void removeClient(ClientConnection connection) {
        connections.remove(connection);

    }

    public void addClients(ClientConnection connection) {
        connections.add(connection);
       // clientCount++;
        //clientNames.add("Client_1");
    }

    public List<ClientConnection> getConnections() {
        return connections;
    }

    public static void main(String[] args) {

        try {
            Server server = new Server(8092);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
