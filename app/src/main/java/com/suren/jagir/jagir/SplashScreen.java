package com.suren.jagir.jagir;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

public class SplashScreen extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        textView = (TextView) findViewById(R.id.CopyRight);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textView.setText("©" + Calendar.getInstance().get(Calendar.YEAR) + " Neplopers.");
        if (isNetworkAvailable(this)) {
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    try {

                        URL url = new URL("http://www.google.com");
                        HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                        urlc.setRequestProperty("User-Agent", "Android");
                        urlc.setRequestProperty("Connection", "close");
                        urlc.setConnectTimeout(1500);
                        urlc.connect();
                        if (urlc.getResponseCode() == 200 || urlc.getResponseCode() > 400) {
                            Intent myIntent = new Intent(SplashScreen.this, MainActivity.class);
                            SplashScreen.this.startActivity(myIntent);
                        }
                    } catch (Exception e) {
                        AlertDialog alertDialog = new AlertDialog.Builder(SplashScreen.this).create();
                        alertDialog.setTitle("Error !!");
                        alertDialog.setMessage("Oops!! Something went wrong. Please try again.");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        SplashScreen.this.finish();
                                        System.exit(0);
                                    }
                                });
                        alertDialog.show();
                    }finally {
                        finish();
                    }
                }
            });

            thread.start();

        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(SplashScreen.this).create();
            alertDialog.setTitle("Error !!");
            alertDialog.setMessage("Oops!! It seems like you are not connected to internet. Please check your internet connection and try again.");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            SplashScreen.this.finish();
                            System.exit(0);
                        }
                    });
            alertDialog.show();
        }
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}
