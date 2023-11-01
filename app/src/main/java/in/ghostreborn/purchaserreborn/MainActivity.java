package in.ghostreborn.purchaserreborn;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import in.ghostreborn.purchaserreborn.utils.PurchaserHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PurchaserHelper.setupProducts(MainActivity.this);

    }
}