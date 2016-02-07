package com.myevent.config;

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


    protected static ArrayList<String> getStringFromInputStream(InputStream is) {

        String stringaFinale = "";
        ArrayList<String> results = new ArrayList<>();
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

        try{
            JSONArray jArray = new JSONArray(sb.toString());
            for(int i=0;i<jArray.length();i++){
                JSONObject json_data = jArray.getJSONObject(i);

                Log.i("TEST", "IdEvents: " + json_data.getString("idEvents") +
                                ", Nome: " + json_data.getString("Nome")
                );

                stringaFinale = json_data.getString("idEvents") + " " + json_data.getString("Nome") + "\n\n";
                results.add(stringaFinale);

            }
        }
        catch(JSONException e){
            Log.e("log_tag", "Error parsing data "+e.toString());
        }


        return results;

    }

        }
