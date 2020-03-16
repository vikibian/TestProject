package com.neu.testimageload.pdfprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.neu.testimageload.R;

import java.io.File;

public class PdfReadActivity extends AppCompatActivity {
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_read);

        pdfView = findViewById(R.id.readpdf);
        Intent intent = getIntent();
        String path = intent.getStringExtra("path");
        pdfView.fromFile(new File(path)).load();
    }
}
