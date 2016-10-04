package ru.vaxl.Server.connections;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface Connect  {
    boolean init(String addr);
    void close() throws IOException,TimeoutException;
    boolean send(String message);
}
