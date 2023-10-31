package in.ghostreborn.purchaserreborn.fragment;

import static android.app.Activity.RESULT_OK;

import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sangcomz.fishbun.FishBun;
import com.sangcomz.fishbun.adapter.image.impl.GlideAdapter;

import java.io.IOException;
import java.util.ArrayList;

import in.ghostreborn.purchaserreborn.Constants;
import in.ghostreborn.purchaserreborn.R;
import in.ghostreborn.purchaserreborn.database.PurchaserDatabase;

public class AddProductFragment extends Fragment {

    EditText productNameEdit;
    EditText productPriceEdit;
    Button productSaveButton;
    ImageView productImageView;
    String uri = "";

    private static final int REQUEST_CODE = 27;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);
        productNameEdit = view.findViewById(R.id.product_name_edit);
        productPriceEdit = view.findViewById(R.id.product_price_edit);
        productSaveButton = view.findViewById(R.id.save_product_button);
        productImageView = view.findViewById(R.id.product_image);

        productImageView.setOnClickListener(v -> FishBun.with(AddProductFragment.this)
                .setImageAdapter(new GlideAdapter())
                .startAlbumWithOnActivityResult(REQUEST_CODE));

        productSaveButton.setOnClickListener(v -> {
            if (!uri.equals("")) {
                saveProduct(
                        productNameEdit.getText().toString(),
                        productPriceEdit.getText().toString(),
                        uri
                );
            }
        });

        return view;
    }

    private void saveProduct(
            String product,
            String price,
            String uri
    ) {
        SQLiteDatabase db;
        try (PurchaserDatabase productDatabase = new PurchaserDatabase(getContext())) {
            try {
                db = productDatabase.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Constants.TABLE_PRODUCT_NAME, product);
                values.put(Constants.TABLE_PRODUCT_PRICE, price);
                values.put(Constants.TABLE_PRODUCT_PIC_ID, uri);
                assert db != null;
                long rowID = db.insert(Constants.TABLE_NAME_PRODUCT, null, values);
                Log.e("TAG", "Inserted at row: " + rowID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                assert data != null;
                ArrayList<Uri> uris = data.getParcelableArrayListExtra(FishBun.INTENT_PATH);
                assert uris != null;
                Log.e("TAG", uris.get(0).toString());
                try {
                    uri = uris.get(0).toString();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uris.get(0));
                    productImageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}