package io.tiles.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Samvel Abrahamyan 11/25/16.
 */
public class Client {

    public static void main(String[] args) throws IOException {

        String hostname = "localhost";
        int port = 3000;

        Socket echoSocket = new Socket(hostname, port);
        PrintWriter out =
                new PrintWriter(echoSocket.getOutputStream(), true);
        BufferedReader in =
                new BufferedReader(
                        new InputStreamReader(echoSocket.getInputStream()));
        String message;
        while((message = in.readLine()) != null) {
            System.out.println("Hello");
            System.out.println("echo: " + message);
        }

    }
}
