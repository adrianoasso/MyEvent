package com.myevent;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.myevent.event.NewEvent;
import com.myevent.questions.MainQuestions;

import static java.security.AccessController.getContext;

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
