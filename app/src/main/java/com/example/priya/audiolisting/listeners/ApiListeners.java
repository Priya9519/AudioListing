package com.example.priya.audiolisting.listeners;

import com.example.priya.audiolisting.pojo.Data;

import java.util.ArrayList;

/**
 * Created by priya on 4/9/17.
 * Listener to send data from RequestApi interface to MainActivity when response is receive from api
 */

public interface ApiListeners {
     void onSuccess(String success, ArrayList<Data> data);
    void onError(String errorMessage);

}
