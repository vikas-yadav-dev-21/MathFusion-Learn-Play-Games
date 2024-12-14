package com.technicalsad.mathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BadgesActivity extends AppCompatActivity {

    TextView badgetxt1,badgetxt2,badgetxt3,badgetxt4,badgetxt5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badges);

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        badgetxt1=findViewById(R.id.badge1txt);
        badgetxt2=findViewById(R.id.badge2txt);
        badgetxt3=findViewById(R.id.badge3txt);
        badgetxt4=findViewById(R.id.badge4txt);
        badgetxt5=findViewById(R.id.badge5txt);

        SharedPreferences badge = getSharedPreferences("badges", MODE_PRIVATE);
        String badge1=badge.getString("badge1","");
        String badge2=badge.getString("badge2","");
        String badge3=badge.getString("badge3","");
        String badge4=badge.getString("badge4","");
        String badge5=badge.getString("badge5","");

        if (!badge1.equals("lock")){
            badgetxt1.setVisibility(View.GONE);
        }
        if (!badge2.equals("lock")){
            badgetxt2.setVisibility(View.GONE);
        }
        if (!badge3.equals("lock")){
            badgetxt3.setVisibility(View.GONE);
        }
        if (!badge4.equals("lock")){
            badgetxt4.setVisibility(View.GONE);
        }
        if (!badge5.equals("lock")){
            badgetxt5.setVisibility(View.GONE);
        }

        badgetxt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create dialog to confirm exit the quiz
                Dialog unlockdialog = new Dialog(BadgesActivity.this);
                unlockdialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                unlockdialog.setCancelable(false);
                unlockdialog.setContentView(R.layout.badge_unlock_dialog);
                unlockdialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                unlockdialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                Window window = unlockdialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                Button unlock;
                ImageView cross;
                TextView unlockcoin;

                unlock=unlockdialog.findViewById(R.id.unlock);
                unlockcoin=unlockdialog.findViewById(R.id.unlockcoin);
                cross=unlockdialog.findViewById(R.id.cross);

                unlockcoin.setText("10,000 Coins");

                cross.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        unlockdialog.dismiss();
                    }
                });

                unlock.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        SharedPreferences sharedPreferences = getSharedPreferences("VPoint", MODE_PRIVATE);
                        int coin=sharedPreferences.getInt("vpoint",0);

                        if (coin>10000){
                            SharedPreferences.Editor myEdit = sharedPreferences.edit();
                            // write all the data entered by the user in SharedPreference and apply
                            myEdit.putInt("vpoint", coin-10000);
                            myEdit.apply();

                            SharedPreferences badge = getSharedPreferences("badges", MODE_PRIVATE);
                            SharedPreferences.Editor badgeedit=badge.edit();

                            badgeedit.putString("badge1","unlock");
                            badgeedit.apply();

                            Toast.makeText(BadgesActivity.this, "Purchase successfull ", Toast.LENGTH_SHORT).show();

                            Intent intent=new Intent(BadgesActivity.this,BadgesActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Dialog coinnotenoughdialog = new Dialog(BadgesActivity.this);
                            coinnotenoughdialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            coinnotenoughdialog.setCancelable(false);
                            coinnotenoughdialog.setContentView(R.layout.coin_not_enough_dialog);
                            coinnotenoughdialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            coinnotenoughdialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                            Window window = coinnotenoughdialog.getWindow();
                            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                            Button okay;
                            ImageView cross;

                            okay=coinnotenoughdialog.findViewById(R.id.okay);
                            cross=coinnotenoughdialog.findViewById(R.id.cross);

                            cross.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    coinnotenoughdialog.dismiss();
                                }
                            });

                            okay.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    coinnotenoughdialog.dismiss();
                                }
                            });

                            coinnotenoughdialog.show();
                        }

                        unlockdialog.dismiss();
                    }
                });

                unlockdialog.show();
            }
        });

        badgetxt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create dialog to confirm exit the quiz
                Dialog unlockdialog = new Dialog(BadgesActivity.this);
                unlockdialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                unlockdialog.setCancelable(false);
                unlockdialog.setContentView(R.layout.badge_unlock_dialog);
                unlockdialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                unlockdialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                Window window = unlockdialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                Button unlock;
                ImageView cross;
                TextView unlockcoin;

                unlock=unlockdialog.findViewById(R.id.unlock);
                unlockcoin=unlockdialog.findViewById(R.id.unlockcoin);
                cross=unlockdialog.findViewById(R.id.cross);

                unlockcoin.setText("20,000 Coins");

                cross.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        unlockdialog.dismiss();
                    }
                });

                unlock.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        SharedPreferences sharedPreferences = getSharedPreferences("VPoint", MODE_PRIVATE);
                        int coin=sharedPreferences.getInt("vpoint",0);

                        if (coin>20000){
                            SharedPreferences.Editor myEdit = sharedPreferences.edit();
                            // write all the data entered by the user in SharedPreference and apply
                            myEdit.putInt("vpoint", coin-20000);
                            myEdit.apply();

                            SharedPreferences badge = getSharedPreferences("badges", MODE_PRIVATE);
                            SharedPreferences.Editor badgeedit=badge.edit();

                            badgeedit.putString("badge2","unlock");
                            badgeedit.apply();

                            Toast.makeText(BadgesActivity.this, "Purchase successfull ", Toast.LENGTH_SHORT).show();


                            Intent intent=new Intent(BadgesActivity.this,BadgesActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Dialog coinnotenoughdialog = new Dialog(BadgesActivity.this);
                            coinnotenoughdialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            coinnotenoughdialog.setCancelable(false);
                            coinnotenoughdialog.setContentView(R.layout.coin_not_enough_dialog);
                            coinnotenoughdialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            coinnotenoughdialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                            Window window = coinnotenoughdialog.getWindow();
                            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                            Button okay;
                            ImageView cross;

                            okay=coinnotenoughdialog.findViewById(R.id.okay);
                            cross=coinnotenoughdialog.findViewById(R.id.cross);

                            cross.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    coinnotenoughdialog.dismiss();
                                }
                            });

                            okay.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    coinnotenoughdialog.dismiss();
                                }
                            });

                            coinnotenoughdialog.show();
                        }

                        unlockdialog.dismiss();
                    }
                });

                unlockdialog.show();
            }
        });

        badgetxt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create dialog to confirm exit the quiz
                Dialog unlockdialog = new Dialog(BadgesActivity.this);
                unlockdialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                unlockdialog.setCancelable(false);
                unlockdialog.setContentView(R.layout.badge_unlock_dialog);
                unlockdialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                unlockdialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                Window window = unlockdialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                Button unlock;
                ImageView cross;
                TextView unlockcoin;

                unlock=unlockdialog.findViewById(R.id.unlock);
                unlockcoin=unlockdialog.findViewById(R.id.unlockcoin);
                cross=unlockdialog.findViewById(R.id.cross);

                unlockcoin.setText("30,000 Coins");

                cross.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        unlockdialog.dismiss();
                    }
                });

                unlock.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        SharedPreferences sharedPreferences = getSharedPreferences("VPoint", MODE_PRIVATE);
                        int coin=sharedPreferences.getInt("vpoint",0);

                        if (coin>30000){
                            SharedPreferences.Editor myEdit = sharedPreferences.edit();
                            // write all the data entered by the user in SharedPreference and apply
                            myEdit.putInt("vpoint", coin-30000);
                            myEdit.apply();

                            SharedPreferences badge = getSharedPreferences("badges", MODE_PRIVATE);
                            SharedPreferences.Editor badgeedit=badge.edit();

                            badgeedit.putString("badge3","unlock");
                            badgeedit.apply();

                            Toast.makeText(BadgesActivity.this, "Purchase successfull ", Toast.LENGTH_SHORT).show();

                            Intent intent=new Intent(BadgesActivity.this,BadgesActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Dialog coinnotenoughdialog = new Dialog(BadgesActivity.this);
                            coinnotenoughdialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            coinnotenoughdialog.setCancelable(false);
                            coinnotenoughdialog.setContentView(R.layout.coin_not_enough_dialog);
                            coinnotenoughdialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            coinnotenoughdialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                            Window window = coinnotenoughdialog.getWindow();
                            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                            Button okay;
                            ImageView cross;

                            okay=coinnotenoughdialog.findViewById(R.id.okay);
                            cross=coinnotenoughdialog.findViewById(R.id.cross);

                            cross.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    coinnotenoughdialog.dismiss();
                                }
                            });

                            okay.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    coinnotenoughdialog.dismiss();
                                }
                            });

                            coinnotenoughdialog.show();
                        }

                        unlockdialog.dismiss();
                    }
                });

                unlockdialog.show();
            }
        });

        badgetxt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create dialog to confirm exit the quiz
                Dialog unlockdialog = new Dialog(BadgesActivity.this);
                unlockdialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                unlockdialog.setCancelable(false);
                unlockdialog.setContentView(R.layout.badge_unlock_dialog);
                unlockdialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                unlockdialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                Window window = unlockdialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                Button unlock;
                ImageView cross;
                TextView unlockcoin;

                unlock=unlockdialog.findViewById(R.id.unlock);
                unlockcoin=unlockdialog.findViewById(R.id.unlockcoin);
                cross=unlockdialog.findViewById(R.id.cross);

                unlockcoin.setText("40,000 Coins");

                cross.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        unlockdialog.dismiss();
                    }
                });

                unlock.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        SharedPreferences sharedPreferences = getSharedPreferences("VPoint", MODE_PRIVATE);
                        int coin=sharedPreferences.getInt("vpoint",0);

                        if (coin>40000){
                            SharedPreferences.Editor myEdit = sharedPreferences.edit();
                            // write all the data entered by the user in SharedPreference and apply
                            myEdit.putInt("vpoint", coin-40000);
                            myEdit.apply();

                            SharedPreferences badge = getSharedPreferences("badges", MODE_PRIVATE);
                            SharedPreferences.Editor badgeedit=badge.edit();

                            badgeedit.putString("badge4","unlock");
                            badgeedit.apply();

                            Toast.makeText(BadgesActivity.this, "Purchase successfull ", Toast.LENGTH_SHORT).show();

                            Intent intent=new Intent(BadgesActivity.this,BadgesActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Dialog coinnotenoughdialog = new Dialog(BadgesActivity.this);
                            coinnotenoughdialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            coinnotenoughdialog.setCancelable(false);
                            coinnotenoughdialog.setContentView(R.layout.coin_not_enough_dialog);
                            coinnotenoughdialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            coinnotenoughdialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                            Window window = coinnotenoughdialog.getWindow();
                            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                            Button okay;
                            ImageView cross;

                            okay=coinnotenoughdialog.findViewById(R.id.okay);
                            cross=coinnotenoughdialog.findViewById(R.id.cross);

                            cross.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    coinnotenoughdialog.dismiss();
                                }
                            });

                            okay.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    coinnotenoughdialog.dismiss();
                                }
                            });

                            coinnotenoughdialog.show();
                        }

                        unlockdialog.dismiss();
                    }
                });

                unlockdialog.show();
            }
        });

        badgetxt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create dialog to confirm exit the quiz
                Dialog unlockdialog = new Dialog(BadgesActivity.this);
                unlockdialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                unlockdialog.setCancelable(false);
                unlockdialog.setContentView(R.layout.badge_unlock_dialog);
                unlockdialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                unlockdialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                Window window = unlockdialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                Button unlock;
                ImageView cross;
                TextView unlockcoin;

                unlock=unlockdialog.findViewById(R.id.unlock);
                unlockcoin=unlockdialog.findViewById(R.id.unlockcoin);
                cross=unlockdialog.findViewById(R.id.cross);

                unlockcoin.setText("50,000 Coins");

                cross.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        unlockdialog.dismiss();
                    }
                });

                unlock.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        SharedPreferences sharedPreferences = getSharedPreferences("VPoint", MODE_PRIVATE);
                        int coin=sharedPreferences.getInt("vpoint",0);

                        if (coin>50000){
                            SharedPreferences.Editor myEdit = sharedPreferences.edit();
                            // write all the data entered by the user in SharedPreference and apply
                            myEdit.putInt("vpoint", coin-50000);
                            myEdit.apply();

                            SharedPreferences badge = getSharedPreferences("badges", MODE_PRIVATE);
                            SharedPreferences.Editor badgeedit=badge.edit();

                            badgeedit.putString("badge5","unlock");
                            badgeedit.apply();

                            Toast.makeText(BadgesActivity.this, "Purchase successfull ", Toast.LENGTH_SHORT).show();

                            Intent intent=new Intent(BadgesActivity.this,BadgesActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Dialog coinnotenoughdialog = new Dialog(BadgesActivity.this);
                            coinnotenoughdialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            coinnotenoughdialog.setCancelable(false);
                            coinnotenoughdialog.setContentView(R.layout.coin_not_enough_dialog);
                            coinnotenoughdialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            coinnotenoughdialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                            Window window = coinnotenoughdialog.getWindow();
                            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                            Button okay;
                            ImageView cross;

                            okay=coinnotenoughdialog.findViewById(R.id.okay);
                            cross=coinnotenoughdialog.findViewById(R.id.cross);

                            cross.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    coinnotenoughdialog.dismiss();
                                }
                            });

                            okay.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    coinnotenoughdialog.dismiss();
                                }
                            });

                            coinnotenoughdialog.show();
                        }

                        unlockdialog.dismiss();
                    }
                });

                unlockdialog.show();
            }
        });
    }
}