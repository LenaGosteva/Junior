package com.example.junior.Activities;

import static android.os.Environment.DIRECTORY_DOCUMENTS;
import static android.os.Environment.getExternalStorageDirectory;
import static android.os.Environment.getExternalStoragePublicDirectory;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;

import static com.itextpdf.io.font.constants.StandardFonts.TIMES_ROMAN;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.junior.Adapters.FieldsAdapter;
import com.example.junior.Classes.UsersDocument;
import com.example.junior.Controllers.AuthController;
import com.example.junior.Controllers.ConvertDocumentToPDF;
import com.example.junior.databinding.ActivityNewBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.itextpdf.kernel.colors.DeviceRgb;

import com.itextpdf.kernel.pdf.PdfDocument; // IMPORTANT!!!
import com.itextpdf.kernel.pdf.PdfWriter; // IMPORTANT!!!
import com.itextpdf.layout.Document; // IMPORTANT!!!
import com.itextpdf.layout.element.Paragraph; // IMPORTANT!!!


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;


public class NewActivity extends AppCompatActivity {
    AuthController authController;
    private static final int STORAGE_CODE = 1000;
    HashMap<String, String> titlePAge = new HashMap<>();
    HashMap<String, String> mainText = new HashMap<>();

    private ActivityNewBinding binding;
    FieldsAdapter mainTextAdapter;
    FieldsAdapter titleAdapter;
    File file;
    File file_gson;
    PdfFont font;

    public UsersDocument document = new UsersDocument();

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.typeOfField.setText("Название документа");
        binding.textOfField.setHint("Введите название тут");
        authController = new AuthController();

        doTitleListFull();

        mainText.put("Заголовок", "");
        mainText.put("Подзаголовок", "");
        mainText.put("Абзац", "");


        titleAdapter = new FieldsAdapter(titlePAge, this);
        mainTextAdapter = new FieldsAdapter(mainText, this);
        binding.recyclerToMainText.setLayoutManager(new LinearLayoutManager(getApplication()));
        binding.recyclerToMainText.setAdapter(mainTextAdapter);
        binding.recyclerToTitlePage.setLayoutManager(new LinearLayoutManager(getApplication()));
        binding.recyclerToTitlePage.setAdapter(titleAdapter);

        binding.titleRecyclerUp.setOnClickListener(gbh -> {

            binding.recyclerToTitlePage.setVisibility(binding.recyclerToTitlePage.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            binding.switcherDrop.showNext();
        });
        binding.titleRecyclerDown.setOnClickListener(gbh -> {

            binding.recyclerToTitlePage.setVisibility(binding.recyclerToTitlePage.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            binding.switcherDrop.showNext();
        });
        binding.save.setOnClickListener(fghjk -> {
            clickSave(false);
            finish();

        });
        binding.viewPDfF.setOnClickListener(fg -> {
            clickSave(true);

        });

        binding.add.setOnClickListener(hjhk -> {
            binding.layoutAddHeader.setVisibility(binding.layoutAddHeader.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            binding.layoutAddSubtitle.setVisibility(binding.layoutAddSubtitle.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            binding.layoutAddTopic.setVisibility(binding.layoutAddTopic.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
        });

        binding.addHeader.setOnClickListener(h -> {

            addField(View.GONE, "Заголовок", mainTextAdapter.getKeys().size());
        });
        binding.addSubtitle.setOnClickListener(h -> {

            addField(View.GONE, "Подзаголовок", mainTextAdapter.getKeys().size());

        });
        binding.addTopic.setOnClickListener(h -> {
            addField(View.GONE, "Абзац", mainTextAdapter.getKeys().size());

        });

    }

    public void addField(int flag, String text, int position) {
        binding.layoutAddHeader.setVisibility(flag);
        binding.layoutAddSubtitle.setVisibility(flag);
        binding.layoutAddTopic.setVisibility(flag);

        mainTextAdapter.addToList(text + randInt(12, 1000000), "", position);
    }

    public int randInt(int min, int max) {
        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public void doTitleListFull() {
        titlePAge.put("Заголовок", "");
        titlePAge.put("Тип документа", "");
        titlePAge.put("Руководитель", App.getSharedPreferences().getTeacher());
        titlePAge.put("Выполняющий", App.getSharedPreferences().getName());
        titlePAge.put("Место и год", App.getSharedPreferences().getPlace() + App.getSharedPreferences().getYear());
        titlePAge.put("Организация", App.getSharedPreferences().getOrganization());
    }

    public void savePDF(UsersDocument doc, String fileName) {
        File file1 = new File(getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)+"/Junior/Documents/pdf/");

        file = new File(file1, fileName + ".pdf");
        Log.e("KJGih", doc.getFields().values().toString());

        try {
            PdfDocument pdf = new PdfDocument(new PdfWriter(file));
            Document document = new Document(pdf);
            for (String s : doc.getFields().values()) {
                Paragraph p = new Paragraph(s);
                p.setFontColor(new DeviceRgb(0, 0, 0));
                document.add(p);
            }

            document.close();

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("KJGih", e.getMessage());
        }

        Toast.makeText(this, "Your PDF file is saved!", Toast.LENGTH_SHORT).show();
    }
    public void savePDFGson(UsersDocument doc, String fileName) {

        file_gson = new File(getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), fileName + "_json.pdf");
        Log.e("KJGih", doc.getFields().values().toString());

        try {
            PdfDocument pdf = new PdfDocument(new PdfWriter(file_gson));
            Document document = new Document(pdf);
            ;
            Paragraph p = new Paragraph(App.gson.toJson(doc));
            document.add(p);

            document.close();

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("KJGih", e.getMessage());
        }

        Toast.makeText(this, "Your PDF file is saved!", Toast.LENGTH_SHORT).show();
    }

    private void goFile() {

        Intent intent = new Intent(this, ViewActivity.class);

        intent.putExtra(App.EXTRA_TO_SEE_DOCUMENT, Uri.fromFile(file));
        intent.setType("application/data");

        startActivity(intent);
    }

    private void clickSave(boolean goToViewActivity) {
        document = new UsersDocument(!binding.textOfField.getText().toString().isEmpty() ?
                binding.textOfField.getText().toString() : "document " + Math.random(),
                titleAdapter.getList(), mainTextAdapter.getList());
        document.setPathOfDocument(getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/Junior/Documents/pdf/" + document.getNameOfDocument() + ".pdf");
        savePDF(document, document.nameOfDocument);
        savePDFGson(document, document.nameOfDocument);
        saveToNet();
        if (goToViewActivity) {
            goFile();
        }
    }

    private void saveToNet() {

        authController.addDocumentGson(document, task -> {
        });
        authController.addDocument(document, task -> {
            if (task.isComplete()) Toast.makeText(this, "ydtfi;guhij", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        });
        authController.addDocumentStorage(document.getNameOfDocument() + ".pdf", Uri.fromFile(file), k -> {
        });
        authController.addDocumentStorage(document.getNameOfDocument() + "_json.pdf", Uri.fromFile(file_gson), k -> {
        });
    }

    @Override
    protected void onStop() {
        titlePAge = null;
        mainText = null;
        super.onStop();
    }
}