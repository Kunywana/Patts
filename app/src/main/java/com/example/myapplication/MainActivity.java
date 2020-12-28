package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {
    public final static String KEY_EXTRA_CONTACT_ID = "KEY_EXTRA_CONTACT_ID";
    ExampleDBHelper dbHelper;
    android.widget.ListView listView;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView1);
        BatteryManager bm = (BatteryManager) getSystemService(BATTERY_SERVICE);
        int batLeveL = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

        Toast.makeText(this, ""+batLeveL+"%", Toast.LENGTH_SHORT).show();

        dbHelper = new ExampleDBHelper(this);

        final Cursor cursor = dbHelper.getAllPersons();
        String [] columns = new String[] {
                ExampleDBHelper.PERSON_COLUMN_ID,
                ExampleDBHelper.PERSON_COLUMN_NAME
        };
        int [] widgets = new int[] {
                R.id.personID,
                R.id.personName
        };

        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, R.layout.person_info,
                cursor, columns, widgets, 0);
        listView.setAdapter(cursorAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                Cursor itemCursor = (Cursor) MainActivity.this.listView.getItemAtPosition(position);
                int personID = itemCursor.getInt(itemCursor.getColumnIndex(ExampleDBHelper.PERSON_COLUMN_ID));
                Intent intent = new Intent(getApplicationContext(), CreateOrEditActivity.class);
                intent.putExtra(KEY_EXTRA_CONTACT_ID, personID);
                startActivity(intent);
            }
        });

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

            case R.id.save:
                startActivity(new Intent(getApplicationContext(),Save_To_Space.class));
                break;

            case R.id.sc:
                startActivity(new Intent(getApplicationContext(),StudentProvider.class));
                break;

            case R.id.sql:
                Intent intent = new Intent(MainActivity.this, CreateOrEditActivity.class);
                intent.putExtra(KEY_EXTRA_CONTACT_ID, 0);
                startActivity(intent);
                break;

            case R.id.call:
                Intent call = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", "+256758838398", null));

                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    requestPermissions();
                }
                else {
                    startActivity(call);
                }
                break;
            default:
                break;

        }

        return true;
    }
    private void requestPermissions() {
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
    }
    public void alarm(View view) {
        startActivity(new Intent(getApplicationContext(),Alarm.class));
    }
}


