package in.ghostreborn.purchaserreborn.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import in.ghostreborn.purchaserreborn.Constants;
import in.ghostreborn.purchaserreborn.R;
import in.ghostreborn.purchaserreborn.fragment.UpdateProductFragment;

public class ProductEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_edit);

        UpdateProductFragment fragment = new UpdateProductFragment(String.valueOf(Constants.productIndex + 1));

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.product_edit_container, fragment)
                .commit();

    }
}