package ru.mirea.bamba.mireaproject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import ru.mirea.bamba.mireaproject.databinding.FragmentHttpBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InternetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InternetFragment extends Fragment {
    private FragmentHttpBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InternetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HttpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InternetFragment newInstance(String param1, String param2) {
        InternetFragment fragment = new InternetFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHttpBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connectivityManager =
                        (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkinfo = null;
                if (connectivityManager != null) {
                    networkinfo = connectivityManager.getActiveNetworkInfo();
                }
                if (networkinfo != null && networkinfo.isConnected()) {

                    new DownloadPageTask().execute("https://ipinfo.io/json"); // запуск нового потока

                } else {
                    binding.textViewIP.setText("Загружаем...");
                    binding.textViewCountry.setText("Загружаем...");
                    binding.textViewCity.setText("Загружаем...");
                    binding.textViewD.setText("Загружаем...");
                    binding.textViewS.setText("Загружаем...");
                }
            }

        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connectivityManager =
                        (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkinfo = null;
                if (connectivityManager != null) {
                    networkinfo = connectivityManager.getActiveNetworkInfo();
                }
                if (networkinfo != null && networkinfo.isConnected()) {
                    String par1 = binding.textViewD.getText().toString();
                    String par2 = binding.textViewS.getText().toString();
                    String zap = "https://api.open-meteo.com/v1/forecast?latitude="+par1+"&longitude="+par2+"&current_weather=true";
                    new DownloadPageTask2().execute(zap);
                }
                else {
                    binding.textViewPogoda.setText("Нет интернета");


                }
            }
        });
        return view;

    }
    private class DownloadPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            binding.textViewIP.setText("Загружаем...");
            binding.textViewCountry.setText("Загружаем...");
            binding.textViewCity.setText("Загружаем...");
            binding.textViewD.setText("Загружаем...");
            binding.textViewS.setText("Загружаем...");
        }
        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadIpInfo(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }
        @Override
        protected void onPostExecute(String result) {
            Log.d(MainActivity.class.getSimpleName(), result);
            try {
                JSONObject responseJson = new JSONObject(result);
                Log.d(MainActivity.class.getSimpleName(), "Response: " + responseJson);
                String ip = responseJson.getString("ip");
                String city = responseJson.getString("city");
                String region = responseJson.getString("country");
                String par = responseJson.getString("loc");
                String[] words = par.split(",");
                binding.textViewIP.setText("My IP: " + ip);
                binding.textViewCity.setText(city);
                binding.textViewCountry.setText(region);
                binding.textViewD.setText(words[0]);
                binding.textViewS.setText(words[1]);
                Log.d(MainActivity.class.getSimpleName(), "IP: " + ip);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(result);
        }
    }
    private String downloadIpInfo(String address) throws IOException {
        InputStream inputStream = null;
        String data = "";
        try {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(100000);
            connection.setConnectTimeout(100000);
            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(true);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK
                inputStream = connection.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int read = 0;
                while ((read = inputStream.read()) != -1) {
                    bos.write(read); }
                bos.close();
                data = bos.toString();
            } else {
                data = connection.getResponseMessage()+". Error Code: " + responseCode;
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return data;
    }

    private class DownloadPageTask2 extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            binding.textViewPogoda.setText("Загружаем...");
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadIpInfo(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }

        protected void onPostExecute(String result) {
            try {
                JSONObject responseJson = new JSONObject(result);
                Log.d(MainActivity.class.getSimpleName(), "Response: " + responseJson);
                String weather= responseJson.getString("current_weather");
                JSONObject responseJson2 = new JSONObject(weather);
                String temp = responseJson2.getString("temperature");
                binding.textViewPogoda.setText("Температура: " + temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
            super.onPostExecute(result);
        }
    }

}