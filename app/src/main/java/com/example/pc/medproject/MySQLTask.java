package com.example.pc.medproject;

import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by PC on 02.06.2016.
 */
public class MySQLTask extends AsyncTask<String,String,String>{
    Context ctx;
    String pesel;
    String result;
    String time;
    String beforeFood;

    public MySQLTask(Context ctx, String pesel, String result, String time, String beforeFood){
        this.ctx = ctx;
        this.pesel = pesel;
        this.result = result;
        this.time = time;
        this.beforeFood = beforeFood;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

//        String url_create_product = "http://10.0.2.2:80/android_connect/create_product.php";
        String url_create_product = "http://192.168.0.11/android-connector/create_product.php";
        try{
            URL url = new URL(url_create_product);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String data = URLEncoder.encode("pesel", "UTF-8") + "=" +URLEncoder.encode(pesel, "UTF-8")+" & "+
                    URLEncoder.encode("result", "UTF-8") + "=" +URLEncoder.encode(String.valueOf(result), "UTF-8")+" & "+
                    URLEncoder.encode("time", "UTF-8") + "=" +URLEncoder.encode(time, "UTF-8")+" & "+
                    URLEncoder.encode("before_food", "UTF-8") + "=" +URLEncoder.encode(String.valueOf(beforeFood), "UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            inputStream.close();
            return "success";

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
    }
}
