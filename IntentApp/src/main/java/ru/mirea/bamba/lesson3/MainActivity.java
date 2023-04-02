package ru.mirea.bamba.lesson3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private String tag = MainActivity.class.getSimpleName().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void GoSecondActivity(View view) {
        long dateInMillis = System.currentTimeMillis();
        String format = "yyyy-MM-dd HH:mm:ss";
        final SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateString = sdf.format(new Date(dateInMillis));
        Intent intent = new Intent(this, second_activity.class);
        intent.putExtra("key", "КВАДРАТ ЗНАЧЕНИЯ МОЕГО НОМЕРА ПО СПИСКУ В ГРУППЕ СОСТАВЛЯЕТ 961, а текущее время"+" "+dateString);
        startActivity(intent);
    }


}