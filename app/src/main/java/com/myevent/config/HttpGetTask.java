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

public class HttpGetTask extends AsyncTask {

    @Override
    protected Object doInBackground(Object[] params) {
        //String result = "";
        String stringaFinale = "";
        /*ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("idnomerichiesto","1"));
        InputStream is = null;*/

        //http post
        try{
            URLConnection connection = new URL("http://myevent.16mb.com/viewusers.php").openConnection();

            InputStream response = connection.getInputStream();

            String result = getStringFromInputStream(response);

            System.out.println(result);
            System.out.println("Done");

            try{
                JSONArray jArray = new JSONArray(result);
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    Log.i("TEST",  "IdEvents: "+json_data.getString("idEvents")+
                                    ", Nome: "+json_data.getString("Nome")
                    );
                    stringaFinale = json_data.getString("idEvents") + " " + json_data.getString("Nome") + "\n\n";
                }
            }
            catch(JSONException e){
                Log.e("log_tag", "Error parsing data "+e.toString());
            }

/*
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://192.168.0.2:8080/richiestaInfo.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();*/
        }catch(Exception e){
            Log.e("TEST", "Errore nella connessione http " + e.toString());
        }
        /*if(is != null){
            //converto la risposta in stringa
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();

                result=sb.toString();
            }catch(Exception e){
                Log.e("TEST", "Errore nel convertire il risultato "+e.toString());
            }

            System.out.println(result);

            //parsing dei dati arrivati in formato json
            try{
                JSONArray jArray = new JSONArray(result);
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    Log.i("TEST","id: "+json_data.getInt("id")+
                                    ", cognome: "+json_data.getString("cognome")+
                                    ", nascita: "+json_data.getInt("anno")
                    );
                    stringaFinale = json_data.getInt("id") + " " + json_data.getString("cognome") + " " + json_data.getInt("anno") + "\n\n";
                }
            }
            catch(JSONException e){
                Log.e("log_tag", "Error parsing data "+e.toString());
            }
        }
        else{//is è null e non ho avuto risposta

        }*/

        return stringaFinale;
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
