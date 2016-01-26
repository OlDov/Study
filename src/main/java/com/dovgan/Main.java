package com.dovgan;

import com.dovgan.spring.AOP.StatisticsAspect;
import com.dovgan.spring.core.App;
import com.dovgan.spring.core.Event;
import com.dovgan.spring.core.loggers.CombinedEventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by alexovgan on 12/20/15.
 */
public class Main {
    public static void main(String[] args) {
        //ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("ctx.xml");
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("ctxLogger.xml","ctx.xml");
        App app = (App)ctx.getBean("App");
        System.out.println("app started...");

        Event e = (Event)ctx.getBean("Ev");
        e.setMsg("INFO");
        app.logEvent(App.EventType.INFO, e);
        app.logEvent(null, (Event) ctx.getBean("Ev"));
        e = (Event)ctx.getBean("Ev");
        e.setMsg("ERROR");
        app.logEvent(App.EventType.ERROR, (Event)ctx.getBean("Ev"));
//        StatisticsAspect a = (StatisticsAspect)ctx.getBean("StatisticsAspect");
//        a.
        ctx.close();
    }
}
