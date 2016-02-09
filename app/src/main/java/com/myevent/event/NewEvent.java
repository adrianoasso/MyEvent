package com.myevent.event;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.myevent.R;
import com.myevent.dao.Events;
import com.myevent.dao.HttpGetTask;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class NewEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final EditText titoloEvento = (EditText) findViewById(R.id.titoloEvento);
        final EditText dataEvento = (EditText) findViewById(R.id.dataEvento);
        final EditText descrEvento = (EditText) findViewById(R.id.descrEvento);

        FloatingActionButton insertEvent = (FloatingActionButton) findViewById(R.id.fab);
        insertEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    final ArrayList<Events> results = (ArrayList<Events>) new HttpGetTask().execute("INS_EVENTO", titoloEvento.getText(), dataEvento.getText(), descrEvento.getText()).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
