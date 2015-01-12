package com.ffdev.messageDBdemo.db.rw;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.net.Uri;

/**
 * Created by F.Florio on 12/01/15.
 * floriofrancesco@gmail.com
 */
public class DatabaseWriter {

    private final ContentResolver contentResolver;
    private final String AUTHORITY;

    public DatabaseWriter(String AUTHORITY, ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
        this.AUTHORITY = AUTHORITY;
    }


    protected void saveDataToTable(String table, ContentValues values) {
        Uri uri = createUri(table);
        contentResolver.insert(uri, values);
        contentResolver.notifyChange(uri, null);
    }

    private Uri createUri(String path) {
        return Uri.parse(AUTHORITY + path);
    }

    protected void registerContentObserver(String table, ContentObserver observer){
        Uri uri = createUri(table);
        contentResolver.registerContentObserver(uri, true, observer);
    }
    protected void unregisterContentObserver(ContentObserver observer){  contentResolver.unregisterContentObserver(observer); }
}
