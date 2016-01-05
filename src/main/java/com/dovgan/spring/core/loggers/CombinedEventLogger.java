package com.dovgan.spring.core.loggers;
import com.dovgan.spring.core.App;
import com.dovgan.spring.core.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * Created by alexdovgan on 12/22/15.
 */
public class CombinedEventLogger implements EventLogger{
    private EventLogger defaultLogger;
    private String ss;

    Map<App.EventType, EventLogger> loggers;

    public void setDefaultLogger(EventLogger defaultLogger) {
        this.defaultLogger = defaultLogger;
    }

    public void eventLog(App.EventType type, Event e) {

    EventLogger logger = loggers.get(type);
        if(logger == null){
            logger = defaultLogger;
        }
        logger.eventLog(type, e);
    }

    public CombinedEventLogger(Map<App.EventType, EventLogger> loggers) {

        this.loggers = loggers;
    }

}
