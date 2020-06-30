package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Alarm extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_ringing);

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), notification);
        mediaPlayer.start();

        TextView dismiss = findViewById(R.id.dismiss);
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
//                Intent stopBroadCast = new Intent(Intent.ACTION_MAIN);
//                stopBroadCast.addCategory(Intent.CATEGORY_LAUNCHER);
//                stopBroadCast.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(stopBroadCast);
                finish();
                startActivity(new Intent(getApplicationContext(),DisplayMessageActivity.class));
            }
        });
    }
}
