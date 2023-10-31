package in.ghostreborn.purchaserreborn.fragment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import in.ghostreborn.purchaserreborn.Constants;
import in.ghostreborn.purchaserreborn.R;
import in.ghostreborn.purchaserreborn.database.PurchaserDatabase;

public class UpdateProductFragment extends Fragment {

    private final String id;

    public UpdateProductFragment(String id) {
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_update_product, container, false);
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
            while (cursor.moveToNext()) {
                Log.e("TAG", "ID: " + cursor.getString(0));
                Log.e("TAG", "NAME: " + cursor.getString(1));
                Log.e("TAG", "PRICE: " + cursor.getString(2));
                Log.e("TAG", "PIC_ID: " + cursor.getString(3));
            }
            cursor.close();
        }
    }

}