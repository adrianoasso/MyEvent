package com.myevent.event;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.myevent.R;
import com.myevent.dao.Events;
import com.myevent.dao.HttpGetTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class NewEvent extends AppCompatActivity implements View.OnClickListener {

    private EditText dataEventotxt;
    private DatePickerDialog dataEventoPickerDialog;

    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final EditText titoloEvento = (EditText) findViewById(R.id.titoloEvento);
        final EditText descrEvento = (EditText) findViewById(R.id.descrEvento);

        dataEventotxt = (EditText) findViewById(R.id.dataEvento);
        dataEventotxt.setInputType(InputType.TYPE_NULL);
        setDateTimeField();

        dataEventotxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (v == dataEventotxt && hasFocus == true) {
                    dataEventoPickerDialog.show();
                }
            }
        });

        dataEventotxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == dataEventotxt) {
                    dataEventoPickerDialog.show();
                }
            }
        });

        FloatingActionButton insertEvent = (FloatingActionButton) findViewById(R.id.fab);
        insertEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    final ArrayList results = (ArrayList<Events>) new HttpGetTask().execute("INS_EVENTO", titoloEvento.getText(), dataEventotxt.getText(), descrEvento.getText()).get();
                    if (results.get(0).toString().equalsIgnoreCase("0")) {
                        Snackbar.make(view, "L'evento è stato inserito!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setDateTimeField() {
        dataEventotxt.setOnClickListener(this);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        Calendar newCalendar = Calendar.getInstance();
        dataEventoPickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dataEventotxt.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }


    @Override
    public void onClick(View v) {
        /*if(v == dataEventotxt) {
            dataEventoPickerDialog.show();
        }*/
    }
}
