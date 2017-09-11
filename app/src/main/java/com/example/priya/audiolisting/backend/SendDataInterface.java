package com.example.priya.audiolisting.backend;

import com.example.priya.audiolisting.pojo.DataModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by priya on 25/8/17.
 * For Sending request to http url with fields
 */

public interface SendDataInterface {
    @FormUrlEncoded
    @POST("app_getAudioDetails")
     Call<DataModel>sendRequest(
            @Field("page") int page);
}
