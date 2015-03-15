package com.example.android.navigationdrawerexample;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utilities {

    public static boolean isOnline(Context mContext) {
        ConnectivityManager conMgr = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfo = conMgr.getAllNetworkInfo();
        boolean state = false;
        for (NetworkInfo nInfo : networkInfo) {
            if (nInfo.getType() == ConnectivityManager.TYPE_WIFI
                    || nInfo.getType() == ConnectivityManager.TYPE_ETHERNET
                    || nInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                if (nInfo.getState() == NetworkInfo.State.CONNECTED
                        || nInfo.getState() == NetworkInfo.State.CONNECTING) {
                    state = true;
                    break;
                }
            }
        }
        return state;
    }

    public void testMethodExternal() {
        System.out.println("testMethodExternal in constructor");
    }

    /**
     * Creating exception handling box
     * @param method
     * @param message
     */
    public void messageBox(String method, String message, Context context) {
        AlertDialog.Builder messageBox = new AlertDialog.Builder(context);
        messageBox.setTitle(method);
        messageBox.setMessage(message);
        messageBox.setCancelable(false);
        messageBox.setNeutralButton("OK", null);
        messageBox.show();
    }

}
