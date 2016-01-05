package com.dovgan.spring.core;

import com.dovgan.spring.core.loggers.CacheFileEventLogger;
import com.dovgan.spring.core.loggers.ConsoleEventLogger;
import com.dovgan.spring.core.loggers.EventLogger;
import com.dovgan.spring.core.loggers.FileEventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexdovgan on 1/4/16.
 */
@Configuration
@Import(LoggersConfig.class)
public class AppConfig {


    //<context:property-placeholder location="classpath:logger.properties, classpath:client.properties"/>
//    <bean id="dateFormat" class="java.text.DateFormat" factory-method="getDateTimeInstance"/>
//    <util:map id="loggerMap">
//    <entry key="INFO" value-ref="EventLoggerConsole" />
//    <entry key="ERROR" value-ref="EventLoggerFileCache"/>
//    </util:map>

    @Bean
    @Scope("prototype")
    Event event(){
        return new Event(new Date(), dateFormat());
    }

    DateFormat dateFormat(){
        return DateFormat.getDateInstance();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
//@Autowired
//private EventLogger combinedLogger;
//Java