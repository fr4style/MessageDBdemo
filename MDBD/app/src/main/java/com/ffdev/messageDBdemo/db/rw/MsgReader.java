package com.ffdev.messageDBdemo.db.rw;

import android.database.Cursor;

/**
 * Created by F.Florio on 12/01/15.
 * floriofrancesco@gmail.com
 */
public class MsgReader {

    private final DatabaseReader dbReader;

    public MsgReader(DatabaseReader dbReader){ this.dbReader = dbReader; }

    public Cursor getAllMessages(){
        return dbReader.getAllFrom(DatabaseConstants.TABLE_MESSAGES);
    }

}
