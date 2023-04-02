package ru.mirea.bamba.sharer;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> imageActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityResultCallback<ActivityResult> callback = new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    Log.d(MainActivity.class.getSimpleName(), "Data:" + data.getDataString());
                }
            }
        };
        imageActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),callback);

    }
    public void GoClick1(View view) {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_TEXT, "Mirea");
        startActivity(Intent.createChooser(intent, "Выбор за вами!"));
    }

    public void GoClick2(View view){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("*/*");
        imageActivityResultLauncher.launch(intent);
    }


}