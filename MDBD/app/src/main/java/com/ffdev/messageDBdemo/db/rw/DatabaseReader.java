package com.ffdev.messageDBdemo.db.rw;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by F.Florio on 12/01/15.
 * floriofrancesco@gmail.com
 */
public class DatabaseReader {

    private final ContentResolver contentResolver;
    private final String AUTHORITY;

    public DatabaseReader(String AUTHORITY, ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
        this.AUTHORITY = AUTHORITY;
    }

    /**
     * (1) Read - generic table support
     */
    protected Cursor getAllFrom(String tableName) {
        Uri uri = createUri(tableName);
        return contentResolver.query(uri, null, null, null, null);
    }

    private Uri createUri(String path) {
        return Uri.parse(AUTHORITY + path);
    }
}
