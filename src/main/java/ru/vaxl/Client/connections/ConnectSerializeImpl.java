package ru.vaxl.Client.connections;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ConnectSerializeImpl implements ConnectToServer {
    private Socket socket;
    private ObjectInputStream in;

    @Override
    public boolean init(String addr) {
        try {
            socket = new Socket(addr, 502);
            in = new ObjectInputStream(socket.getInputStream());
            return true;
        } catch (IOException e) {return false;}
    }

    @Override
    public void close() throws IOException {
        if(in!=null)     in.close();
        if(socket!=null) socket.close();
    }

    @Override
    public String recieve() throws IOException {
        try {
                return (String) in.readObject();
        } catch (ClassNotFoundException e) {e.printStackTrace(); return "Error";}
    }
}
