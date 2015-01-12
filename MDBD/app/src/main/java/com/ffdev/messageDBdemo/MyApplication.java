package com.ffdev.messageDBdemo;

import android.app.Application;

import com.ffdev.messageDBdemo.db.MessagesProvider;
import com.ffdev.messageDBdemo.db.rw.DatabaseReader;
import com.ffdev.messageDBdemo.db.rw.DatabaseWriter;
import com.ffdev.messageDBdemo.db.rw.MsgReader;
import com.ffdev.messageDBdemo.db.rw.MsgWriter;

/**
 * Created by F.Florio on 12/01/15.
 * floriofrancesco@gmail.com
 */
public class MyApplication extends Application {

    private DatabaseReader dbReader;
    private DatabaseWriter dbWriter;
    private MsgReader msgReader;
    private MsgWriter msgWriter;

    private DatabaseReader getDbReader(){
        if(dbReader == null) dbReader = new DatabaseReader(MessagesProvider.getAuthority(getApplicationContext()),
                                                           getContentResolver());
        return dbReader;
    }

    private DatabaseWriter getDbWriter(){
        if(dbWriter == null) dbWriter = new DatabaseWriter(MessagesProvider.getAuthority(getApplicationContext()),
                                                           getContentResolver());
        return dbWriter;
    }

    public MsgReader getMsgReader(){
        if(msgReader == null) msgReader = new MsgReader(getDbReader());

        return  msgReader;
    }

    public MsgWriter getMsgWriter(){
        if(msgWriter == null) msgWriter = new MsgWriter(getDbWriter());

        return  msgWriter;
    }
}
