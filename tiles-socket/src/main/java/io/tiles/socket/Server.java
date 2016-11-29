package io.tiles.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Samvel Abrahamyan 11/25/16.
 */
public class Server {

    public static void main(String[] args) throws IOException {

        int port = 3000;

        ServerSocket serverSocket = new ServerSocket(port);


        while(true){
            Socket clientSocket = serverSocket.accept();
            PrintWriter out =
                    new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            String line;
            while((line = in.readLine()) != null){
                System.out.println(line);
            }

            out.write("bcctpu");
            out.close();
            System.out.println("Connected!");
        }

    }

}
