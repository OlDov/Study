package com.dovgan.spring.core.loggers;

import com.dovgan.spring.core.App;
import com.dovgan.spring.core.Event;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexdovgan on 12/20/15.
 */
public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private String fileName;
    List<Event> cache;

    public CacheFileEventLogger(String fileName) {
        super(fileName);
        this.fileName = fileName;
    }

    public void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    @Override
    public void eventLog(App.EventType type ,Event e) {
        if(cache == null)
            cache = new ArrayList<Event>();
        cache.add(e);
        if (cache.size() >= cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    private void writeEventsFromCache() {
        File file = new File(fileName);
        for (Event curr : cache) {
            try {
                FileUtils.writeStringToFile(file, curr.toString() + System.lineSeparator(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @PreDestroy
    private void destroy() {
        System.out.println("ctxDestroing...");
        if (!cache.isEmpty()) {
            writeEventsFromCache();
        }
    }
}
