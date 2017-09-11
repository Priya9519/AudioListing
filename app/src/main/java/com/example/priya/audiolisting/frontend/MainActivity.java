package com.example.priya.audiolisting.frontend;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.priya.audiolisting.R;
import com.example.priya.audiolisting.listeners.ApiListeners;
import com.example.priya.audiolisting.pojo.Data;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ApiListeners {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isNetworkAvailable()) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            AudioListFragment fragment = new AudioListFragment();
            fragmentTransaction.add(R.id.frame_layout, fragment);
            fragmentTransaction.commit();

        } else
            messageBox();
    }

    @Override
    public void onSuccess(String success, ArrayList<Data> data) {
        Log.e("Message",success);
    }

    @Override
    public void onError(String errorMessage) {
        Log.e("Message",errorMessage);

    }
    //Show Alert box to user if Internet connection is not available
    private void messageBox() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Please Check Your Internet Connection");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
    //check network availability
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo!= null && activeNetworkInfo.isConnected();
    }
}
