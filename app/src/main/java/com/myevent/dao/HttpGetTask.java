package com.myevent.dao;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
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
        ArrayList results = new ArrayList<>();
        //http post
        try{
            if (params[0] == "ESTRAI_EVENTI") {
                URLConnection connection = new URL(HOST + "events.php").openConnection();
                InputStream response = connection.getInputStream();
                results = GetDataDB.getJSONFromBuffer(GetDataDB.getStringFromInputStream(response));
            } else if (params[0] == "INS_EVENTO") {
                URL url = new URL(HOST + "insevent.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                String urlParameters = "nome=" + params[1].toString() + "&data=" + params[2].toString() + "&descrizione=" + params[3].toString();
                // Send post request
                connection.setDoInput(true);
                connection.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                byte[] postDataBytes = urlParameters.toString().getBytes("UTF-8");
                connection.getOutputStream().write(postDataBytes);

                InputStream response = connection.getInputStream();
                String esito = GetDataDB.getStringFromInputStream(response);
                results.add(esito);
            }


        }catch(Exception e){
            Log.e("TEST", "Errore nella connessione http " + e.toString());
        } finally {
            return results;
        }

    }


}