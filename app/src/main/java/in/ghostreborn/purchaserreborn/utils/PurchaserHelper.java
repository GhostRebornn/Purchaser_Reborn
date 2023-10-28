package in.ghostreborn.purchaserreborn.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import in.ghostreborn.purchaserreborn.Constants;
import in.ghostreborn.purchaserreborn.database.PurchaserDatabase;

public class PurchaserHelper {

    public static void setupProducts(Context context) {
        try {
            try (PurchaserDatabase database = new PurchaserDatabase(context)) {
                Constants.products = new ArrayList<>();
                SQLiteDatabase db = database.getReadableDatabase();
                String query = "SELECT * FROM " + Constants.TABLE_NAME_PRODUCT;
                Cursor cursor = db.rawQuery(query, null);
                while (cursor.moveToNext()) {
                    Constants.products.add(
                            cursor.getString(1)
                    );
                }
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setupUsers(Context context) {
        try {
            try (PurchaserDatabase database = new PurchaserDatabase(context)) {
                Constants.users = new ArrayList<>();
                SQLiteDatabase db = database.getReadableDatabase();
                String query = "SELECT * FROM " + Constants.TABLE_NAME_USER;
                Cursor cursor = db.rawQuery(query, null);
                while (cursor.moveToNext()) {
                    Constants.users.add(
                            cursor.getString(1)
                    );
                }
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
