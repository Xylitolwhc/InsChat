package Util;

import android.telephony.TelephonyManager;

import Application.InsChatApplication;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * Created by QiMeng on 2016/12/22.
 */

public class IMEIUtil {
    public static String getImei() {
        String imei = ((TelephonyManager) InsChatApplication.getInstance().getSystemService(TELEPHONY_SERVICE))
                .getDeviceId();
        return imei;
    }
}
