package ru.vaxl.Server.connections;

/*For test*/

import static ru.vaxl.ConsoleHelper.*;

public class ConnectSoutImpl implements Connect {
    @Override
    public boolean init(String addr) {
        printMessage("init");
        return true;
    }

    @Override
    public void close() {
        printMessage("close");
    }

    @Override
    public boolean send(String message) {
        printMessage(message);
        return true;
    }
}
