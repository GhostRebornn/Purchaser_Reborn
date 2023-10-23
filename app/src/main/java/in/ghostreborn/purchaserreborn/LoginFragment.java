package in.ghostreborn.purchaserreborn;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginFragment extends Fragment {

    EditText userNameTextView;
    EditText userPassTextView;
    Button loginButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        userNameTextView = view.findViewById(R.id.login_user_edit);
        userPassTextView = view.findViewById(R.id.login_pass_edit);
        loginButton = view.findViewById(R.id.login_button);
        loginButton.setOnClickListener(v -> {
            checkUser(
                    userNameTextView.getText().toString(),
                    userPassTextView.getText().toString()
            );
        });
        return view;
    }

    private void checkUser(String userName, String pass){
        PurchaserDatabase purchaserDatabase = new PurchaserDatabase(getContext());
        SQLiteDatabase db = purchaserDatabase.getReadableDatabase();
        String query = "SELECT * FROM " + Constants.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String user = cursor.getString(1);
            String password = cursor.getString(2);
            boolean isSeller = cursor.getInt(3) == 1;
            boolean isAdmin = cursor.getInt(4) == 1;

            if (userName.equals(user) && password.equals(pass)){
                if (isAdmin){
                    startActivity(new Intent(getContext(), AdminActivity.class));
                }
            }

        }
    }

}