package com.example.mariuspilgrim.firstapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;


public class DisplayMessageActivity extends ActionBarActivity {

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.activity_display_message);

            Intent intent = getIntent();
            String messageName = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_NAME);
            String messageEmail = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_EMAIL);
            String messagePhone = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_PHONE);

            TextView textViewName = (TextView) findViewById(R.id.result_name);
            TextView textViewEmail = (TextView) findViewById(R.id.result_email);
            TextView textViewPhone = (TextView) findViewById(R.id.result_phone);

            textViewName.append("Name: " + messageName + "\n");
            textViewEmail.append("Email: " + messageEmail + "\n");
            textViewPhone.append("Phone: " + messagePhone + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    /**
     * Change image from pug1 to pug2
     * method involves exception handling
     * @param view
     */
    public void changePicture(View view) {
        try {
            image = (ImageView) findViewById(R.id.imageView_pug1);
            image.setImageResource(R.drawable.pug2);
        } catch (Exception e) {
            messageBox("changePicture", e.getMessage());
        }
    }

    /**
     * Creating exception handling box
     * @param method
     * @param message
     */
    private void messageBox(String method, String message) {

        Log.d("Exception: " + method, message);

        AlertDialog.Builder messageBox = new AlertDialog.Builder(this);
        messageBox.setTitle(method);
        messageBox.setMessage(message);
        messageBox.setCancelable(false);
        messageBox.setNeutralButton("OK", null);
        messageBox.show();
    }

}

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_message, menu);
        return true;
    }
    */

        /*
        //Get the message from the intent
        Intent intent = getIntent();
        String messageName = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_NAME);
        String messageEmail = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_EMAIL);
        String messagePhone = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_PHONE);

        //Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(20);

        textView.setText("Name: " + messageName);
        textView.setText("Email: " + messageEmail);
        textView.setText("Phone: " + messagePhone);

        //Set the text view as the activity layout
        setContentView(textView);
        */