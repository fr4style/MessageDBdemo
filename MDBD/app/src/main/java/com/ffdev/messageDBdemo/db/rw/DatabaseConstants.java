package com.ffdev.messageDBdemo.db.rw;

/**
 * Created by F.Florio on 12/01/15.
 * floriofrancesco@gmail.com
 */
public class DatabaseConstants {

    private DatabaseConstants() {}

    public static final String TABLE_MESSAGES = "messages";

    public static class Messages{
        public static final String COL_MESSAGE = "message";
        public static final String COL_TIMESTAMP = "ts";
    }

}
