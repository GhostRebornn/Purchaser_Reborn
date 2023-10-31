package in.ghostreborn.purchaserreborn.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import in.ghostreborn.purchaserreborn.Constants;
import in.ghostreborn.purchaserreborn.R;
import in.ghostreborn.purchaserreborn.adapter.ProductAdapter;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RecyclerView cartRecycler = findViewById(R.id.cart_recycler);
        ProductAdapter adapter = new ProductAdapter(CartActivity.this, Constants.cart,false);
        LinearLayoutManager manager = new LinearLayoutManager(CartActivity.this);
        cartRecycler.setLayoutManager(manager);
        cartRecycler.setAdapter(adapter);
    }
}