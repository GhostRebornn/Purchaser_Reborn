package in.ghostreborn.purchaserreborn.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

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
        ImageView productImage = findViewById(R.id.buy_product_image);

        Products product = Constants.products.get(Constants.productIndex);
        productNameText.setText(product.getName());
        productPriceText.setText(product.getPrice());
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                    getContentResolver(),
                    product.getPic_id()
                    );
            productImage.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}