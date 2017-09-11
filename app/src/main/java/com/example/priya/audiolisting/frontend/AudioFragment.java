package com.example.priya.audiolisting.frontend;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.priya.audiolisting.R;

import java.io.IOException;

import static android.content.ContentValues.TAG;

/**
 * Created by priya on 8/9/17.
 * playing the
 */

public class AudioFragment extends Fragment implements View.OnClickListener,MediaPlayer.OnPreparedListener,SeekBar.OnSeekBarChangeListener{
    private String time,title,ref,desc,path;
    private TextView tvTime,tvTitle,tvDesc,tvRef;
   private ImageView ivPlayPause,ivstop;
    private MediaPlayer mPlayer;
    private Handler seekHandler = new Handler();
    private Runnable run;
    SeekBar seekBar;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.audio_fragment, container, false);
        initView(view);
         mPlayer= new MediaPlayer();
        fetchBundleData();
        tvTitle.setText(title);
        tvDesc.setText(Html.fromHtml(desc));
        tvRef.setText(ref);
        seekBar.setProgress(0);// To set initial progress, i.e zero in starting of the song
        playMusic();
//
//        run = new Runnable() {
//            @Override
//            public void run() {
//                int min, sec;
//                seekUpdation();
//                if (mPlayer != null) {
//                    int mCurrentPosition = seekBar.getProgress();
//
//                    min = mCurrentPosition / 60;
//                    sec = mCurrentPosition % 60;
//
//                    Log.e("Music Player Activity", "Minutes : "+min +" Seconds : " + sec);
//                    tvTime.setText(min/1000+":"+sec+""+ "/" +time);
//                        /*currentime_mm.setText("" + min);
//                        currentime_ss.setText("" + sec);*/
//                }
//            }
//        };

        return view;
    }
    private void initView(View view) {
        tvTime=(TextView)view.findViewById(R.id.tv_time);
        tvTitle=(TextView)view.findViewById(R.id.tv_title);
        tvDesc=(TextView)view.findViewById(R.id.tv_desc);
        tvRef=(TextView)view.findViewById(R.id.tv_ref);
        seekBar=(SeekBar)view.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);
        ivPlayPause=(ImageView)view.findViewById(R.id.iv_play_pause);
        ivstop=(ImageView)view.findViewById(R.id.iv_stop);
        ivstop.setOnClickListener(this);
        ivPlayPause.setOnClickListener(this);
    }
    private void fetchBundleData() {
        Bundle bundle=getArguments();
        time= bundle.getString("time");
        title= bundle.getString("title");
        ref= bundle.getString("ref");
        desc= bundle.getString("desc");
        path=bundle.getString("path");
        Log.e("path",path);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_play_pause:
                if(ivPlayPause.getDrawable().getConstantState()== getResources().getDrawable( R.mipmap.play_button).getConstantState()){
                    mPlayer.pause();
                   ivPlayPause.setImageDrawable(ContextCompat.getDrawable(getContext(),R.mipmap.pause_button));
                }
              else{
                    ivPlayPause.setImageDrawable(ContextCompat.getDrawable(getContext(),R.mipmap.play_button));
                    mPlayer.start();
                }
                break;
             case R.id.iv_stop:
                mPlayer.stop();
                break;

        }
    }
    private void playMusic() {
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {

            mPlayer.setDataSource(path);
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mPlayer.setOnPreparedListener(this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
            try {
                mPlayer.prepareAsync();
            }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    //on Fragment pause
    @Override
    public void onPause() {
        super.onPause();
        mPlayer.pause();
        try {
            mPlayer.stop();
            mPlayer.release();
        }catch (Exception exp){
            exp.printStackTrace();
        }

    }
    //on fragment destroy
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPlayer.stop();
    }
    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
        seekBar.setMax(mPlayer.getDuration());
        tvTime.setText(mPlayer.getCurrentPosition()+""+ "/" +time);
        if(mPlayer.isPlaying())
            Log.e("message","Playing");
        ivPlayPause.setImageDrawable(ContextCompat.getDrawable(getContext(),R.mipmap.play_button));



//Make sure you update Seekbar on UI thread
        getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if(mPlayer != null){
                    int mCurrentPosition = mPlayer.getCurrentPosition() / 1000;
                    seekBar.setProgress(mCurrentPosition);

                    Log.e(TAG, "position is current "+mCurrentPosition);
                }
                seekHandler.postDelayed(this, 1000);
            }
        });



    }
    //for updating seekbar progress after every 1 sec
   private void seekUpdation(){
        seekBar.setProgress(mPlayer.getCurrentPosition());
        tvTime.setText(mPlayer.getCurrentPosition()+""+ "/" +time);
        Log.e("time",mPlayer.getCurrentPosition()+"");
//        seekHandler.postDelayed(run, 1000);
    }
    @Override
    public void onProgressChanged(final SeekBar seekBar, int progress, boolean fromUser) {

        mPlayer.seekTo(progress * 1000);
        Log.e(TAG, "progress is on progress changed "+progress*1000);
//        seekBar.setProgress(progress * 1000);
    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar){

    }
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {


    }

}

