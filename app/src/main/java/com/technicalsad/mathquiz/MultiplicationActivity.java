package com.technicalsad.mathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MultiplicationActivity extends AppCompatActivity {

    TextView firstnumber,secondnumber,option1,option2,option3,option4,times,score,vpoint;

    ImageView back,firstlife,secondlife,lastlife;

    Random random = new Random();
    ArrayList<Integer> optionList = new ArrayList<>();

    private long startTime = 0;
    private Handler handler = new Handler();
    private boolean isRunning = true;

    int currentscore=0;
    int vpointtotal;

    int lifes=3;

    int number_of_right_ans=0;
    int number_of_wrong_ans=0;


    String dialog_time="0";

    MediaPlayer musicplayer;

    String musicstate;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication);

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

        firstnumber=findViewById(R.id.firstnumber);
        secondnumber=findViewById(R.id.secondnumber);
        option1=findViewById(R.id.option1);
        option2=findViewById(R.id.option2);
        option3=findViewById(R.id.option3);
        option4=findViewById(R.id.option4);
        times=findViewById(R.id.time);
        score=findViewById(R.id.score);
        vpoint=findViewById(R.id.vpoint);
        back=findViewById(R.id.back);
        firstlife=findViewById(R.id.firstlife);
        secondlife=findViewById(R.id.secondlife);
        lastlife=findViewById(R.id.lastlife);

        score.setText("Score : " + String.valueOf(currentscore));

        // Fetching the stored data from the SharedPreference
        SharedPreferences sh = getSharedPreferences("VPoint", MODE_PRIVATE);
        vpointtotal=sh.getInt("vpoint",0);
        vpoint.setText(String.valueOf(vpointtotal));

        startTime = System.currentTimeMillis();
        handler.post(updateTimer);

        newquestion();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create dialog to confirm exit the quiz
                Dialog backdialog = new Dialog(MultiplicationActivity.this);
                backdialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                backdialog.setCancelable(false);
                backdialog.setContentView(R.layout.quiz_back_dialog);
                backdialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                backdialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                Window window = backdialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                Button cancle,exit;
                ImageView cross;

                cancle=backdialog.findViewById(R.id.cancle);
                exit= backdialog.findViewById(R.id.exit);
                cross=backdialog.findViewById(R.id.cross);

                cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        backdialog.dismiss();
                    }
                });

                cross.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        backdialog.dismiss();
                    }
                });

                exit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        backdialog.dismiss();

                        if (number_of_right_ans==0 && number_of_wrong_ans==0){
                            onBackPressed();
                        }
                        else {
                            showquizenddialog(number_of_right_ans,number_of_wrong_ans,currentscore);
                        }

                    }
                });

                backdialog.show();
            }
        });
    }

    public void newquestion(){
        option1.setBackgroundResource(R.drawable.option_bg);
        option2.setBackgroundResource(R.drawable.option_bg);
        option3.setBackgroundResource(R.drawable.option_bg);
        option4.setBackgroundResource(R.drawable.option_bg);

        int firstrandomnumber=generaterandom();
        int secondrandomnumber=generaterandom();

        if (firstrandomnumber>secondrandomnumber){
            optionList.add((firstrandomnumber*secondrandomnumber)-1);
            optionList.add((firstrandomnumber*secondrandomnumber)-2);
            optionList.add(firstrandomnumber*secondrandomnumber);
            optionList.add((firstrandomnumber*secondrandomnumber)+1);
            optionList.add(0);

            firstnumber.setText(String.valueOf(firstrandomnumber));
            secondnumber.setText("X" + String.valueOf(secondrandomnumber));


            int option1txt=random.nextInt(4);
            option1.setText(String.valueOf(optionList.get(option1txt)));
            optionList.remove(option1txt);
            option1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkanswer(option1,String.valueOf(firstrandomnumber*secondrandomnumber),option1.getText().toString().trim());
                }
            });


            int option2txt=random.nextInt(3);
            option2.setText(String.valueOf(optionList.get(option2txt)));
            optionList.remove(option2txt);
            option2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkanswer(option2,String.valueOf(firstrandomnumber*secondrandomnumber),option2.getText().toString().trim());
                }
            });

            int option3txt=random.nextInt(2);
            option3.setText(String.valueOf(optionList.get(option3txt)));
            optionList.remove(option3txt);
            option3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkanswer(option3,String.valueOf(firstrandomnumber*secondrandomnumber),option3.getText().toString().trim());
                }
            });

            int option4txt= 0;
            option4.setText(String.valueOf(optionList.get(option4txt)));
            optionList.remove(option4txt);
            option4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkanswer(option4,String.valueOf(firstrandomnumber*secondrandomnumber),option4.getText().toString().trim());
                }
            });
        }
        else {
            newquestion();
        }

    }

    public void checkanswer(View view,String rightanswer,String selectedanswer){


        if (selectedanswer.equals(rightanswer)){

            view.setBackgroundResource(R.drawable.right_bg);

            optionList.clear();

            number_of_right_ans+=1;

            currentscore+=5;
            score.setText("Score : " + String.valueOf(currentscore));

            // Creating a shared pref object with a file name "TotalScore" in private mode
            SharedPreferences sharedPreferences = getSharedPreferences("VPoint", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();

            // write all the data entered by the user in SharedPreference and apply
            myEdit.putInt("vpoint",vpointtotal+5 );
            myEdit.apply();
            // Fetching the stored data from the SharedPreference
            SharedPreferences sh = getSharedPreferences("VPoint", MODE_PRIVATE);
            vpointtotal=sh.getInt("vpoint",0);
            vpoint.setText(String.valueOf(vpointtotal));


        }
        else {
            view.setBackgroundResource(R.drawable.wrong_bg);

            optionList.clear();

            lifes-=1;
            number_of_wrong_ans+=1;

            currentscore-=1;
            score.setText("Score : " + String.valueOf(currentscore));

            // Creating a shared pref object with a file name "TotalScore" in private mode
            SharedPreferences sharedPreferences = getSharedPreferences("VPoint", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            // write all the data entered by the user in SharedPreference and apply
            myEdit.putInt("vpoint",vpointtotal-1 );
            myEdit.apply();

            // Fetching the stored data from the SharedPreference
            SharedPreferences sh = getSharedPreferences("VPoint", MODE_PRIVATE);
            vpointtotal=sh.getInt("vpoint",0);
            vpoint.setText(String.valueOf(vpointtotal));

            if (lifes==2){
                firstlife.setImageResource(R.drawable.blank_heart);
            } else if (lifes==1) {
                secondlife.setImageResource(R.drawable.blank_heart);

            } else if (lifes==0) {
                lastlife.setImageResource(R.drawable.blank_heart);
                //dialog to show score and time taken then go back to home

                showquizenddialog(number_of_right_ans,number_of_wrong_ans,currentscore);

            }

        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                newquestion();
            }
        },800);
    }


    public void showquizenddialog(int right,int wrong,int score){
        handler.removeCallbacks(updateTimer);
        Dialog dialog = new Dialog(MultiplicationActivity.this);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.quiz_end_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        TextView scoretxt,questionstxt,righttxt,wrongtxt,percentagetxt,timetaken,retry,gotohome;

        scoretxt=dialog.findViewById(R.id.gameoverscore);
        questionstxt=dialog.findViewById(R.id.questions);
        righttxt=dialog.findViewById(R.id.rightans);
        wrongtxt=dialog.findViewById(R.id.wrongans);
        percentagetxt=dialog.findViewById(R.id.percentage);
        retry=dialog.findViewById(R.id.retry);
        gotohome=dialog.findViewById(R.id.gotohome);
        timetaken=dialog.findViewById(R.id.timetaken);

        scoretxt.setText(String.valueOf(score));
        timetaken.setText(dialog_time);
        questionstxt.setText(String.valueOf(right+wrong));
        righttxt.setText(String.valueOf(right));
        wrongtxt.setText(String.valueOf(wrong));
        int percent=(right*100)/(right+wrong);
        percentagetxt.setText(String.valueOf(percent)+" %");

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MultiplicationActivity.this,MultiplicationActivity.class);
                startActivity(intent);
                finish();
                dialog.dismiss();
            }
        });

        gotohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public int generaterandom(){
        // Generate a random number between 1 and 100 (inclusive).
        int randomNumber = random.nextInt(100) + 1;
        return randomNumber;
    }

    private Runnable updateTimer = new Runnable() {
        @Override
        public void run() {
            if (isRunning) {
                long currentTime = System.currentTimeMillis() - startTime;
                updateDisplay(currentTime);
                handler.postDelayed(this, 1000);
            }
        }
    };

    private void updateDisplay(long time) {
        int seconds = (int) (time / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;
        int hours = minutes / 60;
        minutes = minutes % 60;

        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        times.setText(timeString);

        dialog_time=timeString;

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