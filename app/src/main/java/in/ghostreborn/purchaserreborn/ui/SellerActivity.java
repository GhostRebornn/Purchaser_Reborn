package in.ghostreborn.purchaserreborn.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import in.ghostreborn.purchaserreborn.Constants;
import in.ghostreborn.purchaserreborn.R;
import in.ghostreborn.purchaserreborn.adapter.ProductAdapter;
import in.ghostreborn.purchaserreborn.utils.PurchaserHelper;

public class SellerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);

        TextView userNameText = findViewById(R.id.seller_name_text);
        String hiUser = "Hello " + Constants.user;
        userNameText.setText(hiUser);

    }

    @Override
    protected void onResume() {
        super.onResume();

        Constants.showAddToCart = false;
        setupProducts();

    }

    private void setupProducts(){
        PurchaserHelper.setupProducts(this);
        RecyclerView recyclerView = findViewById(R.id.seller_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        ProductAdapter adapter = new ProductAdapter(this, Constants.products,PurchaserHelper.checkEditable());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

}