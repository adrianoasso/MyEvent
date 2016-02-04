package com.myevent.questions;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.myevent.R;
import com.myevent.config.HttpGetTask;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainQuestions extends AppCompatActivity {

    public MainQuestions() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_questions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent mainActivityIntent=getIntent();
        String username=mainActivityIntent.getStringExtra("username");
        final Intent newQuestionIntent = new Intent(this, NewQuestion.class);


        FloatingActionButton newQuestion = (FloatingActionButton) findViewById(R.id.newQuestion);
        newQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(newQuestionIntent);
            }
        });

        try {
            ArrayList<String> results = (ArrayList<String>) new HttpGetTask().execute().get();
            TextView textView = (TextView) findViewById(R.id.username);
            String stringone = "";
            int i = 0;
            while(results.size() != i){
                stringone = stringone + results.get(i) + "\n";
                i++;
            }
            textView.setText(stringone);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



    }



}
