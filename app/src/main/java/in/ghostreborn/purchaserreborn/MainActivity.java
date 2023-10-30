package in.ghostreborn.purchaserreborn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sangcomz.fishbun.FishBun;
import com.sangcomz.fishbun.adapter.image.impl.GlideAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 27;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button selectButton = findViewById(R.id.selectButton);
        selectButton.setOnClickListener(v -> FishBun.with(MainActivity.this)
                .setImageAdapter(new GlideAdapter())
                .startAlbumWithOnActivityResult(REQUEST_CODE));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                assert data != null;
                ArrayList<Uri> uris = data.getParcelableArrayListExtra(FishBun.INTENT_PATH);
                assert uris != null;
                Log.e("TAG", uris.get(0).toString());
            }
        }
    }
}