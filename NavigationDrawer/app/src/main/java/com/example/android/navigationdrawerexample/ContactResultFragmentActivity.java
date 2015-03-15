package com.example.android.navigationdrawerexample;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ContactResultFragmentActivity extends Activity {

    public ContactResultFragmentActivity() {
        //MainActivity m = new MainActivity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.fragment_contact_results);

            Intent intent = getIntent();
            String messageFirstName = intent.getStringExtra(ContactFragmentActivity.EXTRA_MESSAGE_FIRST_NAME);
            String messageLastName = intent.getStringExtra(ContactFragmentActivity.EXTRA_MESSAGE_LAST_NAME);
            String messageEmail = intent.getStringExtra(ContactFragmentActivity.EXTRA_MESSAGE_EMAIL);
            String messageMessage= intent.getStringExtra(ContactFragmentActivity.EXTRA_MESSAGE_MESSAGE);

            TextView textViewFirstName = (TextView) findViewById(R.id.result_first_name);
            TextView textViewLastName = (TextView) findViewById(R.id.result_last_name);
            TextView textViewEmail = (TextView) findViewById(R.id.result_email);
            TextView textViewMessage = (TextView) findViewById(R.id.result_message);

            textViewFirstName.append(getResources().getString(R.string.result_first_name) + messageFirstName);
            textViewLastName.append(getResources().getString(R.string.result_last_name) + messageLastName);
            textViewEmail.append(getResources().getString(R.string.result_email) + messageEmail);
            textViewMessage.append(getResources().getString(R.string.result_message) + messageMessage);

            //throw new RuntimeException(); //triggers Exception
        } catch (Exception ex) {
            messageBox(getResources().getString(R.string.error_oncreate_ContactResultFragmentActivity), ex.getMessage());
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action buttons
        switch(item.getItemId()) {
            case R.id.action_websearch:
                // create intent to perform web search for this planet
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
                // catch event that there's no activity to handle intent
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
                }
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

    public void backToSendMessage(View view) {
            setContentView(R.layout.fragment_contact);
            finish();
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