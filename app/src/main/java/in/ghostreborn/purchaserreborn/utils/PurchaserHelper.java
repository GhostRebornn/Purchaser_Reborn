package in.ghostreborn.purchaserreborn.utils;

import android.content.Context;
import android.content.SharedPreferences;

import in.ghostreborn.purchaserreborn.Constants;

public class PurchaserHelper {

    public static boolean checkLogin(Context context){
        SharedPreferences preferences = context
                .getSharedPreferences(Constants.PURCHASER_PREFERENCES, Context.MODE_PRIVATE);
        return preferences.contains(Constants.PREFERENCES_LOGIN);
    }

}
