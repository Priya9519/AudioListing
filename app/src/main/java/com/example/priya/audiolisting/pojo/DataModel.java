package com.example.priya.audiolisting.pojo;

/**
 * Created by priya on 5/9/17.
 * Setter and getter method of object variables receive from api
 */

public class DataModel {
  String status;
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    Data data=new Data();
}





