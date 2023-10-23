package in.ghostreborn.purchaserreborn;

import android.content.Context;
import android.content.SharedPreferences;

public class PurchaserHelper {

    public static boolean checkLogin(Context context){
        SharedPreferences preferences = context
                .getSharedPreferences(Constants.PURCHASER_PREFERENCES, Context.MODE_PRIVATE);
        if (preferences.contains(Constants.PREFERENCES_LOGIN)){
            return true;
        }
        return false;
    }

}
