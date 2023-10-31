package in.ghostreborn.purchaserreborn.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import in.ghostreborn.purchaserreborn.R;
import in.ghostreborn.purchaserreborn.adapter.ProductAdapter;
import in.ghostreborn.purchaserreborn.utils.PurchaserHelper;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        PurchaserHelper.setupProducts(this);
        RecyclerView recyclerView = findViewById(R.id.products_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        ProductAdapter adapter = new ProductAdapter(this, PurchaserHelper.checkEditable());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }
}