package com.myevent.dao;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by tonyasso on 07/02/16.
 */
public class GetDataDB {


    protected static String getStringFromInputStream(InputStream is) {

        String stringaFinale = "";
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
            return sb.toString();
        }
    }

    protected static ArrayList<Events> getJSONFromBuffer(String buffer){
        ArrayList<Events> events = new ArrayList<Events>();
        Events event;
            try {
                JSONArray jArray = new JSONArray(buffer);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    event = new Events();

                    event.setIdEvents(json_data.getInt("idEvents"));
                    event.setNome(json_data.getString("Nome"));
                    event.setData(json_data.getString("Data"));
                    event.setDescrizione(json_data.getString("Descrizione"));
                    event.setFlagEsist(json_data.getString("FlagEsist"));
                    event.setFlagPassato(json_data.getString("FlagPassato"));
                    event.setDataUltMod(json_data.getString("DataUltMod"));
                    event.setUtenteUltMod(json_data.getString("UtenteUltMod"));

                    events.add(event);
                }
            } catch (JSONException e) {
                Log.e("log_tag", "Error parsing data " + e.toString());
            }


            return events;
        }

    }


