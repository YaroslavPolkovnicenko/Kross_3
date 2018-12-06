package com.company;

import com.company.Threads.FactoryThread;
import com.company.Threads.ReadThread;


public class Main {

    public static void main(String[] args) throws InterruptedException {

        ReadThread readThread1 = new ReadThread("csv\\Staffers.csv");
        ReadThread readThread2 = new ReadThread("csv\\Workers.csv");

        FactoryThread factoryThread = new FactoryThread();

        Thread thread1 = new Thread(readThread1);
        thread1.setName("Read Thread 1 ");

        Thread thread2 = new Thread(readThread2);
        thread2.setName("Read Thread 2 ");

        Thread thread3 = new Thread(factoryThread);
        thread3.setName("Factory Thread ");

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println();
        System.out.println();

        factoryThread.printList();

        System.out.println();

        factoryThread.sortByName();

        System.out.println("Отсортированный список:");

        factoryThread.printList();
    }
}