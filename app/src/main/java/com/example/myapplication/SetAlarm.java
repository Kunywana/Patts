package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

public class SetAlarm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

    }

    public void thirty(View view) {
        int seconds = 30;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, seconds);
        long inMills = calendar.getTimeInMillis();
        setAlarm(inMills);
    }

    public void forty5(View view) {
        int seconds = 45;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, seconds);
        long inMills = calendar.getTimeInMillis();
        setAlarm(inMills);
    }

    public void one(View view) {
        int seconds = 60;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, seconds);
        long inMills = calendar.getTimeInMillis();
        setAlarm(inMills);
    }

    public void three(View view) {
        int seconds = 180;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, seconds);
        long inMills = calendar.getTimeInMillis();
        setAlarm(inMills);
    }

    public void ten(View view) {
        int seconds = 600;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, seconds);
        long inMills = calendar.getTimeInMillis();
        setAlarm(inMills);
    }
    public void setAlarm(long mill){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReciever.class);

        PendingIntent fakeAlarm = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        alarmManager.set(AlarmManager.RTC_WAKEUP,mill,fakeAlarm);

        Toast.makeText(this, "Alarm has been set!", Toast.LENGTH_SHORT).show();
        Intent intent1 = new Intent(getApplicationContext(),DisplayMessageActivity.class);
        startActivity(intent1);
    }
}
