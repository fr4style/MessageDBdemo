package com.ffdev.messageDBdemo.db.rw;

import android.content.ContentValues;
import android.database.ContentObserver;

import com.ffdev.messageDBdemo.db.model.Message;

/**
 * Created by F.Florio on 12/01/15.
 * floriofrancesco@gmail.com
 */
public class MsgWriter {

    private final DatabaseWriter dbWriter;

    public MsgWriter(DatabaseWriter dbWriter) {
        this.dbWriter = dbWriter;
    }

    public void saveMessage(Message newMsg) {
        ContentValues values = new ContentValues();
        values.put(DatabaseConstants.Messages.COL_MESSAGE, newMsg.getMsg());
        values.put(DatabaseConstants.Messages.COL_TIMESTAMP, newMsg.getTimestamp());

        dbWriter.saveDataToTable(DatabaseConstants.TABLE_MESSAGES, values);
    }

    public void registerContentObserver(ContentObserver observer) {
        dbWriter.registerContentObserver(DatabaseConstants.TABLE_MESSAGES, observer);
    }

    public void unregisteredContentObserver(ContentObserver observer) {
        dbWriter.unregisterContentObserver(observer);
    }
}