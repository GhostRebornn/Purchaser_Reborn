package in.ghostreborn.purchaserreborn.fragment;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import in.ghostreborn.purchaserreborn.Constants;
import in.ghostreborn.purchaserreborn.R;
import in.ghostreborn.purchaserreborn.database.PurchaserDatabase;

public class AddUserFragment extends Fragment {

    EditText addUserEdit;
    EditText addPassEdit;
    RadioGroup radioGroup;
    Button saveButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);
        addUserEdit = view.findViewById(R.id.add_user_edit);
        addPassEdit = view.findViewById(R.id.add_pass_edit);
        radioGroup = view.findViewById(R.id.radioGroup);
        saveButton = view.findViewById(R.id.login_button);

        saveButton.setOnClickListener(v -> saveUser(addUserEdit.getText().toString(), addPassEdit.getText().toString()));

        return view;
    }

    private boolean checkAdmin(){
        int checked = radioGroup.getCheckedRadioButtonId();
        return checked == R.id.is_admin_radio;
    }

    private boolean checkSeller(){
        int checked = radioGroup.getCheckedRadioButtonId();
        return checked == R.id.is_seller_radio;
    }

    private void saveUser(
            String userName,
            String pass
    ){
        boolean isSeller = checkSeller();
        boolean isAdmin = checkAdmin();
        SQLiteDatabase db;
        try (PurchaserDatabase purchaserDatabase = new PurchaserDatabase(getContext())) {
            db = purchaserDatabase.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Constants.TABLE_USER_NAME, userName);
            values.put(Constants.TABLE_USER_PASS, pass);
            values.put(Constants.TABLE_USER_SELLER, isSeller ? 1 : 0);
            values.put(Constants.TABLE_USER_ADMIN, isAdmin ? 1 : 0);
            db.insert(Constants.TABLE_NAME_USER, null, values);
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getContext(), "Failed to save", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(getContext(), "Saved!", Toast.LENGTH_SHORT).show();
        getActivity().finish();

    }

}