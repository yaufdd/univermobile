package ru.mirea.bamba.data_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import ru.mirea.bamba.data_thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.textView.setText("Метод runOnUiThread позволяет запускать код в главном потоке, а метод postDelayed позволяет откладывать выполнение кода на определенный промежуток времени, не блокируя основной поток. Эти методы могут использоваться в любом порядке и комбинации. Например, можно вызывать метод runOnUiThread изнутри метода post, чтобы обновить пользовательский интерфейс на главном потоке, а также использовать метод postDelayed после вызова runOnUiThread, чтобы задержать обновление пользовательского интерфейса");

        final Runnable runn1 = new Runnable() {
            public void run() {
                binding.textViewInfo.setText("runn1");
            }
        };
        final Runnable runn2 = new Runnable() {
            public void run() {
                binding.textViewInfo.setText("runn2");
            }
        };
        final Runnable runn3 = new Runnable() {
            public void run() {
                binding.textViewInfo.setText("runn3");
            }
        };
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runn1);
                    TimeUnit.SECONDS.sleep(1);
                    binding.textViewInfo.postDelayed(runn3, 2000);
                    binding.textViewInfo.post(runn2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}