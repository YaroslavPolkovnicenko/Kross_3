package com.company.Events;

import java.util.ArrayList;

public class MyEventProducer {

    private ArrayList<MyListener> listeners = new ArrayList<>();

    public void addMyListener(MyListener listener)    {
        listeners.add(listener);
    }

    protected void fireMyHappend(String message)    {

        MyEvent ev = new MyEvent(this, message);

        for(MyListener listener : listeners)
            listener.myHappend(ev);
    }

    public void doWork(String workName)    {
        fireMyHappend("End work" + workName);
    }
}