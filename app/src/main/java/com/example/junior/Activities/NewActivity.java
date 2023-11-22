package com.example.junior.Activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.junior.Adapters.FieldsAdapter;
import com.example.junior.Controllers.ConvertDocumentToPDF;
import com.example.junior.databinding.ActivityNewBinding;
import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;


public class NewActivity extends AppCompatActivity {

    HashMap<String, String> titlePAge = new HashMap<>();
    HashMap<String, String> mainText = new HashMap<>();

    private ActivityNewBinding binding;
    FieldsAdapter mainTextAdapter;
    public FieldsAdapter.UsersDocument document = new FieldsAdapter.UsersDocument();

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

        binding.titleRecyclerUpDown.setOnClickListener(gbh->{
            binding.recyclerToTitlePage.setVisibility(binding.recyclerToTitlePage.getVisibility()==View.VISIBLE ? View.GONE:View.VISIBLE);
        });
        binding.save.setOnClickListener(fghjk -> {
            document = new FieldsAdapter.UsersDocument(!binding.textOfField.getText().toString().isEmpty() ?
                    binding.textOfField.getText().toString() : "document " + Math.random(),
                    titlePAge, mainText);
            ConvertDocumentToPDF converter = new ConvertDocumentToPDF(document);
            try {
                converter.documentDoMainPage();
//                converter.documentFieldsToPDF();
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        binding.add.setOnClickListener(hjhk -> {
            binding.layoutAddHeader.setVisibility(binding.layoutAddHeader.getVisibility()==View.VISIBLE?View.GONE:View.VISIBLE);
            binding.layoutAddSubtitle.setVisibility(binding.layoutAddSubtitle.getVisibility()==View.VISIBLE?View.GONE:View.VISIBLE);
            binding.layoutAddTopic.setVisibility(binding.layoutAddTopic.getVisibility()==View.VISIBLE?View.GONE:View.VISIBLE);
        });

        binding.addHeader.setOnClickListener(h->{
            binding.layoutAddHeader.setVisibility(View.GONE);
            binding.layoutAddSubtitle.setVisibility(View.GONE);
            binding.layoutAddTopic.setVisibility(View.GONE);
            mainTextAdapter.addToList("Заголовок"+randInt(12, 1000000), "");
        });binding.addSubtitle.setOnClickListener(h->{
            binding.layoutAddHeader.setVisibility(View.GONE);
            binding.layoutAddSubtitle.setVisibility(View.GONE);
            binding.layoutAddTopic.setVisibility(View.GONE);
            mainTextAdapter.addToList("Подзаголовок"+randInt(12, 1000000), "");

        });
        binding.addTopic.setOnClickListener(h -> {
            binding.layoutAddHeader.setVisibility(View.GONE);
            binding.layoutAddSubtitle.setVisibility(View.GONE);
            binding.layoutAddTopic.setVisibility(View.GONE);
            mainTextAdapter.addToList("Абзац"+randInt(12, 1000000), "");

        });

    }
    public  int randInt(int min, int max) {
        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    public void doTitleListFull(){
        titlePAge.put("Заголовок", "");
        titlePAge.put("Тип документа", "");
        titlePAge.put("Руководитель", App.getSharedPreferences().getTeacher());
        titlePAge.put("Выполняющий",App.getSharedPreferences().getName());
        titlePAge.put("Место и год",App.getSharedPreferences().getPlace()+", "+App.getSharedPreferences().getYear());
        titlePAge.put("Организация",App.getSharedPreferences().getOrganization());
    }
}