package in.ghostreborn.purchaserreborn.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import in.ghostreborn.purchaserreborn.Constants;

public class ProductDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "purchaser.db";
    private static final int DATABASE_VERSION = 1;

    public ProductDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + Constants.TABLE_NAME_PRODUCT +
                "(" +
                Constants.TABLE_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Constants.TABLE_PRODUCT_NAME + " TEXT UNIQUE," +
                Constants.TABLE_PRODUCT_PRICE + " TEXT" +
                ")";
        db.execSQL(query);
        query = "INSERT INTO " + Constants.TABLE_NAME_USER +
                " VALUES (" +
                "1," + "\"Carrot\"," + "15" +
                ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_PRODUCT);
        onCreate(db);
    }
}
