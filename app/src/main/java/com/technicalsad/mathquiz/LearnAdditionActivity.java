package com.technicalsad.mathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class LearnAdditionActivity extends AppCompatActivity {

    MediaPlayer musicplayer;

    String musicstate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_addition);

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        // Adding the music file to our
        // newly created object music
        musicplayer = MediaPlayer.create(
                this, R.raw.music);
        musicplayer.setLooping(true);
        musicplayer.setVolume(100,100);

        Intent intent=getIntent();
        if (intent.getExtras()!=null){
            musicstate=intent.getStringExtra("musicstate");
            if (musicstate.equals("music")){
                musicplayer.start();
            }

        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        musicplayer.pause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (musicstate.equals("music")){
            musicplayer.start();
        }
        else {
            musicplayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        musicplayer.stop();
    }
}