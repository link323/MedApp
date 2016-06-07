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
 * Created by PC on 07.06.2016.
 */
public class InputAnalyzer {
    int data;
    boolean bool;
    int diastolic;
    int systolic;

    public InputAnalyzer(int data, boolean bool){
        this.data = data;
        this.bool = bool;
    }
    public InputAnalyzer(int diastolic, int systolic){
        this.diastolic = diastolic;
        this.systolic = systolic;
    }

    public boolean checkDiabeticInput(Context ctx) {
        if (bool && (data < 250 && data > 60)) {
            return true;
        } else if (!bool && (data < 300 && data > 60)) {
            return true;
        } else {
            Toast.makeText(ctx, "Twój wynik znacznie odbiega od norm. Upewnij się, że jest prawidłowy.", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public boolean checkPressureInput(Context ctx) {
        if ( (90 < diastolic && diastolic < 250 && systolic > 50 && systolic < 150)) {
            return true;
        } else {
            Toast.makeText(ctx, "Twój wynik znacznie odbiega od norm. Upewnij się, że jest prawidłowy.", Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
