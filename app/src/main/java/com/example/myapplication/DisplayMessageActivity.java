package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayMessageActivity extends AppCompatActivity implements Recycler_Adapter.ItemClickListener{
    Recycler_Adapter messageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra("MESSAGE");
        TextView messageView = findViewById(R.id.messageTextView);
        messageView.setText(message);


        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("RecyclerView Messages : "+message);
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

    public void onClose(View view){
        super.finish();
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + messageAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}
