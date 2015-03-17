package com.example.android.navigationdrawerexample;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by mariuspilgrim on 12/03/15.
 */
public class CampusFragment extends Fragment {

    public static final String ARG_CAMPUS_NUMBER = "CAMPUS_number";

    /*
        public CampusFragment() {
        }
    */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_campus, container, false);
        int i = getArguments().getInt(ARG_CAMPUS_NUMBER);
        String menuItem = getResources().getStringArray(R.array.menu_items_array)[i];
        getActivity().setTitle(menuItem);

        //Starting GalleryActivity.class
        //Intent intent = new Intent(getActivity(), GalleryActivity.class);
        //startActivity(intent);

        /*
        Working code!!!
        Button button_goToGoogleMaps = (Button) rootView.findViewById(R.id.btn_goToGoogleMaps);
        button_goToGoogleMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    goToGoogleMaps(rootView);//Finally not null
                } catch (NullPointerException ex) {
                    messageBox("goToGoogleMaps, set to rootview?",ex.toString());
                }
            }
        });
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

    /*
    Working code!!!
    public void goToGoogleMaps(View rootView) {
        Intent intent = new Intent(getActivity(), MapsFragmentActivity.class);
        startActivity(intent);
    }

    <Button
    android:id="@+id/btn_goToGoogleMaps"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@string/campus_show_map"
    android:onClick="goToGoogleMaps"
    android:textSize="16sp" />
    */

}