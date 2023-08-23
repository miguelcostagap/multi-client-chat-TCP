package org.thestudio.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class KeyboardHandler implements Runnable {

    private Socket socket;

    public KeyboardHandler(Socket socket) {
        this.socket = socket;
    }

    /**
     * listens for message from keyboard input
     * sends message to server
     */

    @Override
    public void run() {

        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            while (!socket.isClosed()) {
                String message = input.readLine();

                if (message.equals("/quit")) {
                    System.exit(0);
                }
                output.println(message);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
