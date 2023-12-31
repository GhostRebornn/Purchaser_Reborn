package in.ghostreborn.purchaserreborn.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import in.ghostreborn.purchaserreborn.Constants;

public class PurchaserDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "purchaser.db";
    private static final int DATABASE_VERSION = 1;

    public PurchaserDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        setupUserTable(db);
        setupProductTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_USER);
        onCreate(db);
    }

    private void setupUserTable(SQLiteDatabase db){
        String query = "CREATE TABLE " + Constants.TABLE_NAME_USER +
                "(" +
                Constants.TABLE_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Constants.TABLE_USER_NAME + " TEXT UNIQUE," +
                Constants.TABLE_USER_PASS + " TEXT," +
                Constants.TABLE_USER_SELLER + " INTEGER NOT NULL DEFAULT 0," +
                Constants.TABLE_USER_ADMIN + " INTEGER NOT NULL DEFAULT 0" +
                ")";
        db.execSQL(query);
        query = "INSERT INTO " + Constants.TABLE_NAME_USER +
                " (" +
                Constants.TABLE_USER_NAME + "," +
                Constants.TABLE_USER_PASS + "," +
                Constants.TABLE_USER_SELLER + "," +
                Constants.TABLE_USER_ADMIN +
                ") " +
                "VALUES (" +
                "\"admin\"," + "\"admin123\"," + "0," +"1" +
                ")";
        db.execSQL(query);
    }

    private void setupProductTable(SQLiteDatabase db) {
        String query = "CREATE TABLE " + Constants.TABLE_NAME_PRODUCT +
                "(" +
                Constants.TABLE_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Constants.TABLE_PRODUCT_NAME + " TEXT UNIQUE," +
                Constants.TABLE_PRODUCT_PRICE + " TEXT," +
                Constants.TABLE_PRODUCT_PIC_ID + " TEXT" +
                ")";
        db.execSQL(query);
    }

}
