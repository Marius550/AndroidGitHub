package com.example.android.navigationdrawerexample;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by mariuspilgrim on 12/03/15.
 */
public class ContactFragmentActivity extends Activity {

    public final static String EXTRA_MESSAGE_NAME = "com.example.android.navigationdrawerexample.MESSAGE_NAME";
    public final static String EXTRA_MESSAGE_EMAIL = "com.example.android.navigationdrawerexample.MESSAGE_EMAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_contact);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    /*
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    */

    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
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
    */



    /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contact,
                container, false);
        Button button = (Button) view.findViewById(R.id.btn_setDefaultInputValues);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // do something
                setDefaultInputValues(v);
            }
        });
        return view;
    }
    */

    /**
     * Called when the user clicks the Send button
     * @param view
     */
    public void sendMessage(View view) {
        //Do something in response to button
        Intent intent = new Intent (this, ContactResultFragmentActivity.class);

        EditText editTextName = (EditText) findViewById(R.id.edit_name);
        EditText editTextEmail = (EditText) findViewById(R.id.edit_email);

        String messageName = editTextName.getText().toString();
        String messageEmail = editTextEmail.getText().toString();

        intent.putExtra(EXTRA_MESSAGE_NAME, messageName);
        intent.putExtra(EXTRA_MESSAGE_EMAIL, messageEmail);
        startActivity(intent);
    }

    /**
     * Set input elements to default values for Elena
     * @param view
     */

    public void setDefaultInputValues(View view) {
        System.out.println("setDefaultInputValues view: " + view.toString());

        EditText editTextName = (EditText) findViewById(R.id.edit_name);
        EditText editTextEmail = (EditText) findViewById(R.id.edit_email);

        editTextName.setText("Elena");
        editTextEmail.setText("elena.enbrecht@gmail.de");
    }


}
