package com.example.android.navigationdrawerexample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

public class ContactResultFragmentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.fragment_contact_results);

            Intent intent = getIntent();
            String messageFirstName = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_FIRST_NAME);
            String messageLastName = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_LAST_NAME);
            String messageEmail = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_EMAIL);

            TextView textViewFirstName = (TextView) findViewById(R.id.result_first_name);
            TextView textViewLastName = (TextView) findViewById(R.id.result_last_name);
            TextView textViewEmail = (TextView) findViewById(R.id.result_email);

            textViewFirstName.append("First Name: " + messageFirstName + "\n");
            textViewLastName.append("Last Name: " + messageLastName + "\n");
            textViewEmail.append("Email: " + messageEmail + "\n");

            //throw new RuntimeException(); //triggers Exception
        } catch (Exception ex) {
            String messageBoxError = "onCreate error";
            messageBox(messageBoxError, ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Creating exception handling box
     * @param method
     * @param message
     */
    private void messageBox(String method, String message) {
        AlertDialog.Builder messageBox = new AlertDialog.Builder(this);
        messageBox.setTitle(method);
        messageBox.setMessage(message);
        messageBox.setCancelable(false);
        messageBox.setNeutralButton("OK", null);
        messageBox.show();
    }

}