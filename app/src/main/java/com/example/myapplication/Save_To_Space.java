package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Save_To_Space extends AppCompatActivity {
    ImageView source;
    Button btn, gal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save__to__space);

        source = findViewById(R.id.iv_source);
        btn = findViewById(R.id.btn);
        gal = findViewById(R.id.gallery);

        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Drawable drawable = getDrawable(R.drawable.united);
                Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
                ContextWrapper wrapper = new ContextWrapper(getApplicationContext());
                File file = wrapper.getDir("Images",MODE_PRIVATE);
                file = new File(file, "manchester_united"+".jpg");
                try{
                    OutputStream stream = null;
                    stream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                    stream.flush();
                    stream.close();

                }catch (IOException e)
                {
                    e.printStackTrace();
                }
                Uri savedImageURI = Uri.parse(file.getAbsolutePath());
                Toast.makeText(Save_To_Space.this, "Image saved in internal storage.\n" + savedImageURI, Toast.LENGTH_SHORT).show();
            }
        });
        gal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable drawable = getDrawable(R.drawable.united);
                Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
                String ImagePath = MediaStore.Images.Media.insertImage(
                        getContentResolver(),
                        bitmap,
                        "manchester_united",
                        "demo_image"
                );
                Uri savedImageURI = Uri.parse(ImagePath);
                Toast.makeText(Save_To_Space.this, "Image saved in internal storage.\n" + savedImageURI, Toast.LENGTH_SHORT).show();
            }
        });
    }
}