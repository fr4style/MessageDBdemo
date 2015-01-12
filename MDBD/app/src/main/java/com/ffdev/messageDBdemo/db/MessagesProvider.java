package com.ffdev.messageDBdemo.db;

import android.content.Context;

import com.ffdev.messageDBdemo.R;

import novoda.lib.sqliteprovider.provider.SQLiteContentProviderImpl;

/**
 * Created by F.Florio on 12/01/15.
 * floriofrancesco@gmail.com
 */
public class MessagesProvider extends SQLiteContentProviderImpl {

    public static final String getAuthority(Context ctx){ return String.format("content://%s/", ctx.getString(R.string.db_authority)); }

}
