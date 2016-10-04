package ru.vaxl;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    static public String readLine(){
        try{
            return reader.readLine();
        }catch (IOException ignore) {return null;}
    }

    static public void printMessage(String message){
        System.out.println(message);
    }
}
