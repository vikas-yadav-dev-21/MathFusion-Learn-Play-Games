package com.technicalsad.mathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class LearnCountingActivity extends AppCompatActivity {

    GridView countgridview;

    MediaPlayer musicplayer;

    String musicstate;

    int[] numlist={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,
            31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,
    66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_counting);

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


        countgridview=findViewById(R.id.countgridview);

        Adapter adapter=new Apdapter(numlist,this);

        countgridview.setAdapter((ListAdapter) adapter);

    }

    public class Apdapter extends BaseAdapter {

        private int[] num;
        private Context context;
        private LayoutInflater layoutInflater;

        public Apdapter(int[] num, Context context) {
            this.num = num;
            this.context = context;
            this.layoutInflater= (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return num.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            if (view==null){
                view=layoutInflater.inflate(R.layout.counting_list_raw,viewGroup,false);
            }

            TextView numbertxt,wordtxt;
            numbertxt =view.findViewById(R.id.num);
            wordtxt=view.findViewById(R.id.word);

            numbertxt.setText(String.valueOf(num[i]));
            wordtxt.setText(convertNumberToWord(num[i]));

            return view;
        }
    }

    private String convertNumberToWord(int number) {
        if (number == 0) {
            return "zero";
        }

        String[] units = {
                "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
        };

        String[] tens = {
                "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
        };

        if (number < 20) {
            return units[number];
        } else if (number < 100) {
            return tens[number / 10] + ((number % 10 != 0) ? " " + units[number % 10] : "");
        } else if (number == 100) {
            return "one hundred";
        }

        return "one hundred one";
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