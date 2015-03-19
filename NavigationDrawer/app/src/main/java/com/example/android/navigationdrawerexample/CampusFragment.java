package com.example.android.navigationdrawerexample;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

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

                Button button_changeCampusImage = (Button) rootView.findViewById(R.id.btn_changeImage);
        button_changeCampusImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    changeCampusImage(rootView);//
                } catch (NullPointerException ex) {
                    messageBox("changeCampusImage, set to rootview?",ex.toString());
                }
            }
        });

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

    public void changeCampusImage(View rootView) {
        ImageView changeImage = (ImageView) rootView.findViewById(R.id.image_campus_change);
        int randomNumber = randInt(0,4);
        String changeImageString = getResources().getStringArray(R.array.image_changes_campus)[randomNumber];
        changeImage.setImageResource(getResources().getIdentifier(changeImageString, "drawable", MainActivity.PACKAGE_NAME));
    }

    public static int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
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