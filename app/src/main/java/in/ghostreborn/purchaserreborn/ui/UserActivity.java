package in.ghostreborn.purchaserreborn.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import in.ghostreborn.purchaserreborn.Constants;
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

        TextView userNameText = findViewById(R.id.user_name_text);
        String hiUser = "Hello " + Constants.user;
        userNameText.setText(hiUser);

    }
}