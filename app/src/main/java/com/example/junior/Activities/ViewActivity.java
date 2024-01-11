package com.example.junior.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.junior.R;
import com.example.junior.databinding.ActivityViewBinding;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;

public class ViewActivity extends AppCompatActivity {

    private ActivityViewBinding binding;

    private Bundle bundle;

    private Uri fileUri;
    private String fileUriS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bundle = getIntent().getExtras();

        fileUri = (Uri) bundle.get(App.EXTRA_TO_SEE_DOCUMENT);

        Log.i("extra2", fileUri.toString());
        if (fileUri != null) {

                      binding.pdfView.fromUri(fileUri).load();



        }
    }
}