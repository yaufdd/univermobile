package ru.mirea.bamba.lesson3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class second_activity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String data = (String) getIntent().getSerializableExtra("key");
        textView= findViewById(R.id.textView);
        textView.setText(data);

    }
}