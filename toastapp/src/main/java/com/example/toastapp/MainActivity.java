package com.example.toastapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextTextPersonName);
        button = findViewById(R.id.button);
    }

    public void onClick(View view) {
        int len = editText.getText().length();
        Toast toast = Toast.makeText(getApplicationContext(),
                "«Бамба № 31 ГРУППА БСБО-17-20 Количество символов - " + len,
                Toast.LENGTH_SHORT);
        toast.show();
    }
}