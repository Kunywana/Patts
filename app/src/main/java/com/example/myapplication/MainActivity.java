package com.example.myapplication;

import android.content.Intent;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BatteryManager bm = (BatteryManager) getSystemService(BATTERY_SERVICE);
        int batLeveL = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

        Toast.makeText(this, ""+batLeveL+"%", Toast.LENGTH_SHORT).show();

    }

    public void sendMessage(View view){
        EditText message= (EditText)findViewById(R.id.message);
        Toast.makeText(this, "Sending message", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),DisplayMessageActivity.class);
        intent.putExtra("MESSAGE",message.getText().toString());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.songs:
                startActivity(new Intent(getApplicationContext(),Songs.class));
                break;

            case R.id.read:
                startActivity(new Intent(getApplicationContext(),Read.class));
                break;
            default:
                break;

        }

        return true;
    }

    public void alarm(View view) {
        startActivity(new Intent(getApplicationContext(),Alarm.class));
    }
}


