package in.ghostreborn.purchaserreborn.fragment;

import static android.app.Activity.RESULT_OK;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sangcomz.fishbun.FishBun;
import com.sangcomz.fishbun.adapter.image.impl.GlideAdapter;

import java.io.IOException;
import java.util.ArrayList;

import in.ghostreborn.purchaserreborn.Constants;
import in.ghostreborn.purchaserreborn.R;
import in.ghostreborn.purchaserreborn.database.PurchaserDatabase;

public class UpdateProductFragment extends Fragment {

    private final String id;
    EditText productNameEdit;
    EditText productPriceEdit;
    ImageView productImageView;
    Button updateButton;
    String mUri = "";
    private static final int REQUEST_CODE = 27;

    public UpdateProductFragment(String id) {
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_update_product, container, false);
        productNameEdit = view.findViewById(R.id.product_update_name_edit);
        productPriceEdit = view.findViewById(R.id.product_update_price_edit);
        productImageView = view.findViewById(R.id.product_update_image);
        productImageView.setOnClickListener(v -> FishBun.with(UpdateProductFragment.this)
                .setImageAdapter(new GlideAdapter())
                .startAlbumWithOnActivityResult(REQUEST_CODE));
        updateButton = view.findViewById(R.id.update_product_button);
        getDetailsWithID(getContext(), id);
        return view;
    }

    private void getDetailsWithID(Context context, String id) {
        try (PurchaserDatabase database = new PurchaserDatabase(context)) {
            SQLiteDatabase db = database.getReadableDatabase();
            String query = "SELECT * FROM " +
                    Constants.TABLE_NAME_PRODUCT +
                    " WHERE " + Constants.TABLE_PRODUCT_ID +
                    "=" + id;
            Cursor cursor = db.rawQuery(query, null);
            cursor.moveToNext();
            Log.e("TAG", "ID: " + cursor.getString(0));
            Log.e("TAG", "NAME: " + cursor.getString(1));
            Log.e("TAG", "PRICE: " + cursor.getString(2));
            Log.e("TAG", "PIC_ID: " + cursor.getString(3));
            productNameEdit.setText(cursor.getString(1));
            productPriceEdit.setText(cursor.getString(2));
            Uri uri = Uri.parse(cursor.getString(3));
            mUri = uri.toString();
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
            productImageView.setImageBitmap(bitmap);
            cursor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        updateButton.setOnClickListener(v -> {
            try (PurchaserDatabase database = new PurchaserDatabase(context)) {
                SQLiteDatabase db = database.getReadableDatabase();
                ContentValues values = new ContentValues();
                values.put(Constants.TABLE_PRODUCT_NAME, productNameEdit.getText().toString());
                values.put(Constants.TABLE_PRODUCT_PRICE, productPriceEdit.getText().toString());
                values.put(Constants.TABLE_PRODUCT_PIC_ID, mUri);

                String selection = Constants.TABLE_PRODUCT_ID + " = ?";
                String[] selectionArgs = { String.valueOf(id) };

                int count = db.update(
                        Constants.TABLE_NAME_PRODUCT,
                        values,
                        selection,
                        selectionArgs
                );

                Log.e("TAG", "Rows: " + count);

                db.close();

            }finally {
                Toast.makeText(getContext(), "Saved!", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });

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
                    mUri = uris.get(0).toString();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uris.get(0));
                    productImageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}