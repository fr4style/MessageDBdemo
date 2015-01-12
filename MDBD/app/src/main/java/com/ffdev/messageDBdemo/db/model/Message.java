package com.ffdev.messageDBdemo.db.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by F.Florio on 12/01/15.
 * floriofrancesco@gmail.com
 */
public class Message {

    private String msg;
    private long timestamp;

    public Message(String msg, long timestamp) {
        this.msg = msg;
        this.timestamp = timestamp;
    }

    public String getMsg() {
        return msg;
    }

    public long getTimestamp() {
        return timestamp;
    }

}
