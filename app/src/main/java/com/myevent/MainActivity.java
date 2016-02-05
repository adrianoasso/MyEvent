package com.myevent;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.myevent.config.HttpGetTask;
import com.myevent.event.NewEvent;
import com.myevent.questions.MainQuestions;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Intent mainQuestionsIntent = new Intent(this, MainQuestions.class);
        final Intent newEventIntent = new Intent(this, NewEvent.class);
        AccountManager accountManager = (AccountManager)getSystemService(Context.ACCOUNT_SERVICE);
        final String username;
        Account[] list = AccountManager.get(this).getAccountsByType("com.google");
        username = "NULL";


        System.out.println("list: " + list.length);

        FloatingActionButton newEvent = (FloatingActionButton) findViewById(R.id.newEvent);
        newEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //newEventIntent.putExtra("username", username);
                startActivity(newEventIntent);
            }
        });

        FloatingActionButton question = (FloatingActionButton) findViewById(R.id.question);
        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainQuestionsIntent.putExtra("username", username);
                startActivity(mainQuestionsIntent);
            }
        });

        try {
            ArrayList<String> results = (ArrayList<String>) new HttpGetTask().execute().get();
            ImageButton[] buttonEvent = new ImageButton[results.size()];
            TextView[] textEvent = new TextView[results.size()];
            LinearLayout mainView = (LinearLayout) findViewById(R.id.mainLayout);
            TableLayout.LayoutParams lp = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
            TableRow[] eventRow = new TableRow[results.size()/2];

            String stringone = "";

            for(int i = 0; i < results.size(); i++){
                System.out.println("ButtonId: " + i);
                buttonEvent[i] = new ImageButton(this);
                buttonEvent[i].setImageResource(R.drawable.ic_launcher);
                buttonEvent[i].setLayoutParams(lp);
                //buttonEvent.setOnClickListener(mGreenBallOnClickListener);
                buttonEvent[i].setBackgroundColor(Color.RED);
                buttonEvent[i].setTag(i);
                buttonEvent[i].setId(i);

                stringone = results.get(i);
                textEvent[i] = new TextView(this);
                textEvent[i].setText(stringone);

                if((i % 2) == 0) {
                    eventRow[i] = new TableRow(this);
                    eventRow[i].addView(buttonEvent[i]);
                    eventRow[i].addView(textEvent[i]);
                    mainView.addView(eventRow[i]);
                    System.out.println("if");
                    //lp.(RelativeLayout.ALIGN_PARENT_LEFT);
                    //lp.addRule(RelativeLayout.);
                } else if((i % 2 ) == 1) {

                    //eventRow[i].addView(buttonEvent[i],i);
                    System.out.println("else");
                    //eventRow[i].addView(textEvent[i],i);

                  //  lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                }

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
