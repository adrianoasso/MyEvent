package com.myevent.dao;

import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class HttpGetTask extends AsyncTask {

    private static String HOST = "http://myevent.16mb.com/";
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

    @Override
    protected ArrayList doInBackground(Object[] params) {
        ArrayList<Events> results = new ArrayList<>();
        //http post
        try{
            if (params[0] == "ESTRAI_EVENTI") {
                URLConnection connection = new URL(HOST + "events.php").openConnection();

                InputStream response = connection.getInputStream();
                results = GetDataDB.getStringFromInputStream(response);
            } else if (params[0] == "INS_EVENTO") {
                URLConnection connection = new URL(HOST + "insevent.php").openConnection();
                connection.setRequestProperty("nome", params[1].toString());
                connection.setRequestProperty("data", params[2].toString());
                connection.setRequestProperty("descrizione", params[3].toString());
            }


        }catch(Exception e){
            Log.e("TEST", "Errore nella connessione http " + e.toString());
        } finally {
            return results;
        }

    }


}