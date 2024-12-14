package com.technicalsad.mathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        // Fetching the stored data from the SharedPreference
        SharedPreferences sh = getSharedPreferences("VPoint", MODE_PRIVATE);
        String check = sh.getString("newuser", "yes");


        if (check.equals("yes")){
            // Creating a shared pref object with a file name "TotalScore" in private mode
            SharedPreferences sharedPreferences = getSharedPreferences("VPoint", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();

            // write all the data entered by the user in SharedPreference and apply
            myEdit.putString("newuser", "no");
            myEdit.putInt("vpoint", 0);
            myEdit.apply();

            SharedPreferences badge = getSharedPreferences("badges", MODE_PRIVATE);
            SharedPreferences.Editor badgeedit=badge.edit();

            badgeedit.putString("badge1","lock");
            badgeedit.putString("badge2","lock");
            badgeedit.putString("badge3","lock");
            badgeedit.putString("badge4","lock");
            badgeedit.putString("badge5","lock");
            badgeedit.apply();
        }



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },1000);
    }
}