package com.example.junior.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.junior.R;
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

            binding.pdfView.setBackgroundColor(ContextCompat.getColor(this, R.color.grey));

            Log.i("extra2", fileUri.toString());

            if (fileUri != null) {
                binding.pdfView.fromUri(fileUri)
                        .spacing(10)
                        .load();
            }
        }
    }
}