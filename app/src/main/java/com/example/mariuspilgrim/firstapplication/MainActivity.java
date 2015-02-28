package com.example.mariuspilgrim.firstapplication;

import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

import java.net.URI;


public class MainActivity extends ActionBarActivity {

    public final static String EXTRA_MESSAGE_NAME = "com.example.mariuspilgrim.firstapplication.MESSAGE_NAME";
    public final static String EXTRA_MESSAGE_EMAIL = "com.example.mariuspilgrim.firstapplication.MESSAGE_EMAIL";
    public final static String EXTRA_MESSAGE_PHONE = "com.example.mariuspilgrim.firstapplication.MESSAGE_PHONE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    switch (item.getItemId()) {
        case R.id.action_search:
            //openSearch();
            System.out.println("Need to define search()");
            return true;
        case R.id.action_settings:
            openAndroidSettings();
            return true;
        case R.id.action_browser:
            openAndroidBrowser();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Called when the user clicks the Send button
     * @param view
     */
    public void sendMessage(View view) {
        //Do something in response to button
        Intent intent = new Intent (this, DisplayMessageActivity.class);

        EditText editTextName = (EditText) findViewById(R.id.edit_name);
        EditText editTextEmail = (EditText) findViewById(R.id.edit_email);
        EditText editTextPhone = (EditText) findViewById(R.id.edit_phone);

        String messageName = editTextName.getText().toString();
        String messageEmail = editTextEmail.getText().toString();
        String messagePhone = editTextPhone.getText().toString();

        intent.putExtra(EXTRA_MESSAGE_NAME, messageName);
        intent.putExtra(EXTRA_MESSAGE_EMAIL, messageEmail);
        intent.putExtra(EXTRA_MESSAGE_PHONE, messagePhone);
        startActivity(intent);
    }

    /**
     * Set input elements to default values for Elena
     * @param view
     */
    public void setDefaultInputValues(View view) {
        EditText editTextName = (EditText) findViewById(R.id.edit_name);
        EditText editTextEmail = (EditText) findViewById(R.id.edit_email);
        EditText editTextPhone = (EditText) findViewById(R.id.edit_phone);

        editTextName.setText("Elena");
        editTextEmail.setText("elena.enbrecht@gmail.de");
        editTextPhone.setText("0167 1712131243");
    }

    /**
     * Opens Android device settings
     */
    public void openAndroidSettings() {
        startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
    }

    /**
     * Opens Android device browser
     */
    public void openAndroidBrowser() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        startActivity(browserIntent);
    }

}
