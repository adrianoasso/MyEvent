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
import android.widget.RelativeLayout;
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
            RelativeLayout.LayoutParams[] layoutParams = new RelativeLayout.LayoutParams[results.size()];
            ImageButton[] buttonEvent = new ImageButton[results.size()];
            TextView[] textEvent = new TextView[results.size()];
            RelativeLayout mainView = (RelativeLayout) findViewById(R.id.mainLayout);
            //RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            //RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            //RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(400, 400);
            //RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(400, 400);
            //TableRow[] eventRow = new TableRow[results.size()];

            String stringone = "";

            for(int c = 0; c < results.size(); c++){
                int i = 1000 + c;
                layoutParams[c] = new RelativeLayout.LayoutParams(500,500);
                System.out.println("ButtonId: " + c);
                buttonEvent[c] = new ImageButton(this);
                buttonEvent[c].setImageResource(R.drawable.ic_launcher);
                //buttonEvent[c].setLayoutParams(lp);
                //buttonEvent.setOnClickListener(mGreenBallOnClickListener);

                buttonEvent[c].setTag(i);
                buttonEvent[c].setId(i);


                stringone = results.get(c);
                textEvent[c] = new TextView(this);
                textEvent[c].setText(stringone);
                textEvent[c].setTag(i+1000);
                textEvent[c].setId(i + 1000);

               // eventRow[i] = new TableRow(this);
                //eventRow[i].addView(buttonEvent[i]);
                //eventRow[i].addView(textEvent[i]);

                if(c % 2 == 0 ) {
                    if(c == 0) {
                        buttonEvent[c].setBackgroundColor(Color.RED);
                        layoutParams[c].addRule(RelativeLayout.ALIGN_PARENT_START);
                    } else {
                        buttonEvent[c].setBackgroundColor(Color.YELLOW);
                        layoutParams[c].addRule(RelativeLayout.BELOW, buttonEvent[c - 2].getId());
                    }
                }
                if(c % 2 == 1 ) {
                    buttonEvent[c].setBackgroundColor(Color.MAGENTA);
                    layoutParams[c].addRule(RelativeLayout.RIGHT_OF, buttonEvent[c - 1].getId());
                    if(c != 1) {
                        layoutParams[c].addRule(RelativeLayout.BELOW, buttonEvent[c - 2].getId());
                    }
                }

               /* if(c == 2 ) {
                    buttonEvent[c].setBackgroundColor(Color.YELLOW);
                    layoutParams[c].addRule(RelativeLayout.BELOW, buttonEvent[c - 2].getId());
                }
                if(c % 2 == 1 ) {
                    buttonEvent[c].setBackgroundColor(Color.MAGENTA);
                    layoutParams[c].addRule(RelativeLayout.RIGHT_OF, buttonEvent[c - 1].getId());
                }*/

                buttonEvent[c].setLayoutParams(layoutParams[c]);
                textEvent[c].setLayoutParams(layoutParams[c]);
                mainView.addView(buttonEvent[c], layoutParams[c]);
                mainView.addView(textEvent[c], layoutParams[c]);



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
