package com.ffdev.messageDBdemo.ui;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by F.Florio on 12/01/15.
 * floriofrancesco@gmail.com
 */
public class MessageCursorAdapter extends CursorAdapter {

    private final SimpleDateFormat df;
    public MessageCursorAdapter(Context context) {
        super(context, null, false);
        df = new SimpleDateFormat("HH.mm.ss");
    }

    @Override public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, null);
    }

    @Override public void bindView(View view, Context context, Cursor cursor) {
        //Get msg
        final String msg = cursor.getString(1);

        //Get timestamp
        final Date d = new Date(cursor.getLong(2));
        final String timestamp = df.format(d);

        ViewHolder vh = (ViewHolder)view.getTag();
        if(vh == null){
            vh = new ViewHolder();
            vh.t1 = (TextView) view.findViewById(android.R.id.text1);
            vh.t2 = (TextView) view.findViewById(android.R.id.text2);
            view.setTag(vh);
        }
        vh.t1.setText(msg);
        vh.t2.setText(timestamp);
    }

    class ViewHolder{
        TextView t1, t2;
    }


}
