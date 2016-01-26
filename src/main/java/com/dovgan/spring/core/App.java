package com.dovgan.spring.core;

import com.dovgan.spring.AOP.StatisticsAspect;
import com.dovgan.spring.core.beans.Client;
import com.dovgan.spring.core.loggers.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.Setter;
import java.util.Map;

/**
 * Created by alexdovgan on 12/18/15.
 */
@Setter
@Component
public class App {
    public enum EventType {
        INFO,ERROR
    }

    private Client c;
    private EventLogger logger;
    @Autowired
    private StatisticsAspect statistic;

    public App(Client c, EventLogger logger) {
        this.c = c;
        this.logger = logger;
    }

    public void logEvent(App.EventType type, Event e) {
        logger.eventLog(type, e);
    }

    public Map<Class<?>, Integer> getStatistic(){
        return statistic.getCounter();
    }

}
