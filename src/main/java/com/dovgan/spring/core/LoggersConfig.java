package com.dovgan.spring.core;

import com.dovgan.spring.core.loggers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by alexdovgan on 1/4/16.
 */
@Configuration
@PropertySource({"classpath:logger.properties", "classpath:client.properties"})
public class LoggersConfig {
    @Value("${cacheSize}")
    private String cacheSize;
    @Value("${logPath}")
    private String logPath;

//    @Autowired
//    FileEventLogger fileEventLogger;
    //private Map<App.EventType, EventLogger> loggerMap;

    @Bean
         ConsoleEventLogger consoleEventLogger() {
        return new ConsoleEventLogger();
    }


    @Bean(initMethod = "init")
    FileEventLogger fileEventLogger() {
        FileEventLogger f =  new FileEventLogger(logPath);
        return f;
    }

    @Bean(destroyMethod = "destroy")
    CacheFileEventLogger cacheFileEventLogger() {
        CacheFileEventLogger l = new CacheFileEventLogger(logPath);
        l.setCacheSize(Integer.valueOf(cacheSize));
        return l;
    }

    @Bean
    CombinedEventLogger combinedEventLogger() {
        CombinedEventLogger l = new CombinedEventLogger(loggerMap());
        l. setDefaultLogger(cacheFileEventLogger());
        return l;
    }

    Map<App.EventType, EventLogger> loggerMap(){
        Map<App.EventType, EventLogger> map = new HashMap<App.EventType, EventLogger>();
        map.put(App.EventType.INFO, consoleEventLogger());
        map.put(App.EventType.ERROR, fileEventLogger());
        return map;
    }

}


//<!--events-->
//<bean id="Ev" class="com.dovgan.spring.core.Event" scope="prototype"
//        c:df-ref="dateFormat">
//<constructor-arg>
//<bean class="java.util.Date"/>
//</constructor-arg>
//</bean>
//
//<!--loggers-->
//<bean id="EventLoggerConsole" class="com.dovgan.spring.core.loggers.ConsoleEventLogger"/>
//
//<bean id="EventLoggerFile" class="com.dovgan.spring.core.loggers.FileEventLogger" init-method="init"
//        c:fileName="${logPath}">
//</bean>
//<bean id="EventLoggerFileCache" class="com.dovgan.spring.core.loggers.CacheFileEventLogger" destroy-method="destroy" parent="EventLoggerFile"
//        p:cacheSize="${cacheSize}"
//        c:fileName="${logPath}">
//</bean>
//<bean id="EventLoggerCombiner" class="com.dovgan.spring.core.loggers.CombinedEventLogger"
//        c:loggers-ref="loggerMap"
//        p:defaultLogger-ref="EventLoggerFileCache">
//</bean>