package in.ghostreborn.purchaserreborn.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

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

        Constants.showAddToCart = false;

        RecyclerView cartRecycler = findViewById(R.id.cart_recycler);
        ProductAdapter adapter = new ProductAdapter(CartActivity.this, Constants.cart,false);
        LinearLayoutManager manager = new LinearLayoutManager(CartActivity.this);
        cartRecycler.setLayoutManager(manager);
        cartRecycler.setAdapter(adapter);

        TextView totalText = findViewById(R.id.cart_total_text);
        Constants.price = getTotal();
        String total = "Total: " + getTotal() + "₹";
        totalText.setText(total);

        Button checkOutButton = findViewById(R.id.check_out_button);
        checkOutButton.setOnClickListener(v -> startActivity(new Intent(CartActivity.this, PayActivity.class)));

    }

    private int getTotal(){
        int total = 0;
        for (int i=0; i<Constants.cart.size(); i++){
            total += Constants.cart.get(i).getPrice();
        }
        return total;
    }

}