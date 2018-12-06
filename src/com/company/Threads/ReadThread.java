package com.company.Threads;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadThread implements Runnable{

    private String fileName;

    public ReadThread(String fileName) {
        this.fileName = fileName;
    }

    public void read(BufferedReader reader) throws IOException {

        String line;

        while((line = reader.readLine()) != null) {
            System.out.println("Работает " + Thread.currentThread().getName() + " Строка: " + line);
            EntryQueue.add(line);
        }
    }

    @Override
    public void run() {

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(fileName));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            read(reader);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}