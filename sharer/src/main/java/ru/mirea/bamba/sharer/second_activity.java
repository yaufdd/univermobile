package ru.mirea.bamba.sharer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class second_activity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String text = (String) getIntent().getSerializableExtra("mirea");
        textView = findViewById(R.id.textView);
        textView.setText(text);
    }
}