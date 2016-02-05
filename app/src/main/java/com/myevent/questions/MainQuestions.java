package com.myevent.questions;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.myevent.R;

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

    }



}
