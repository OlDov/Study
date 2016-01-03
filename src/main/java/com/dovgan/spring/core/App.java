package com.dovgan.spring.core;

import com.dovgan.spring.core.beans.Client;
import com.dovgan.spring.core.loggers.ConsoleEventLogger;
import com.dovgan.spring.core.loggers.EventLogger;

import java.util.Map;

/**
 * Created by alexdovgan on 12/18/15.
 */
public class App {
    public enum EventType {
        INFO,ERROR
    }

    private Client c;
    private EventLogger logger;

    public App(Client c, EventLogger logger) {
        this.c = c;
        this.logger = logger;
    }

    public void logEvent(App.EventType type, Event e) {
        logger.eventLog(type, e);
    }
}
