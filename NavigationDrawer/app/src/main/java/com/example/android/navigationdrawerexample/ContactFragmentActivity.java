package com.example.android.navigationdrawerexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by mariuspilgrim on 12/03/15.
 */
public class ContactFragmentActivity extends Activity {

    /*
    public final static String EXTRA_MESSAGE_FIRST_NAME = "com.example.android.navigationdrawerexample.MESSAGE_FIRST_NAME";
    public final static String EXTRA_MESSAGE_LAST_NAME = "com.example.android.navigationdrawerexample.MESSAGE_LAST_NAME";
    public final static String EXTRA_MESSAGE_EMAIL = "com.example.android.navigationdrawerexample.MESSAGE_EMAIL";
    */

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

    /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        Button button = (Button) view.findViewById(R.id.btn_setDefaultInputValues);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // do something
            }
        });
        return view;
    }

    /**
     * Called when the user clicks the onClick="setDefaultInputValues" button
     * @param view
     */
    /*
    public void setDefaultInputValuesNotMain(View view) {

        System.out.print("setDefaultInputValuesNotMain");

        EditText editTextFirstName = (EditText) findViewById(R.id.edit_first_name);
        EditText editTextLastName = (EditText) findViewById(R.id.edit_last_name);
        EditText editTextEmail = (EditText) findViewById(R.id.edit_email);

        editTextFirstName.setText("Max");
        editTextLastName.setText("Mustermann");
        editTextEmail.setText("max.mustermann@gmail.de");
    }
    */

}
