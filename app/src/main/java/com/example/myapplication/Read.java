package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class Read extends AppCompatActivity {
    ImageView pdfview;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        pdfview = findViewById(R.id.pdf);

        try{
            openPDF();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "went wrong:  :"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void openPDF() throws IOException {

        //open file in assets

        AssetManager assetManager = getAssets();
        AssetFileDescriptor assetFileDescriptor =
                assetManager.openFd("NewFile.pdf");
        ParcelFileDescriptor fileDescriptor =
                assetFileDescriptor.getParcelFileDescriptor();

        //open file from sdcard
        /*
        String targetPdf = "/sdcard/test.pdf";
        File file = new File(targetPdf);

        ParcelFileDescriptor fileDescriptor = null;
        fileDescriptor = ParcelFileDescriptor.open(
                file, ParcelFileDescriptor.MODE_READ_ONLY);
        */

        //min. API Level 21
        PdfRenderer pdfRenderer = null;
        pdfRenderer = new PdfRenderer(fileDescriptor);

        final int pageCount = pdfRenderer.getPageCount();
        Toast.makeText(this,
                "pageCount = " + pageCount,
                Toast.LENGTH_LONG).show();

        //Display page 0
        PdfRenderer.Page rendererPage = pdfRenderer.openPage(1);
        int rendererPageWidth = rendererPage.getWidth();
        int rendererPageHeight = rendererPage.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(
                rendererPageWidth,
                rendererPageHeight,
                Bitmap.Config.ARGB_8888);
        rendererPage.render(bitmap, null, null,
                PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);

        pdfview.setImageBitmap(bitmap);
        rendererPage.close();

        pdfRenderer.close();

        assetFileDescriptor.close();
    }


    private void copyPDF(InputStream in, OutputStream out) throws IOException {
        byte [] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0 ,read);
        }

    }
}
