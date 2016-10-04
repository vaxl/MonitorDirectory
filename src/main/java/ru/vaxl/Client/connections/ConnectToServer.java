package ru.vaxl.Client.connections;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface ConnectToServer {
    boolean init(String addr);
    void close() throws IOException,TimeoutException;
    String recieve() throws IOException ;
}
