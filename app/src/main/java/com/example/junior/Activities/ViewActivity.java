package com.example.junior.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.junior.databinding.ActivityViewBinding;

public class ViewActivity extends AppCompatActivity {

    private ActivityViewBinding binding;

    private Bundle bundle;

    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bundle = getIntent().getExtras();

        if (bundle != null) {
            fileUri = (Uri) bundle.get(App.EXTRA_TO_SEE_DOCUMENT);

            Log.i("extra2", fileUri.toString());

            if (fileUri != null) {
                binding.pdfView.fromUri(fileUri).load();
            }
        }
    }
}