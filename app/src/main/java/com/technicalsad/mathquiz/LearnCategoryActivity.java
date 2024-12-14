package com.technicalsad.mathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class LearnCategoryActivity extends AppCompatActivity {

    TextView countingtoword,addition,subtract,multiply,division;

    MediaPlayer musicplayer;

    String musicstate;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_category);

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        countingtoword=findViewById(R.id.countingtoword);
        addition=findViewById(R.id.addition);
        subtract=findViewById(R.id.subtract);
        multiply=findViewById(R.id.multiply);
        division=findViewById(R.id.division);

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


        countingtoword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LearnCategoryActivity.this,LearnCountingActivity.class);
                intent.putExtra("musicstate",musicstate);
                startActivity(intent);

            }
        });

        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LearnCategoryActivity.this, LearnAdditionActivity.class);
                intent.putExtra("musicstate",musicstate);
                startActivity(intent);
            }
        });

        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LearnCategoryActivity.this, LearnSubtractActivity.class);
                intent.putExtra("musicstate",musicstate);
                startActivity(intent);
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LearnCategoryActivity.this, LearnMultiplicationActivity.class);
                intent.putExtra("musicstate",musicstate);
                startActivity(intent);
            }
        });

        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LearnCategoryActivity.this, LearnDivisionActivity.class);
                intent.putExtra("musicstate",musicstate);
                startActivity(intent);
            }
        });


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