package in.ghostreborn.purchaserreborn.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import in.ghostreborn.purchaserreborn.R;

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
        return view;
    }
}