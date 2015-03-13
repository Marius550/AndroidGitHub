package com.example.android.navigationdrawerexample;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by mariuspilgrim on 12/03/15.
 */
public class ContactFragmentActivity extends Fragment {

    public static final String ARG_CONTACT_NUMBER = "CONTACT_number";

    public final static String EXTRA_MESSAGE_FIRST_NAME = "com.example.android.navigationdrawerexample.MESSAGE_FIRST_NAME";
    public final static String EXTRA_MESSAGE_LAST_NAME = "com.example.android.navigationdrawerexample.MESSAGE_LAST_NAME";
    public final static String EXTRA_MESSAGE_EMAIL = "com.example.android.navigationdrawerexample.MESSAGE_EMAIL";

    public ContactFragmentActivity() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_contact, container, false); //final View?
        int i = getArguments().getInt(ARG_CONTACT_NUMBER);
        String menuItem = getResources().getStringArray(R.array.menu_items_array)[i];
        getActivity().setTitle(menuItem);

        Button button_setDefaultInputValues = (Button) rootView.findViewById(R.id.btn_setDefaultInputValues); //final Button?
        button_setDefaultInputValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDefaultInputValues(rootView);//Finally not null
                }
            });
        Button button_sendMessage = (Button) rootView.findViewById(R.id.btn_sendMessage);
        button_sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(rootView);
            }
        });
        return rootView;
    }

    /**
     * Called when the user clicks the onClick="sendMessage" button
     * @param rootView
     */
    public void sendMessage(View rootView) {
        //Do something in response to button
        try {
            Intent intent = new Intent(getActivity(), ContactResultFragmentActivity.class); //getActivity() must be inserted instead of this

            EditText editTextFirstName = (EditText) rootView.findViewById(R.id.edit_first_name);
            EditText editTextLastName = (EditText) rootView.findViewById(R.id.edit_last_name);
            EditText editTextEmail = (EditText) rootView.findViewById(R.id.edit_email);

            String messageFirstName = editTextFirstName.getText().toString();
            String messageLastName = editTextLastName.getText().toString();
            String messageEmail = editTextEmail.getText().toString();

            intent.putExtra(EXTRA_MESSAGE_FIRST_NAME, messageFirstName);
            intent.putExtra(EXTRA_MESSAGE_LAST_NAME, messageLastName);
            intent.putExtra(EXTRA_MESSAGE_EMAIL, messageEmail);

            startActivity(intent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Called when the user clicks the onClick="setDefaultInputValues" button
     * @param rootView
     */
    public void setDefaultInputValues(View rootView) {
        EditText editTextFirstName = (EditText) rootView.findViewById(R.id.edit_first_name);
        EditText editTextLastName = (EditText) rootView.findViewById(R.id.edit_last_name);
        EditText editTextEmail = (EditText) rootView.findViewById(R.id.edit_email);

        editTextFirstName.setText("Max");
        editTextLastName.setText("Mustermann");
        editTextEmail.setText("max.mustermann@gmail.de");
    }

}