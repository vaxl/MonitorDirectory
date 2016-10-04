package ru.vaxl.Client;

import ru.vaxl.Client.connections.ConnectToServer;
import ru.vaxl.Client.connections.ConnectSerializeImpl;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import static ru.vaxl.ConsoleHelper.printMessage;

class Client implements Runnable,AutoCloseable{
    private final String SERVER_IP;
    private volatile AtomicBoolean isRun = new AtomicBoolean(false);
    private ConnectToServer connect;

    Client(String SERVER_IP) {
        this.SERVER_IP = SERVER_IP;
    }

    void start(){
        connect = new ConnectSerializeImpl();
        if (!connect.init(SERVER_IP)) {
            printMessage("No connection");
            return;
        }
        printMessage("Connected");
        isRun.set(true);
        run();
    }

    @Override
    public void close() throws Exception {
        if (connect!=null) connect.close();
    }

    @Override
    public void run() {
        while (isRun.get()) {
            try {
                printMessage(connect.recieve());
            } catch (IOException e) {e.printStackTrace();}
        }
    }
}
