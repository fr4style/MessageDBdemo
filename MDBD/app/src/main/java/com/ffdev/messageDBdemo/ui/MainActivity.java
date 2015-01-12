package com.ffdev.messageDBdemo.ui;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.ffdev.messageDBdemo.utils.MessageObserver;
import com.ffdev.messageDBdemo.MyApplication;
import com.ffdev.messageDBdemo.R;
import com.ffdev.messageDBdemo.db.MessagesProvider;
import com.ffdev.messageDBdemo.db.model.Message;
import com.ffdev.messageDBdemo.db.rw.DatabaseConstants;
import com.ffdev.messageDBdemo.db.rw.MsgReader;
import com.ffdev.messageDBdemo.db.rw.MsgWriter;
import com.ffdev.messageDBdemo.tasks.MessageGeneratorTask;


public class MainActivity extends ActionBarActivity implements MessageObserver,
                                                               LoaderManager.LoaderCallbacks<Cursor> {

    //DB
    private MsgWriter msgWriter;

    //UI
    private ListView list;
    private MessageCursorAdapter adapter;

    //Vars
    private int i = 0;

    private MessageGeneratorTask t0, t1000;

//--------------------------------------------
// Interfaces
//--------------------------------------------
    private ContentObserver observer = new ContentObserver(new Handler()) {
        @Override public void onChange(boolean selfChange) {
            getLoaderManager().restartLoader(0, null, MainActivity.this);
        }
    };


//--------------------------------------------
// Activity lifecycle
//--------------------------------------------
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication app = (MyApplication)getApplication();

        msgWriter = app.getMsgWriter();

        setContentView(R.layout.activity_main);

        list = (ListView)findViewById(R.id.list);
        list.setStackFromBottom(true);
        list.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        adapter = new MessageCursorAdapter(this);
        list.setAdapter(adapter);

        getLoaderManager().initLoader(0, null, this);
        msgWriter.registerContentObserver(observer);
     }

    @Override protected void onResume() {
        super.onResume();
        t0 = new MessageGeneratorTask(this);
        t0.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        t1000 = new MessageGeneratorTask(this);
        t1000.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 1000);
    }

    @Override protected void onPause() {
        super.onPause();
        t0.stopTask();
        t1000.stopTask();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        msgWriter.unregisteredContentObserver(observer);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) { getMenuInflater().inflate(R.menu.menu_main, menu); return true; }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_send) {
            msgWriter.saveMessage(new Message("Messaggio inviato #"+(++i), System.currentTimeMillis()));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//--------------------------
// MessageObserver
//--------------------------
    @Override public void onMessageReceived(String s) {
        msgWriter.saveMessage(new Message(s, System.currentTimeMillis()));
    }

//--------------------------
// LoaderCallback<Cursor>
//--------------------------
    @Override public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri baseUri = Uri.withAppendedPath(Uri.parse(MessagesProvider.getAuthority(getApplicationContext())), Uri.encode(DatabaseConstants.TABLE_MESSAGES));
        return new CursorLoader(this,
                                baseUri,
                                null, null, null, null);
    }

    @Override public void onLoadFinished(Loader<Cursor> loader, Cursor data) { adapter.swapCursor(data); }

    @Override public void onLoaderReset(Loader<Cursor> loader) { adapter.swapCursor(null); }
}
