package com.company.Threads;

import java.util.ArrayDeque;
import java.util.Deque;

public class EntryQueue {

    private static Deque<String> queue = new ArrayDeque<>();

    public static synchronized void add(String i) {
        queue.add(i);
        FactoryThread.eventProducer.doWork();
    }

    public static String getFirst() {
        return queue.pollFirst();
    }
}