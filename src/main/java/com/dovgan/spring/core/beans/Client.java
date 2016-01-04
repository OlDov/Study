package com.dovgan.spring.core.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by alexdovgan on 12/18/15.
 */
@Component
public class Client {
    @Value("${id}")
    private Integer id;
    @Value("${name}")
    private String name;
    @Value("hello there!")
    private String greeting;

    public Client() {
        System.out.println("Client created");
    }

    public Client(Integer id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("Client " + id + " " + name + "created");
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

}
