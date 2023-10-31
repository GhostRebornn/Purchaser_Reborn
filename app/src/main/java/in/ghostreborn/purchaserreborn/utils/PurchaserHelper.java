package in.ghostreborn.purchaserreborn.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import java.util.ArrayList;

import in.ghostreborn.purchaserreborn.Constants;
import in.ghostreborn.purchaserreborn.database.PurchaserDatabase;
import in.ghostreborn.purchaserreborn.model.Products;

public class PurchaserHelper {

    public static void setupProducts(Context context) {
        try (PurchaserDatabase database = new PurchaserDatabase(context)) {
            Constants.products = new ArrayList<>();
            SQLiteDatabase db = database.getReadableDatabase();
            String query = "SELECT * FROM " + Constants.TABLE_NAME_PRODUCT;
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                Constants.products.add(
                        new Products(
                                cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getInt(2),
                                Uri.parse(cursor.getString(3)),
                                false
                        )
                );
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setupUsers(Context context) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean checkEditable(){
        if (Constants.isAdmin){
            return true;
        }else return Constants.isSeller;
    }

}
