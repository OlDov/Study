package com.dovgan;

import com.dovgan.spring.core.App;
import com.dovgan.spring.core.AppConfig;
import com.dovgan.spring.core.Event;
import com.dovgan.spring.core.LoggersConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by alexovgan on 12/20/15.
 */
public class Main {

    public static void main(String[] args) {
        //ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("ctx.xml");
        //ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("ctxLogger.xml","ctx.xml");
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.scan ("com.dovgan.spring");
        ctx.refresh();

        App app = (App)ctx.getBean(App.class);
        System.out.println("app started...");
        //1
        Event e = ctx.getBean(Event.class);
        e.setMsg("INFO");
        app.logEvent(App.EventType.INFO, e);
        //2
        app.logEvent(null, (Event) ctx.getBean(Event.class));
        //3
        e = ctx.getBean(Event.class);
        e.setMsg("ERROR!");
        app.logEvent(App.EventType.ERROR, e);
        ctx.close();
    }

}
