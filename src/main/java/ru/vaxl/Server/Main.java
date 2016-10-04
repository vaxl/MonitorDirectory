package ru.vaxl.Server;


public class Main {

    public static void main(String[] args) throws Exception {
        final String SERVER_IP = "localhost";
        final String DIRECTORY = "C:\\temp";
        final int SCAN_RATE = Integer.valueOf("50");

        try (Server server = new Server(SERVER_IP,DIRECTORY,SCAN_RATE)){
            server.start();
        }

    }
}
