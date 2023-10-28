package in.ghostreborn.purchaserreborn.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import in.ghostreborn.purchaserreborn.Constants;
import in.ghostreborn.purchaserreborn.R;
import in.ghostreborn.purchaserreborn.model.Products;

public class BuyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        TextView productNameText = findViewById(R.id.buy_name_text);
        TextView productPriceText = findViewById(R.id.buy_price_text);
        Products product = Constants.products.get(Constants.productIndex);
        productNameText.setText(product.getName());
        productPriceText.setText(product.getPrice());

    }
}