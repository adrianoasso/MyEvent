package com.myevent.dao;

import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class HttpGetTask extends AsyncTask {

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

    @Override
    protected ArrayList doInBackground(Object[] params) {
        ArrayList<Events> results = new ArrayList<>();
        //http post
        try{
            URLConnection connection = new URL("http://myevent.16mb.com/events.php").openConnection();

            InputStream response = connection.getInputStream();
            results = GetDataDB.getStringFromInputStream(response);

        }catch(Exception e){
            Log.e("TEST", "Errore nella connessione http " + e.toString());
        } finally {
            return results;
        }

    }


}