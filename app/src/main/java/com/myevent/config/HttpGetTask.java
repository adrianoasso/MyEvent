package com.myevent.config;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        String stringaFinale = "";
        ArrayList<String> results = new ArrayList<>();

        //http post
        try{
            URLConnection connection = new URL("http://myevent.16mb.com/events.php").openConnection();

            InputStream response = connection.getInputStream();
            String result = getStringFromInputStream(response);

            try{
                JSONArray jArray = new JSONArray(result);
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    Log.i("TEST",  "IdEvents: "+json_data.getString("idEvents")+
                                    ", Nome: "+json_data.getString("Nome")
                    );
                    stringaFinale = json_data.getString("idEvents") + " " + json_data.getString("Nome") + "\n\n";
                    results.add(stringaFinale);

                }
            }
            catch(JSONException e){
                Log.e("log_tag", "Error parsing data "+e.toString());
            }

        }catch(Exception e){
            Log.e("TEST", "Errore nella connessione http " + e.toString());
        }

        return results;
    }

    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }
}
