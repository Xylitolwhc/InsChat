package Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import Application.InsChatApplication;

/**
 * Created by QiMeng on 2016/12/22.
 */

public class NetConnection {
    public static  boolean hasConnect() {
        ConnectivityManager cm = (ConnectivityManager) InsChatApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.isConnectedOrConnecting()) {
            return true;
        }else {
            return false;
        }
    }
}
