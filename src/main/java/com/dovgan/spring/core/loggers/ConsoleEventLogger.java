package com.dovgan.spring.core.loggers;

import com.dovgan.spring.core.App;
import com.dovgan.spring.core.Event;

/**
 * Created by alexdovgan on 12/18/15.
 */
public class ConsoleEventLogger implements EventLogger{

    public void eventLog(App.EventType type, Event e) {
        System.out.println(e.toString()+"----");
    }
}
