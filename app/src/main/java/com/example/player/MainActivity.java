package com.example.player;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    ImageView playPauseIcon;
    SeekBar seekBar;
    int [] songs = new int[]{R.raw.music, R.raw.music2, R.raw.music3};
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playPauseIcon = findViewById(R.id.playIconImageView);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), songs[count]);

        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        }, 0, 1000);
    }

    public void previous(View view) {
        if(count == 0) {
            count = 2;
        }
        else
        {
            count -=1;
        }
        mediaPlayer.stop();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), songs[count]);
        mediaPlayer.start();
        playPauseIcon.setImageResource(R.drawable.ic_pause_orange_24dp);
    }

    public void next(View view) {
        if(count<songs.length-1) {
            count += 1;
        }
        else
        {
            count = 0;
        }
        mediaPlayer.stop();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), songs[count]);
        mediaPlayer.start();
        playPauseIcon.setImageResource(R.drawable.ic_pause_orange_24dp);
    }

    public void play(View view) {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            playPauseIcon.setImageResource(R.drawable.ic_play_arrow_orange_24dp);
        } else {
            mediaPlayer.start();
            playPauseIcon.setImageResource(R.drawable.ic_pause_orange_24dp);
        }
    }

}