package com.example.junior.Activities;

import static android.os.Environment.getExternalStoragePublicDirectory;

import com.itextpdf.io.font.constants.FontStyles;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.junior.Adapters.FieldsAdapter;
import com.example.junior.Classes.UsersDocument;
import com.example.junior.Controllers.AuthController;
import com.example.junior.databinding.ActivityNewBinding;
import com.itextpdf.kernel.colors.DeviceRgb;

import com.itextpdf.kernel.pdf.PdfDocument; // IMPORTANT!!!
import com.itextpdf.kernel.pdf.PdfWriter; // IMPORTANT!!!
import com.itextpdf.layout.Document; // IMPORTANT!!!
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Paragraph; // IMPORTANT!!!

import java.io.File;
import java.util.HashMap;
import java.util.Random;

public class NewActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityNewBinding binding;

    private AuthController authController;

    private FieldsAdapter titlePageAdapter;
    private FieldsAdapter mainTextAdapter;

    private HashMap<String, String> titlePage = new HashMap<>();
    private HashMap<String, String> mainText = new HashMap<>();

    private File file;

    private File file_gson;

    private UsersDocument document;


    //    public static String FONT_FILENAME = "assets/fonts/ArialRegular.ttf";

    //    private PdfFont font = PdfFontFactory.createFont(FONT_FILENAME, PdfEncodings.IDENTITY_H);

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        authController = new AuthController();

        settingUpClicksViews();

        fillEntirePage();

        initializeAdapters();
        setLayoutManagers();
    }

    private void settingUpClicksViews() {
        binding.titleRecyclerUp.setOnClickListener(this);
        binding.titleRecyclerDown.setOnClickListener(this);
        binding.save.setOnClickListener(this);
        binding.viewPDfF.setOnClickListener(this);
        binding.add.setOnClickListener(this);
        binding.addHeader.setOnClickListener(this);
        binding.addSubtitle.setOnClickListener(this);
        binding.addTopic.setOnClickListener(this);
    }

    private void fillEntirePage() {
        binding.typeOfField.setText("Название документа");
        binding.textOfField.setHint("Введите название тут:");

        doTitleListFull();
        doMainTextFull();
    }

    private void doTitleListFull() {
        titlePage.put("Заголовок", "");
        titlePage.put("Тип документа", "");
        titlePage.put("Руководитель", App.getSharedPreferences().getTeacher());
        titlePage.put("Выполняющий", App.getSharedPreferences().getName());
        titlePage.put("Место и год", App.getSharedPreferences().getPlace() + App.getSharedPreferences().getYear());
        titlePage.put("Организация", App.getSharedPreferences().getOrganization());
    }

    private void doMainTextFull() {
        mainText.put("Заголовок", "");
        mainText.put("Подзаголовок", "");
        mainText.put("Абзац", "");
    }

    private void initializeAdapters() {
        titlePageAdapter = new FieldsAdapter(titlePage, this);
        mainTextAdapter = new FieldsAdapter(mainText, this);
    }

    private void setLayoutManagers() {
        binding.recyclerToTitlePage.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerToTitlePage.setAdapter(titlePageAdapter);

        binding.recyclerToMainText.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerToMainText.setAdapter(mainTextAdapter);
    }

    public void addField(int flag, String text, int position) {
        binding.layoutAddHeader.setVisibility(flag);
        binding.layoutAddSubtitle.setVisibility(flag);
        binding.layoutAddTopic.setVisibility(flag);

        mainTextAdapter.addToList(text + randInt(12, 1_000_000), "", position);
    }

    public int randInt(int min, int max) {
        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public void savePDF(UsersDocument doc, String fileName) {
        file = new File(getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), fileName + ".pdf");
        /*
            todo | use a function getExternalFilesDir instead of a function getExternalStoragePublicDirectory(), which
            todo | can may lead to errors due to the specifics of its work
         */

        Log.e("KJGih", doc.getFields().values().toString());

        try {
            PdfDocument pdf = new PdfDocument(new PdfWriter(file));
            Document document = new Document(pdf);

            String s;
            Paragraph p;

            PdfFont font = PdfFontFactory.createFont(
                    "assets/timesnewromanpsmt.ttf",
                    PdfFontFactory.EmbeddingStrategy.FORCE_EMBEDDED);

            for (String key : doc.getFields().keySet()) {
                s = doc.getFields().get(key);

                if (key.contains("Заголовок")) {
                    p = new Paragraph(s);
                    p.setFont(font);
                    p.setFontColor(new DeviceRgb(0, 0, 0));
                    p.setFontSize(16);
                    Log.e("ifdoidfg", String.valueOf(pdf.getNumberOfPages()));
                    document.add(p);
                }
                if (key.contains("Подзаголовок")) {
                    p = new Paragraph(s);
                    p.setFont(font);
                    p.setFontColor(new DeviceRgb(0, 0, 0));
                    p.setFontSize(14);
                    document.add(p);
                }
                if (key.contains("Абзац")) {
                    p = new Paragraph(s);
                    p.setFont(font);
                    p.setFontColor(new DeviceRgb(0, 0, 0));
                    p.setFontSize(12);
                    document.add(p);
                }
            }

            document.getPdfDocument()
                    .addNewPage(2);
            
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

            Paragraph p = new Paragraph(App.gson.toJson(doc));
            document.add(p);

            document.close();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("KJGih", e.getMessage());
        }

        Toast.makeText(this, "Your PDF file is saved!", Toast.LENGTH_SHORT).show();
    }

    private void saveToNet() {

        authController.addDocumentGson(document, task -> {});

        authController.addDocument(document, task -> {
            if (task.isComplete()) {
                Toast.makeText(this, "ydtfi;guhij", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        authController.addDocumentStorage(document.getNameOfDocument() + ".pdf", Uri.fromFile(file), k -> {});

        authController.addDocumentStorage(document.getNameOfDocument() + "_json.pdf", Uri.fromFile(file_gson), k -> {});
    }

    private void saveDocument() {
        document = new UsersDocument(!binding.textOfField.getText().toString().isEmpty() ?
                binding.textOfField.getText().toString() : "document " + Math.random(),
                titlePageAdapter.getList(),
                mainTextAdapter.getList());

        document.setPathOfDocument(getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/Junior/Documents/pdf/" + document.getNameOfDocument() + ".pdf");
        /*
            todo | use a function getExternalFilesDir instead of a function getExternalStoragePublicDirectory(), which
            todo | can may lead to errors due to the specifics of its work
         */

        savePDF(document, document.nameOfDocument);
        savePDFGson(document, document.nameOfDocument);
        saveToNet();
    }

    private void goToViewActivity() {
        Intent intent = new Intent(this, ViewActivity.class);

        intent.putExtra(App.EXTRA_TO_SEE_DOCUMENT, Uri.fromFile(file));
        intent.setType("application/data");

        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.titleRecyclerUp) {
            binding.recyclerToTitlePage.setVisibility(binding.recyclerToTitlePage.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            binding.switcherDrop.showNext();
        }
        else if (view == binding.titleRecyclerDown) {
            binding.recyclerToTitlePage.setVisibility(binding.recyclerToTitlePage.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            binding.switcherDrop.showNext();
        }
        else if (view == binding.save) {
            saveDocument();
            finish();
        }
        else if (view == binding.viewPDfF) {
            goToViewActivity();
        }
        else if (view == binding.add) {
            binding.layoutAddHeader.setVisibility(binding.layoutAddHeader.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            binding.layoutAddSubtitle.setVisibility(binding.layoutAddSubtitle.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            binding.layoutAddTopic.setVisibility(binding.layoutAddTopic.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
        }
        else if (view == binding.addHeader) {
            addField(View.GONE, "Заголовок", mainTextAdapter.getKeys().size());
        }
        else if (view == binding.addSubtitle) {
            addField(View.GONE, "Подзаголовок", mainTextAdapter.getKeys().size());
        }
        else if (view == binding.addTopic) {
            addField(View.GONE, "Абзац", mainTextAdapter.getKeys().size());
        }
    }

    @Override
    protected void onStop() {
        titlePage.clear();
        mainText.clear();
        super.onStop();
    }
}