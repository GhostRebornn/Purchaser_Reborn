package in.ghostreborn.purchaserreborn.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import in.ghostreborn.purchaserreborn.Constants;
import in.ghostreborn.purchaserreborn.R;
import in.ghostreborn.purchaserreborn.database.PurchaserDatabase;
import in.ghostreborn.purchaserreborn.ui.AdminActivity;
import in.ghostreborn.purchaserreborn.ui.SellerActivity;
import in.ghostreborn.purchaserreborn.ui.UserActivity;

public class LoginFragment extends Fragment {

    EditText userNameTextView;
    EditText userPassTextView;
    Button loginButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        userNameTextView = view.findViewById(R.id.add_user_edit);
        userPassTextView = view.findViewById(R.id.add_pass_edit);
        loginButton = view.findViewById(R.id.save_button);
        loginButton.setOnClickListener(v -> checkUser(
                userNameTextView.getText().toString(),
                userPassTextView.getText().toString()
        ));
        return view;
    }

    private void checkUser(String userName, String pass){
        SQLiteDatabase db;
        try (PurchaserDatabase purchaserDatabase = new PurchaserDatabase(getContext())) {
            db = purchaserDatabase.getReadableDatabase();
            String query = "SELECT * FROM " + Constants.TABLE_NAME_USER;
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                String user = cursor.getString(1);
                String password = cursor.getString(2);
                boolean isSeller = cursor.getInt(3) == 1;
                boolean isAdmin = cursor.getInt(4) == 1;

                if (userName.equals(user) && password.equals(pass)) {

                    Constants.isAdmin = false;
                    Constants.isSeller = false;
                    Constants.isUser = false;

                    if (isAdmin) {
                        Constants.isAdmin = true;
                        startActivity(new Intent(getContext(), AdminActivity.class));
                    }else if (isSeller){
                        Constants.isSeller = true;
                        startActivity(new Intent(getContext(), SellerActivity.class));
                    }else {
                        Constants.isUser = true;
                        startActivity(new Intent(getContext(), UserActivity.class));
                    }
                }
            }
            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}