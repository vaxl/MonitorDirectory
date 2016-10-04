package ru.vaxl.Server;


public class Main {

    public static void main(String[] args) throws Exception {
        final String SERVER_IP = args[0];
        final String DIRECTORY = args[1];
        final int SCAN_RATE = Integer.valueOf(args[2]);

        try (Server server = new Server(SERVER_IP,DIRECTORY,SCAN_RATE)){
            server.start();
        }

    }
}
