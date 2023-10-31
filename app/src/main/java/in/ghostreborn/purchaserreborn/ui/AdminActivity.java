package in.ghostreborn.purchaserreborn.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import in.ghostreborn.purchaserreborn.R;
import in.ghostreborn.purchaserreborn.adapter.ProductAdapter;
import in.ghostreborn.purchaserreborn.adapter.UsersAdapter;
import in.ghostreborn.purchaserreborn.utils.PurchaserHelper;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button addUserButton = findViewById(R.id.add_users_button);
        Button addProductButton = findViewById(R.id.add_products_button);
        addUserButton.setOnClickListener(v -> startActivity(new Intent(AdminActivity.this, AddUsersActivity.class)));
        addProductButton.setOnClickListener(v -> startActivity(new Intent(AdminActivity.this, AddProductsActivity.class)));

        RecyclerView productsRecycler = findViewById(R.id.products_recycler);
        RecyclerView usersRecycler = findViewById(R.id.users_recycler);
        PurchaserHelper.setupProducts(this);
        PurchaserHelper.setupUsers(this);
        ProductAdapter productAdapter = new ProductAdapter(this, PurchaserHelper.checkEditable());
        UsersAdapter usersAdapter = new UsersAdapter();
        LinearLayoutManager productsManager = new LinearLayoutManager(this);
        LinearLayoutManager usersManager = new LinearLayoutManager(this);

        productsRecycler.setLayoutManager(productsManager);
        usersRecycler.setLayoutManager(usersManager);
        productsRecycler.setAdapter(productAdapter);
        usersRecycler.setAdapter(usersAdapter);

    }

}