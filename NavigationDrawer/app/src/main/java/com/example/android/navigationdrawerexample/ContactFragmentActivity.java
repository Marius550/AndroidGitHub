package com.example.android.navigationdrawerexample;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mariuspilgrim on 12/03/15.
 */
public class ContactFragmentActivity extends Fragment {

    public static final String ARG_CONTACT_NUMBER = "CONTACT_number";

    public final static String EXTRA_MESSAGE_FIRST_NAME = "com.example.android.navigationdrawerexample.MESSAGE_FIRST_NAME";
    public final static String EXTRA_MESSAGE_LAST_NAME = "com.example.android.navigationdrawerexample.MESSAGE_LAST_NAME";
    public final static String EXTRA_MESSAGE_EMAIL = "com.example.android.navigationdrawerexample.MESSAGE_EMAIL";
    public final static String EXTRA_MESSAGE_MESSAGE = "com.example.android.navigationdrawerexample.MESSAGE_MESSAGE";

    public ContactFragmentActivity() {
        // Empty constructor required for fragment subclasses

        //Testing method invocation of class Utilities
        Utilities u = new Utilities();
        u.testMethodExternal();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_contact, container, false); //final View?
        int i = getArguments().getInt(ARG_CONTACT_NUMBER);
        String menuItem = getResources().getStringArray(R.array.menu_items_array)[i];
        getActivity().setTitle(menuItem);

        TextView textView_welcome = (TextView) rootView.findViewById(R.id.welcome_info);
        textView_welcome.setTextColor(Color.parseColor("#852339"));
        textView_welcome.setText(getResources().getText(R.string.welcome_info));

            Button button_setDefaultInputValues = (Button) rootView.findViewById(R.id.btn_setDefaultInputValues); //final Button?
            button_setDefaultInputValues.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                    setDefaultInputValues(rootView);//Finally not null
                    } catch (NullPointerException ex) {
                        messageBox("setDefaultInputValues, set to rootview?",ex.toString());
                    }
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
    public Boolean sendMessage(View rootView) {
        //Do something in response to button
        try {
            Intent intent = new Intent(getActivity(), ContactResultFragmentActivity.class); //getActivity() must be inserted instead of this

            EditText editTextFirstName = (EditText) rootView.findViewById(R.id.edit_first_name);
            EditText editTextLastName = (EditText) rootView.findViewById(R.id.edit_last_name);
            EditText editTextEmail = (EditText) rootView.findViewById(R.id.edit_email);
            EditText editTextMessage = (EditText) rootView.findViewById(R.id.edit_message);

            String messageFirstName = editTextFirstName.getText().toString();
            String messageLastName = editTextLastName.getText().toString();
            String messageEmail = editTextEmail.getText().toString();
            String messageMessage = editTextMessage.getText().toString();

            if(isEmailValid(messageEmail)) {
            } else {
                messageBox( getResources().getString(R.string.edit_email_incorrect_title),
                            getResources().getString(R.string.edit_email_incorrect));
                return false;
            }

            intent.putExtra(EXTRA_MESSAGE_FIRST_NAME, messageFirstName);
            intent.putExtra(EXTRA_MESSAGE_LAST_NAME, messageLastName);
            intent.putExtra(EXTRA_MESSAGE_EMAIL, messageEmail);
            intent.putExtra(EXTRA_MESSAGE_MESSAGE, messageMessage);

            startActivity(intent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  true;
    }

    public boolean isEmailValid(String email)
    {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
    }

    /**
     * Called when the user clicks the onClick="setDefaultInputValues" button
     * @param rootView
     */
    public void setDefaultInputValues(View rootView) {
        EditText editTextFirstName = (EditText) rootView.findViewById(R.id.edit_first_name);
        EditText editTextLastName = (EditText) rootView.findViewById(R.id.edit_last_name);
        EditText editTextEmail = (EditText) rootView.findViewById(R.id.edit_email);
        EditText editTextMessage = (EditText) rootView.findViewById(R.id.edit_message);

        editTextFirstName.setText(getResources().getString(R.string.default_first_name));
        editTextLastName.setText(getResources().getString(R.string.default_last_name));
        editTextEmail.setText(getResources().getString(R.string.default_email));
        editTextMessage.setText(getResources().getString(R.string.default_message));
    }

    /**
     * Creating exception handling box
     * @param method
     * @param message
     */
    public void messageBox(String method, String message) {
        AlertDialog.Builder messageBox = new AlertDialog.Builder(getActivity());
        messageBox.setTitle(method);
        messageBox.setMessage(message);
        messageBox.setCancelable(false);
        messageBox.setNeutralButton("OK", null);
        messageBox.show();
    }

}