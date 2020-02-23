package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Songs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        MediaPlayer mediaPlayer = MediaPlayer.create(Songs.this,R.raw.givenchy);
        mediaPlayer.start();

        ListView listView = findViewById(R.id.songs);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Intentions - Justin Beiber");
        arrayList.add("How do you sleep - Sam Smith");
        arrayList.add("Sore eyes - Calum Scott");
        arrayList.add("Ocean eyes - Billie Eilish");
        arrayList.add("Yummy - Justin Beiber");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

    }
}
