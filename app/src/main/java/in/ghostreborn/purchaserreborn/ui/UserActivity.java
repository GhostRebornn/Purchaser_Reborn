package in.ghostreborn.purchaserreborn.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.ghostreborn.purchaserreborn.Constants;
import in.ghostreborn.purchaserreborn.R;
import in.ghostreborn.purchaserreborn.adapter.ProductAdapter;
import in.ghostreborn.purchaserreborn.utils.PurchaserHelper;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        TextView userNameText = findViewById(R.id.user_name_text);
        String hiUser = "Hello " + Constants.user;
        userNameText.setText(hiUser);

        ImageView cartImageView = findViewById(R.id.cart_image_view);
        cartImageView.setOnClickListener(v -> startActivity(new Intent(UserActivity.this, CartActivity.class)));

    }

    @Override
    protected void onResume() {
        super.onResume();

        Constants.cart = new ArrayList<>();
        Constants.showAddToCart = true;
        setupProducts();

    }

    private void setupProducts(){
        PurchaserHelper.setupProducts(this);
        RecyclerView recyclerView = findViewById(R.id.products_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        ProductAdapter adapter = new ProductAdapter(this, Constants.products,PurchaserHelper.checkEditable());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

}