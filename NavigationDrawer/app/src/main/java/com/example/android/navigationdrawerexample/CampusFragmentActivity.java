package com.example.android.navigationdrawerexample;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mariuspilgrim on 12/03/15.
 */
public class CampusFragmentActivity extends Fragment {

    public static final String ARG_CAMPUS_NUMBER = "CAMPUS_number";

    public CampusFragmentActivity() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_campus, container, false);
        int i = getArguments().getInt(ARG_CAMPUS_NUMBER);
        String menuItem = getResources().getStringArray(R.array.menu_items_array)[i];
        getActivity().setTitle(menuItem);

        /*
        TextView textView_welcome = (TextView) rootView.findViewById(R.id.department_prof_list);
        textView_welcome.setTextColor(Color.parseColor("#852339"));
        textView_welcome.setText(Html.fromHtml("bla"));
        */

        return rootView;
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