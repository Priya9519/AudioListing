package com.example.priya.audiolisting.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.priya.audiolisting.R;
import com.example.priya.audiolisting.listeners.FragmentListener;
import com.example.priya.audiolisting.pojo.Data;

import java.util.ArrayList;

/**
 * Created by priya on 6/9/17.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    protected ArrayList<Data> dataList;
    private Context context;
    FragmentListener listener;
    //parameterised constructor of adapter which take context and arraylist as parameter
    public CustomAdapter(ArrayList<Data> dataList, Context context, final FragmentListener listener) {
        this.dataList = dataList;
        this.context=context;
        this.listener=listener;

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item, null);
        return new MyViewHolder(itemView);
    }
    //this method is called for each item of recycelerview to set data to custom_item.xml
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //data=response.body().getData().getAudios();
        final Data data = dataList.get(position);
        holder.audioTime.setText(data.getTime());
        holder.audioTitle.setText(data.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.sendData(data.getTime(),data.getTitle(),data.getDescription(),data.getAudio_path(),data.getReferences());

            }
        });
    }
    //override method of recyclerview adapter which return size of datalist
    @Override
    public int getItemCount() {

        return (null != dataList ? dataList.size() : 0);
    }
  //initailise view of recyclerview item
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView audioTime,audioTitle;
        public MyViewHolder(View view) {
            super(view);
            audioTime=(TextView)view.findViewById(R.id.audio_time);
            audioTitle=(TextView)view.findViewById(R.id.audio_title);


        }
    }

}





