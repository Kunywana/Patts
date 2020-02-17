package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}


