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


    protected static ArrayList<Events> getStringFromInputStream(InputStream is) {

        String stringaFinale = "";
        //ArrayList<String> results = new ArrayList<>();
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        ArrayList<Events> events = new ArrayList<Events>();
        Events event;

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

        try{
            JSONArray jArray = new JSONArray(sb.toString());
            for(int i=0;i<jArray.length();i++){
                JSONObject json_data = jArray.getJSONObject(i);

                Log.i("TEST", "IdEvents: " + json_data.getString("idEvents") +
                        ", Nome: " + json_data.getString("Nome"));

                event = new Events();

                event.setIdEvents(json_data.getInt("idEvents"));
                event.setNome(json_data.getString("Nome"));
                event.setData(json_data.getString("Data"));
                event.setDescrizione(json_data.getString("Descrizione"));
                event.setFlagEsist(json_data.getString("FlagEsist"));
                event.setFlagPassato(json_data.getString("FlagPassato"));
                event.setDataUltMod(json_data.getString("DataUltMod"));
                event.setUtenteUltMod(json_data.getString("UtenteUltMod"));

                //stringaFinale = json_data.getString("idEvents") + " " + json_data.getString("Nome") + "\n\n";
                //results.add(stringaFinale);
                events.add(event);
                System.out.println("Array eventi: " + events.get(i).getNome());
            }
        }
        catch(JSONException e){
            Log.e("log_tag", "Error parsing data "+e.toString());
        }


        return events;

    }

        }
