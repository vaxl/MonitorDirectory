package ru.vaxl.Server;

import java.io.File;

import static ru.vaxl.ConsoleHelper.*;

public class DirScan {
    private File file;
    private File [] files;
    private StringBuilder scanResult = new StringBuilder();

    public DirScan(String dir) {
        file = new File(dir);
    }

    public String scan(){
        if (file==null & !file.exists()) {
            printMessage("Directory error");
            System.exit(1);
        }
        files = file.listFiles();
        if (files!=null) {
            scanResult.delete(0, scanResult.capacity());
            scanResult.append("Directory : ").append(file.getName()).append("\n");
            for (File f : files) {
                scanResult.append(f.getName()).append("\n");
            }
        }
        return scanResult.toString();
    }

}



