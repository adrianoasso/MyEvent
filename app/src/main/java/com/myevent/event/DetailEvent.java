package com.myevent.event;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.myevent.R;

public class DetailEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i=getIntent();
        setTitle(i.getStringExtra("nome"));
        //String nome=i.getStringExtra("nome");
        String data=i.getStringExtra("data");
        String descrizione=i.getStringExtra("descrizione");

        //TextView nomeEvento = (TextView) findViewById(R.id.nomeEvento);
        TextView dataEvento = (TextView) findViewById(R.id.dataEvento);
        TextView descrEvento = (TextView) findViewById(R.id.descrEvento);

        //nomeEvento.setText(nome);
        dataEvento.setText(data);
        descrEvento.setText(descrizione);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }
}
