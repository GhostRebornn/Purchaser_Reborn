package in.ghostreborn.purchaserreborn.fragment;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import in.ghostreborn.purchaserreborn.Constants;
import in.ghostreborn.purchaserreborn.R;
import in.ghostreborn.purchaserreborn.database.PurchaserDatabase;

public class UpdateProductFragment extends Fragment {

    EditText productNameEdit;
    EditText productPriceEdit;
    Button productSaveButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_product, container, false);
        productNameEdit = view.findViewById(R.id.product_name_edit);
        productPriceEdit = view.findViewById(R.id.product_price_edit);
        productSaveButton = view.findViewById(R.id.save_product_button);

        productSaveButton.setOnClickListener(v -> saveProduct(
                productNameEdit.getText().toString(),
                productPriceEdit.getText().toString()
        ));

        return view;
    }

    private void saveProduct(
            String product,
            String price
    ){
        SQLiteDatabase db = null;
        try (PurchaserDatabase productDatabase = new PurchaserDatabase(getContext())) {
            try {
                db = productDatabase.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Constants.TABLE_PRODUCT_NAME, product);
                values.put(Constants.TABLE_PRODUCT_PRICE, price);
                assert db != null;
                long rowID = db.insert(Constants.TABLE_NAME_PRODUCT, null, values);
                Log.e("TAG", "Inserted at row: " + rowID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}