package com.example.priya.audiolisting.requests;

import android.util.Log;

import com.example.priya.audiolisting.backend.ApiClient;
import com.example.priya.audiolisting.listeners.ApiListeners;
import com.example.priya.audiolisting.pojo.Data;
import com.example.priya.audiolisting.pojo.DataModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.priya.audiolisting.utility.AppContants.page;
/**
 * Created by priya on 4/9/17.
 * Sending Request to Api and getting response
 */
public class RequestApi {
    private ArrayList<Data> data;
    public static RequestApi getInstance(){

        return new RequestApi();
    }
   public  void requestApi(final ApiListeners listeners) {
        ApiClient.getApiService().sendRequest(page).enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                data=response.body().getData().getAudios();
                Log.e("data",data.get(0).getDescription());
                Log.e("data",data.get(0).getAudio_path());
                Log.e("data",data.get(0).getTime());
                listeners.onSuccess(response.body().getStatus(),data);
            }
            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Log.e("Message", "Request Fails");
                listeners.onError("Failed to Processes Request");

            }
        });
    }
}
