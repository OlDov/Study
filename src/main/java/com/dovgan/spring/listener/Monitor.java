package com.dovgan.spring.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by alexdovgan on 12/23/15.
 */
public class Monitor implements ApplicationListener{
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("event: "+applicationEvent.toString());
    }
}
