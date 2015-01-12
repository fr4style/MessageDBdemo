package com.ffdev.messageDBdemo.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.ffdev.messageDBdemo.utils.MessageObserver;

/**
 * Created by F.Florio on 12/01/15.
 * floriofrancesco@gmail.com
 */
public class MessageGeneratorTask extends AsyncTask<Integer, String, Void> {
    private MessageObserver observer;
    private boolean isStopped = false;

    public MessageGeneratorTask(MessageObserver observer){ this.observer = observer; }

    @Override protected Void doInBackground(Integer... params) {
        int i = (params.length > 0) ? params[0] : 0;
        Log.w("F_F", "#i: "+i);
        while (!isStopped){
            try{
                publishProgress("Ricevuto msg #"+(++i));
                Thread.sleep(((int)(Math.random()*3000)));

            }
            catch (Exception e){ Log.w("F_F", ""); }
        }
        return null;
    }

    @Override protected void onProgressUpdate(String... values) {
        Log.d("F_F", "#Update -> "+values[0]);
        observer.onMessageReceived(values[0]);
    }

    public void stopTask(){ isStopped = true; }
}
