package com.example.android.navigationdrawerexample;

import android.app.Activity;
import android.app.Fragment;
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

//public abstract class ContactFragmentActivity extends Fragment implements View.OnClickListener{

public class ContactFragmentActivity extends Fragment {

    public static final String ARG_CONTACT_NUMBER = "CONTACT_number";

    public ContactFragmentActivity() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact, container, false); //final View?
        int i = getArguments().getInt(ARG_CONTACT_NUMBER);
        String menuItem = getResources().getStringArray(R.array.menu_items_array)[i];
        getActivity().setTitle(menuItem);

        System.out.println("RootView_1 NotNull: " + rootView.findViewById(R.id.edit_first_name));

        Button button = (Button) rootView.findViewById(R.id.btn_setDefaultInputValues); //final Button?
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("OnClick_2 Null: " + v.findViewById(R.id.edit_first_name));

                EditText editTextFirstName = (EditText) v.findViewById(R.id.edit_first_name);
                //editTextFirstName.setText("Max");
                }
            });
        return rootView;
    }

    /*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_contact);
    }
    */


    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    */

    /*
    public final static String EXTRA_MESSAGE_FIRST_NAME = "com.example.android.navigationdrawerexample.MESSAGE_FIRST_NAME";
    public final static String EXTRA_MESSAGE_LAST_NAME = "com.example.android.navigationdrawerexample.MESSAGE_LAST_NAME";
    public final static String EXTRA_MESSAGE_EMAIL = "com.example.android.navigationdrawerexample.MESSAGE_EMAIL";
   */

    /**
     * Called when the user clicks the onClick="setDefaultInputValues" button
     * @param view
     */

            /*
    public void setDefaultInputValuesNotMain(View view) {

        System.out.print("setDefaultInputValuesNotMain: " + view.toString());




        EditText editTextFirstName = (EditText) view.findViewById(R.id.edit_first_name);
        EditText editTextLastName = (EditText) view.findViewById(R.id.edit_last_name);
        EditText editTextEmail = (EditText) view.findViewById(R.id.edit_email);

        editTextFirstName.setText("Max");
        editTextLastName.setText("Mustermann");
        editTextEmail.setText("max.mustermann@gmail.de");


    }
            */

}

