package com.example.priya.audiolisting.frontend;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.priya.audiolisting.R;
import com.example.priya.audiolisting.adapters.CustomAdapter;
import com.example.priya.audiolisting.listeners.ApiListeners;
import com.example.priya.audiolisting.listeners.FragmentListener;
import com.example.priya.audiolisting.pojo.Data;
import com.example.priya.audiolisting.requests.RequestApi;

import java.util.ArrayList;

import static android.R.attr.data;

/**
 * Created by priya on 8/9/17.
 * show list of audio songs fetched from url in a RecyclerView
 */

public class AudioListFragment extends Fragment implements View.OnClickListener,ApiListeners,FragmentListener{
    private RecyclerView recyclerView;
    private LinearLayout layoutOne, layoutTwo, layoutThree, layoutFour, layoutFive;
    private ProgressDialog progressDialog;
    private CustomAdapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.audio_list_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        layoutOne = (LinearLayout) view.findViewById(R.id.layout_one);
        layoutTwo = (LinearLayout) view.findViewById(R.id.layout_two);
        layoutThree = (LinearLayout) view.findViewById(R.id.layout_three);
        layoutFour = (LinearLayout) view.findViewById(R.id.layout_four);
        layoutFive = (LinearLayout) view.findViewById(R.id.layout_five);

        layoutOne.setOnClickListener(this);
        layoutTwo.setOnClickListener(this);
        layoutThree.setOnClickListener(this);
        layoutFour.setOnClickListener(this);
        layoutFive.setOnClickListener(this);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        hitApi();
        return view;
    }
    private void setRecyclerView( ArrayList<Data> data) {
        mAdapter=new CustomAdapter(data,getContext(),this);
        LinearLayoutManager layoutManager = new   LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }
    //send request to http url
    private void hitApi() {
        progressDialog.show();
        RequestApi.getInstance().requestApi(this);
        progressDialog.dismiss();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_one:
                layoutOne.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorGray));
                layoutTwo.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkGray));
                layoutThree.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkGray));
                layoutFour.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkGray));
                layoutFive.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkGray));
                break;
            case R.id.layout_two:
                layoutTwo.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorGray));
                layoutOne.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkGray));
                layoutThree.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkGray));
                layoutFour.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkGray));
                layoutFive.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkGray));
                break;
            case R.id.layout_three:
                layoutThree.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorGray));
                layoutTwo.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkGray));
                layoutOne.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkGray));
                layoutFour.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkGray));
                layoutFive.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkGray));
                break;

            case R.id.layout_four:
                layoutFour.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorGray));
                layoutTwo.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkGray));
                layoutThree.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkGray));
                layoutOne.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkGray));
                layoutFive.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkGray));
                break;

            case R.id.layout_five:
                layoutFive.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorGray));
                layoutTwo.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkGray));
                layoutThree.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkGray));
                layoutFour.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkGray));
                layoutOne.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkGray));
                break;
        }
    }
    @Override
    public void onSuccess(String success, ArrayList<Data> data) {
        setRecyclerView(data);
        Log.e("TAG",data+"");
    }
    @Override
    public void onError(String errorMessage) {
        Log.e("TAG",data+"");
    }
    @Override
    public void sendData(String time, String title, String desc,String path, String ref) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().addToBackStack(null);
        AudioFragment fragment = new AudioFragment();
        Bundle bundle=new Bundle();
        bundle.putString("time", time);
        bundle.putString("title",title);
        bundle.putString("desc",desc);
        bundle.putString("path",path);
        bundle.putString("ref",ref);
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();

    }
}
