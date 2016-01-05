package com.dovgan.spring.core;

import com.dovgan.spring.core.beans.Client;
import com.dovgan.spring.core.loggers.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by alexdovgan on 12/18/15.
 */
@Getter
@Setter
@Component
public class App {

    public enum EventType {
        INFO,ERROR
    }
    @Autowired
    private Client c;

    @Autowired
    @Qualifier("combinedEventLogger")
    private EventLogger logger;

//        public App(EventLogger logger) {
//        this.logger = logger;
//    }
    //    @Autowired
//    public void setC(Client c) {
//        this.c = c;
//    }
//
//    @Autowired
//    @Qualifier("EventLoggerCombiner")
//    public void setLogger(EventLogger logger) {
//        this.logger = logger;
//    }

    public void logEvent(App.EventType type, Event e) {
        logger.eventLog(type, e);
    }
}
