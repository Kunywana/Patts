package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayMessageActivity extends AppCompatActivity implements Recycler_Adapter.ItemClickListener {
    Recycler_Adapter messageAdapter;
    String[] emails = {"kunywananigyep@gmail.com", "amgraymofficial@gmail.com", "ninsiimadoreen85@gmail.com"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra("MESSAGE");
        TextView messageView = findViewById(R.id.messageTextView);
        messageView.setText(message);


        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("RecyclerView Messages : " + message);
        arrayList.add("Hi ");
        arrayList.add("What are your plans today?");
        arrayList.add("Would you like some Ice Cream?");
        arrayList.add("Do you have a boyfriend,");
        arrayList.add("Dora?");

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageAdapter = new Recycler_Adapter(this, arrayList);
        messageAdapter.setClickListener(this);
        recyclerView.setAdapter(messageAdapter);


    }

    public void onClose(View view) {
        super.finish();
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + messageAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contacts, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.email:
                Intent emailMe = new Intent(Intent.ACTION_SEND);
                emailMe.putExtra(Intent.EXTRA_EMAIL, emails);
                emailMe.putExtra(Intent.EXTRA_SUBJECT, "Help");
                emailMe.setType("mes/rfc822");
                startActivity(Intent.createChooser(emailMe, "Select GMail to send..."));
                break;

            case R.id.call:
                Intent callMe = new Intent(Intent.ACTION_CALL);
                callMe.setData(Uri.parse("tel:0702242197"));
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Please Grant the app Phone Permissions!", Toast.LENGTH_SHORT).show();
                    requestPermissions();
                }
                else {
                    startActivity(callMe);
                }
                break;

            case R.id.alarm:
                startActivity(new Intent(getApplicationContext(),SetAlarm.class));
                break;
            default:
                break;

        }

        return true;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},1);
    }
}
