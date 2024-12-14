package com.technicalsad.mathquiz;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.appupdate.AppUpdateOptions;
import com.google.android.play.core.install.model.ActivityResult;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.review.ReviewException;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.review.model.ReviewErrorCode;

public class MainActivity extends AppCompatActivity {

    TextView playbtn,learnbtn;
    ImageButton homebtn,musicbtn,ratingbtn,sharebtn,exitbtn,badgebtn;

    String link="https://play.google.com/store/apps/details?id=com.technicalsad.mathquiz";

    String musicState="music";
    // Instantiating the MediaPlayer class
    MediaPlayer musicplayer;

    private static final int MY_REQUEST_CODE=100;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        CheckForUpdate();

        playbtn=findViewById(R.id.playbtn);
        learnbtn=findViewById(R.id.learnbtn);
        homebtn=findViewById(R.id.homebtn);
        musicbtn=findViewById(R.id.musicbtn);
        ratingbtn=findViewById(R.id.ratingbtn);
        sharebtn=findViewById(R.id.sharebtn);
        exitbtn=findViewById(R.id.exitbtn);
        badgebtn=findViewById(R.id.badgebtn);

        // Adding the music file to our
        // newly created object music
        musicplayer = MediaPlayer.create(
                this, R.raw.music);
        musicplayer.setLooping(true);
        musicplayer.setVolume(100,100);
        musicplayer.start();


        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,CategoryActivity.class);
                intent.putExtra("musicstate",musicState);
                startActivity(intent);
            }
        });

        learnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,LearnCategoryActivity.class);
                intent.putExtra("musicstate",musicState);
                startActivity(intent);
            }
        });


        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        exitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create dialog to confirm exit the quiz
                Dialog backdialog = new Dialog(MainActivity.this);
                backdialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                backdialog.setCancelable(false);
                backdialog.setContentView(R.layout.exit_dialog);
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
                        // on below line we are finishing activity.
                        MainActivity.this.finish();
                        // on below line we are exiting our activity
                        System.exit(0);
                    }
                });

                backdialog.show();
            }
        });

        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create dialog to confirm exit the quiz
                Dialog sharedialog = new Dialog(MainActivity.this);
                sharedialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                sharedialog.setCancelable(false);
                sharedialog.setContentView(R.layout.share_dialog);
                sharedialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                sharedialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                Window window = sharedialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                ImageButton copybtn,whatsappbtn,telegrambtn,othersbtn;
                ImageView cross;


                copybtn= sharedialog.findViewById(R.id.copybtn);
                whatsappbtn=sharedialog.findViewById(R.id.whatsappbtn);
                telegrambtn=sharedialog.findViewById(R.id.telegrambtn);
                othersbtn=sharedialog.findViewById(R.id.othersbtn);
                cross=sharedialog.findViewById(R.id.cross);


                cross.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sharedialog.dismiss();
                    }
                });

                copybtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ClipboardManager clipboardManager= (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clipData=ClipData.newPlainText("codied text",link);
                        clipboardManager.setPrimaryClip(clipData);
                        Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                        sharedialog.dismiss();
                    }
                });

                whatsappbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        try {
                            Intent whatsappshare=new Intent(Intent.ACTION_SEND);
                            whatsappshare.setType("text/plane");
                            whatsappshare.setPackage("com.whatsapp");
                            whatsappshare.putExtra(Intent.EXTRA_TEXT,link);
                            startActivity(whatsappshare);
                            sharedialog.dismiss();
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Whatsapp not found", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


                telegrambtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            Intent telegramshare=new Intent(Intent.ACTION_SEND);
                            telegramshare.setType("text/plane");
                            telegramshare.setPackage("org.telegram.messenger");
                            telegramshare.putExtra(Intent.EXTRA_TEXT,link);
                            startActivity(telegramshare);
                            sharedialog.dismiss();
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Telegram not found", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                othersbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent othersshare=new Intent(Intent.ACTION_SEND);
                        othersshare.setType("text/plane");
                        othersshare.putExtra(Intent.EXTRA_TEXT,link);
                        startActivity(othersshare);
                        sharedialog.dismiss();
                    }
                });


                sharedialog.show();
            }
        });

        ratingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReviewManager manager = ReviewManagerFactory.create(MainActivity.this);
                Task<ReviewInfo> request = manager.requestReviewFlow();
                request.addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // We can get the ReviewInfo object
                        ReviewInfo reviewInfo = task.getResult();

                        Task<Void> flow = manager.launchReviewFlow(MainActivity.this, reviewInfo);
                        flow.addOnCompleteListener(task1 -> {
                            // The flow has finished. The API does not indicate whether the user
                            // reviewed or not, or even whether the review dialog was shown. Thus, no
                            // matter the result, we continue our app flow.
                        });

                    } else {
                        // There was some problem, log or handle the error code.
                        @ReviewErrorCode int reviewErrorCode = ((ReviewException) task.getException()).getErrorCode();
                    }
                });
            }
        });

        musicbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (musicState.equals("music")){
                    musicbtn.setImageResource(R.drawable.mutewooden);
                    musicState="mute";
                    musicplayer.pause();
                }
                else {
                    musicbtn.setImageResource(R.drawable.musicwooden);
                    musicState="music";
                    musicplayer.start();
                }
            }
        });

        badgebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,BadgesActivity.class);
                intent.putExtra("musicstate",musicState);
                startActivity(intent);
            }
        });


    }

    private void CheckForUpdate(){
        AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(MainActivity.this);

// Returns an intent object that you use to check for an update.
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();

// Checks that the platform will allow the specified type of update.
        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    // This example applies an immediate update. To apply a flexible update
                    // instead, pass in AppUpdateType.FLEXIBLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                // Request the update.
                try {
                    appUpdateManager.startUpdateFlowForResult(
                            // Pass the intent that is returned by 'getAppUpdateInfo()'.
                            appUpdateInfo,
                            AppUpdateType.IMMEDIATE,
                            MainActivity.this,
                            MY_REQUEST_CODE);

                } catch (IntentSender.SendIntentException e) {
                    Log.e("error", String.valueOf(e));
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == MY_REQUEST_CODE) {
            if (resultCode != RESULT_OK) {
                Log.e("error","something went wrong");
            }
        }
    }

    @Override
    public void onBackPressed() {
        //create dialog to confirm exit the quiz
        Dialog backdialog = new Dialog(MainActivity.this);
        backdialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        backdialog.setCancelable(false);
        backdialog.setContentView(R.layout.exit_dialog);
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
                // on below line we are finishing activity.
                MainActivity.this.finish();
                // on below line we are exiting our activity
                System.exit(0);
            }
        });

        backdialog.show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        musicplayer.pause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (musicState.equals("music")){
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