package com.dovgan.spring.core.loggers;

import com.dovgan.spring.core.App;
import com.dovgan.spring.core.Event;

/**
 * Created by alexdovgan on 12/20/15.
 */
public interface EventLogger {
    void eventLog(App.EventType type ,Event e);
}
