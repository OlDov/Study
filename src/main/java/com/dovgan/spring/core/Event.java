package com.dovgan.spring.core;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * Created by alexdovgan on 12/20/15.
 */
@Component
public class Event {
    private int id;
    private String msg;
    private Date date;
    private DateFormat df;

    @Override
    public String toString() {
        return "id=" + id + " date=" + df.format(date) + " msg=" + msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Event(Date date, DateFormat df) {
        id = (int) (Math.random() * 100 );
        this.date = date;
        this.df = df;
    }

    public String getMsg() {
        return msg;
    }


}
