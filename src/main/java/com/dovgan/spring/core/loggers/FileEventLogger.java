package com.dovgan.spring.core.loggers;

import com.dovgan.spring.core.App;
import com.dovgan.spring.core.Event;
import org.apache.commons.io.FileUtils;
import sun.jvm.hotspot.runtime.*;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Inherited;

/**
 * Created by alexdovgan on 12/20/15.
 */
public class FileEventLogger implements EventLogger{
    private String fileName;
    private File file;
    {
        System.out.println("+++"+getClass().getName());
    }
    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public void eventLog(App.EventType type ,Event e) {
        try {
            FileUtils.writeStringToFile(file, e.toString() + System.lineSeparator(),true);
            System.out.println("wrote "+e.toString());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void init() throws IOException{
        System.out.println("context init... class:"+ getClass().getName() + " parent:" + getClass().getSuperclass().getName());
        file = new File(fileName);
        if(!file.exists())
            file.createNewFile();
        if (!file.canWrite())
            throw new IOException("cant write to File "+fileName);
    }

}
