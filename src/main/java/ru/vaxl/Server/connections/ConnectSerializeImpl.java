package ru.vaxl.Server.connections;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectSerializeImpl  implements Connect {
    private  ServerSocket serverSocket;
    private  Socket socket;
    private  ObjectOutputStream out;

    @Override
    public boolean init(String addr) {
        try {
            serverSocket = new ServerSocket(502);
            socket = serverSocket.accept();
            out = new ObjectOutputStream(socket.getOutputStream());
            return true;
        } catch (IOException e) {
            e.printStackTrace();return false;
        }
    }

    @Override
    public void close() throws IOException {
        if (serverSocket!=null) serverSocket.close();
        if (out!=null) out.close();
        if (socket!=null) socket.close();
    }

    @Override
    public boolean send(String message) {
        try {
            out.writeObject(message);
            return true;
        } catch (IOException e) {return false;}
    }
}
