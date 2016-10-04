package ru.vaxl.Server;

import ru.vaxl.Server.connections.Connect;
import ru.vaxl.Server.connections.ConnectSerializeImpl;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import static java.lang.Thread.sleep;
import static ru.vaxl.ConsoleHelper.printMessage;

class Server implements Runnable,AutoCloseable{
    private volatile AtomicBoolean isRun = new AtomicBoolean(false);
    private String lastSended = "";
    private Connect connect;
    private DirScan dirScan;
    private final String SERVER_IP;
    private final String DIRECTORY;
    private final int SCAN_RATE;

    Server(String SERVER_IP, String DIRECTORY, int SCAN_RATE) {
        this.SERVER_IP = SERVER_IP;
        this.DIRECTORY = DIRECTORY;
        this.SCAN_RATE = SCAN_RATE;
    }

    void start(){
        connect = new ConnectSerializeImpl();
        if (!connect.init(SERVER_IP)) {
            printMessage("No connection");
            return;
        }
        printMessage("Connected");
        dirScan = new DirScan(DIRECTORY);
        isRun.set(true);
        run();
    }

    @Override
    public void close(){
        if(connect !=null) try {
            connect.close();
        } catch (IOException |TimeoutException  ignore) {}
        isRun.set(false);
    }


    @Override
    public void run(){
        while(isRun.get()){
            String currentStateDir = dirScan.scan();
            if (!currentStateDir.equals(lastSended)){
                lastSended = currentStateDir;
                connect.send(currentStateDir);
            }
            else {
                try {
                    sleep(SCAN_RATE);
                } catch (InterruptedException ignore) {}
            }
        }
    }
}

