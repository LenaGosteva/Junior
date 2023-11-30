package com.example.junior.Activities;

import static android.os.Environment.DIRECTORY_DOCUMENTS;
import static android.os.Environment.getExternalStorageDirectory;
import static android.os.Environment.getExternalStoragePublicDirectory;

import android.Manifest;
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
import com.example.junior.databinding.ActivityNewBinding;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfBody;
import com.itextpdf.text.pdf.PdfWriter;

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

    private static final int STORAGE_CODE = 1000;
    HashMap<String, String> titlePAge = new HashMap<>();
    HashMap<String, String> mainText = new HashMap<>();

    private ActivityNewBinding binding;
    FieldsAdapter mainTextAdapter;
    public FieldsAdapter.UsersDocument document = new FieldsAdapter.UsersDocument();

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.typeOfField.setText("Название документа");
        binding.textOfField.setHint("Введите название тут");

        doTitleListFull();

        mainText.put("Заголовок", "");
        mainText.put("Подзаголовок", "");
        mainText.put("Абзац", "");


        FieldsAdapter titleAdapter = new FieldsAdapter(titlePAge, this);
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
//            document = new FieldsAdapter.UsersDocument(!binding.textOfField.getText().toString().isEmpty() ?
//                    binding.textOfField.getText().toString() : "document " + Math.random(),
//                    titlePAge, mainText);
//            ConvertDocumentToPDF converter = new ConvertDocumentToPDF(document);
//            try {
//                converter.documentDoMainPage();
////                converter.documentFieldsToPDF();
//            } catch (DocumentException e) {
//                throw new RuntimeException(e);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {

                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                        &&
                        checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                        &&
                        checkSelfPermission(Manifest.permission.MANAGE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    String[] permissionsW = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    String[] permissionsR = {Manifest.permission.READ_EXTERNAL_STORAGE};
                    String[] permissionsM = {Manifest.permission.MANAGE_EXTERNAL_STORAGE};
                    requestPermissions(permissionsW, STORAGE_CODE);
                    requestPermissions(permissionsR, STORAGE_CODE);
                    requestPermissions(permissionsM, STORAGE_CODE);
                } else {
                    savePDF("yguighouhoidgfhjkll,gobhphjp kg9jho");
                    Log.e("PDFCreator", "if savePDF");
                }
            } else {
                Log.e("PDFCreator", "else savePDF");

                savePDF("yguighouhoiu67d57tfyiguohipjk[nph7ihphjp kg9jho");
            }


        });
        binding.add.setOnClickListener(hjhk -> {
            binding.layoutAddHeader.setVisibility(binding.layoutAddHeader.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            binding.layoutAddSubtitle.setVisibility(binding.layoutAddSubtitle.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            binding.layoutAddTopic.setVisibility(binding.layoutAddTopic.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
        });

        binding.addHeader.setOnClickListener(h -> {
            binding.layoutAddHeader.setVisibility(View.GONE);
            binding.layoutAddSubtitle.setVisibility(View.GONE);
            binding.layoutAddTopic.setVisibility(View.GONE);
            mainTextAdapter.addToList("Заголовок" + randInt(12, 1000000), "");
        });
        binding.addSubtitle.setOnClickListener(h -> {
            binding.layoutAddHeader.setVisibility(View.GONE);
            binding.layoutAddSubtitle.setVisibility(View.GONE);
            binding.layoutAddTopic.setVisibility(View.GONE);
            mainTextAdapter.addToList("Подзаголовок" + randInt(12, 1000000), "");

        });
        binding.addTopic.setOnClickListener(h -> {
            binding.layoutAddHeader.setVisibility(View.GONE);
            binding.layoutAddSubtitle.setVisibility(View.GONE);
            binding.layoutAddTopic.setVisibility(View.GONE);
            mainTextAdapter.addToList("Абзац" + randInt(12, 1000000), "");

        });

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

    // Method for creating a pdf file from text, saving it then opening it for display
//    public void savePDF(String text) {
//
//        Toast.makeText(this, "strdyfugihojpk[", Toast.LENGTH_SHORT).show();
//        Document doc = new Document();
//
////        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
////            String file = "storage/emulated/0/Android/data/org.junior/files/Junior" + "/" + new SimpleDateFormat("yyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis()) + ".pdf";
//        //        String fileName = new SimpleDateFormat("yyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis()) + ".pdf";
//        String path = getExternalStorageDirectory()+"/";
//
//        String fileName = "fugjdkk.pdf";
//        File file = new File(path+fileName);
//        try {
//            File dir = new File(path);
//            if (!dir.exists()) {
//                dir.createNewFile();
//            }
//            file.createNewFile();
//
//            PdfWriter.getInstance(doc, new FileOutputStream(fileName));
//            doc.open();
//            if (doc.isOpen()) Toast.makeText(this, "IsOpen", Toast.LENGTH_SHORT).show();
//            Paragraph p1 = new Paragraph(text);
//            Font paraFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 36, BaseColor.BLACK);
//            p1.setAlignment(Paragraph.ALIGN_CENTER);
//            p1.setFont(paraFont);
//            doc.add(p1);
//
//
//        } catch (DocumentException de) {
//            Log.e("PDFCreator", "DocumentException:" + de);
//        } catch (IOException e) {
//            Log.e("PDFCreator", "ioException:" + e);
//            file.mkdir();
//
//
//        } finally {
//            doc.close();
//        }
//    }
    public void savePDF(String string){

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResult) {
        switch (requestCode) {
            case STORAGE_CODE:
                if (grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                    savePDF("");

                } else {
                    Toast.makeText(this, "Permission denied...", Toast.LENGTH_SHORT).show();
                }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResult);

    }
    public static File commonDocumentDirPath(String FolderName)
    {
        File dir = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
        {
            dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/" + FolderName);
        }
        else
        {
            dir = new File(getExternalStorageDirectory() + "/" + FolderName);
        }
        // Make sure the path directory exists.
        if (!dir.exists())
        {
            // Make it, if it doesn't exit
            boolean success = dir.mkdirs();
            if (!success)
            {
                dir = null;
            }
        }
        return dir;
    }
}