package ru.vaxl.Client;



public class Main {
    public static void main(String[] args) {
        final String SERVER_IP = args[0];

        try (Client client = new Client(SERVER_IP)){
            client.start();
        }catch (Exception e) {e.printStackTrace();}
    }
}
