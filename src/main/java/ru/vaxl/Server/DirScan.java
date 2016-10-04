package ru.vaxl.Server;

import java.io.File;

import static ru.vaxl.ConsoleHelper.*;

class DirScan {
    private File file;
    private StringBuilder scanResult = new StringBuilder();

    DirScan(String dir) {
        file = new File(dir);
    }

    String scan(){
        if (file==null & !file.exists()) {
            printMessage("Directory error");
            System.exit(1);
        }
        File[] files = file.listFiles();
        if (files !=null) {
            scanResult.delete(0, scanResult.capacity());
            scanResult.append("Directory : ").append(file.getName()).append("\n");
            for (File f : files) {
                scanResult.append(f.getName()).append("\n");
            }
        }
        return scanResult.toString();
    }

}



