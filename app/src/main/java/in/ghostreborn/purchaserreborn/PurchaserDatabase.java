package in.ghostreborn.purchaserreborn;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PurchaserDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "purchaser.db";
    private static final int DATABASE_VERSION = 1;

    public PurchaserDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + Constants.TABLE_NAME + "(" + Constants.TABLE_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + Constants.TABLE_USER_NAME + " TEXT UNIQUE," + Constants.TABLE_USER_PASS + " TEXT" + ")";
        db.execSQL(query);
        query = "INSERT INTO " + Constants.TABLE_NAME + " (" + Constants.TABLE_USER_NAME + "," + Constants.TABLE_USER_PASS + ") " + "VALUES (" + "\"admin\"," + "\"admin123\"" + ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);
    }
}
