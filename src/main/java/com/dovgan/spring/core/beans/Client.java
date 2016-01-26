package com.dovgan.spring.core.beans;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by alexdovgan on 12/18/15.
 */
@Setter
@Getter
public class Client {
    private Integer id;
    private String name;
    private String greeting;

    public Client() {
        System.out.println("Client created");
    }

    public Client(Integer id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("Client " + id + " " + name + "created");
    }
//
//    public String getGreeting() {
//        return greeting;
//    }
//
//    public void setGreeting(String greeting) {
//        this.greeting = greeting;
//    }

}
