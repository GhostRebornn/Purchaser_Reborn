package in.ghostreborn.purchaserreborn.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.ghostreborn.purchaserreborn.Constants;
import in.ghostreborn.purchaserreborn.R;
import in.ghostreborn.purchaserreborn.adapter.ProductAdapter;
import in.ghostreborn.purchaserreborn.adapter.UsersAdapter;
import in.ghostreborn.purchaserreborn.database.PurchaserDatabase;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        RecyclerView productsRecycler = findViewById(R.id.products_recycler);
        RecyclerView usersRecycler = findViewById(R.id.users_recycler);
        setupProducts();
        setupUsers();
        ProductAdapter productAdapter = new ProductAdapter();
        UsersAdapter usersAdapter = new UsersAdapter();
        LinearLayoutManager productsManager = new LinearLayoutManager(this);
        LinearLayoutManager usersManager = new LinearLayoutManager(this);

        productsRecycler.setLayoutManager(productsManager);
        usersRecycler.setLayoutManager(usersManager);
        productsRecycler.setAdapter(productAdapter);
        usersRecycler.setAdapter(usersAdapter);

    }

    private void setupProducts(){
        try{
            Constants.products = new ArrayList<>();
            PurchaserDatabase database = new PurchaserDatabase(this);
            SQLiteDatabase db = database.getReadableDatabase();
            String query = "SELECT * FROM " + Constants.TABLE_NAME_PRODUCT;
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()){
                Constants.products.add(
                        cursor.getString(1)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupUsers(){
        try{
            Constants.users = new ArrayList<>();
            PurchaserDatabase database = new PurchaserDatabase(this);
            SQLiteDatabase db = database.getReadableDatabase();
            String query = "SELECT * FROM " + Constants.TABLE_NAME_USER;
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()){
                Constants.users.add(
                        cursor.getString(1)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}