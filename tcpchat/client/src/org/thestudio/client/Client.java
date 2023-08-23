package org.thestudio.client;

import org.thestudio.client.graphics.InitializeGeneralGraphics;
import org.thestudio.client.graphics.messages.MessageGraphic;
import org.thestudio.client.graphics.messages.OwnMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * represents tcp chat client
 * reads from message keyboard
 * sends message
 * listens for server incoming messages
 * <p>
 * thread1:listens for server messages
 * thread2:reads from keyboard and sends to server
 */

public class Client {

    private final Socket socket;
    private List<MessageGraphic> messageGraphics;

    public Client(String host, int port) throws IOException {
        socket = new Socket(host, port);
    }

    public void start() throws IOException {

        InitializeGeneralGraphics initializeGraphics = new InitializeGeneralGraphics();
        initializeGraphics.start();

        //TESTS BELOW TO DELETE 2 LINES
        messageGraphics = new ArrayList<>();
        messageGraphics.add(new OwnMessage("popopopo"));


        Thread thread = new Thread(new KeyboardHandler(socket));
        thread.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        while (socket.isBound()) {
            waitMessage(reader);
        }

    }

    /**
     * waits message from server and prints it to console
     */

    private void waitMessage(BufferedReader reader) throws IOException {

        String message = reader.readLine();

        if (message == null) {
            System.out.println("server closed the connection");
            System.exit(0);
        }


        // implement the method to move messages here?

        System.out.println(message);
    }

    public static void main(String[] args) {

        try {
            Client client = new Client("127.0.0.1", 8092);
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
