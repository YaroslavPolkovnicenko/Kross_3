package com.company.Events;

import java.util.ArrayList;

public class MyEventProducer {

    private ArrayList<MyListener> listeners = new ArrayList<>();

    public void addMyListener(MyListener listener)    {
        listeners.add(listener);
    }

    protected void fireMyHappend()    {

        MyEvent ev = new MyEvent(this);

        for(MyListener listener : listeners)
            listener.myHappend(ev);
    }

    public void doWork()    {
        fireMyHappend();
    }
}