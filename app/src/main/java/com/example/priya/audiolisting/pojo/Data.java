package com.example.priya.audiolisting.pojo;

import java.util.ArrayList;

/**
 * Created by priya on 6/9/17.
 * class with setter and getter of all variable of arraylist of object receive from api
 */

public class Data {
    String time,title,audio_path,references,description;
    ArrayList<Data> audios=new ArrayList<>();

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAudio_path() {
        return audio_path;
    }

    public void setAudio_path(String audio_path) {
        this.audio_path = audio_path;
    }

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Data> getAudios() {
        return audios;
    }

    public void setAudios(ArrayList<Data> audios) {
        this.audios = audios;
    }
}
