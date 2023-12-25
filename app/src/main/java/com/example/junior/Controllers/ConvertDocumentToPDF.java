package com.example.junior.Controllers;

import android.graphics.fonts.Font;
import android.widget.Toast;

import com.example.junior.Adapters.FieldsAdapter;
import com.example.junior.Classes.UsersDocument;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.font.otf.Glyph;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.font.PdfSimpleFont;
import com.itextpdf.kernel.pdf.PdfDictionary;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument; // IMPORTANT!!!
import com.itextpdf.kernel.pdf.PdfWriter; // IMPORTANT!!!
import com.itextpdf.layout.Document; // IMPORTANT!!!
import com.itextpdf.layout.element.Paragraph; // IMPORTANT!!!
import com.itextpdf.layout.properties.HorizontalAlignment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConvertDocumentToPDF {
    public UsersDocument document;
    public Document documentPDF;

    public ConvertDocumentToPDF(UsersDocument document, Document documentPDF) {
        this.document = document;
        this.documentPDF = documentPDF;
    }



   public Paragraph title;
   public Paragraph subtitle;
  public   Paragraph topic;

    public void documentFieldsToPDF() throws IOException {

        setTextSettings();
        for (String key : document.fields.keySet()) {
            switch (key) {
                case "title":
                    title = new Paragraph(document.fields.get(key));
                    documentPDF.add(title);
                    break;
                case "subtitle":
                    subtitle = new Paragraph(document.fields.get(key));
                    documentPDF.add(subtitle);
                    break;
                case "topic":
                    topic = new Paragraph(document.fields.get(key));
                    documentPDF.add(topic);
                    break;
            }
        }
        documentPDF.close();
    }

    public  void documentDoMainPage() throws IOException {
        for (String key : document.mainInfo.keySet()) {
            Paragraph writer = new Paragraph(document.mainInfo.get(key));
            switch (key) {
                case "Заголовок":setTextNameSettings(writer);
                    documentPDF.add(writer);
                    break;
                case "Тип документа":
                    setTextTypeSettings(writer);
                    documentPDF.add(writer);
                    break;
                case "Выполняющий":
                    writer = new Paragraph("Выполняет: "+ writer);
                    setPersonsSettings(writer);
                    documentPDF.add(writer);
                    break;
                case "Руководитель":
                    writer = new Paragraph("Руководитель: "+ writer);
                    setPersonsSettings(writer);
                    documentPDF.add(writer);
                    break;
                case "Место и год":
                    setPlaceAndYearSettings(writer);
                    documentPDF.add(writer);
                    break;
                case "Организация":
                    setTextOrganisationSettings(writer);
                    documentPDF.add(writer);
                    break;
            }
            documentPDF.close();
        }

    }

    void setTitleSettings(Paragraph string) {
        try {
            string.setFont(PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN)).setFontSize(32);

        }catch (Exception e){
        }
        string.setHorizontalAlignment(HorizontalAlignment.CENTER);
    }

    void setSubtitleSettings(Paragraph string) {
        try {
            string.setFont(PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN)).setFontSize(20);

        }catch (Exception e){
        }    }

    void setTopicSettings(Paragraph string) {
        try {
            string.setFont(PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN)).setFontSize(14);

        }catch (Exception e){
        }
        string.setFirstLineIndent(4);
    }

    void setTextOrganisationSettings(Paragraph string) {
        try {
            string.setFont(PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN)).setFontSize(32);

        }catch (Exception e){
        }
        string.setHorizontalAlignment(HorizontalAlignment.CENTER);
    }

    void setTextNameSettings(Paragraph string) {
        try {
            string.setFont(PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN)).setFontSize(40);

        }catch (Exception e){
        }
        string.setHorizontalAlignment(HorizontalAlignment.CENTER);
    }
    void setTextTypeSettings(Paragraph string) {
        string = new Paragraph(string.toString().toUpperCase());
        try {
            string.setFont(PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN)).setFontSize(40);

        }catch (Exception e){
        }
        string.setHorizontalAlignment(HorizontalAlignment.CENTER);        ;
    }

    void setPersonsSettings(Paragraph string) {
        try {
            string.setFont(PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN)).setFontSize(16);

        }catch (Exception e){
        }
        string.setHorizontalAlignment(HorizontalAlignment.RIGHT);
    }

    void setPlaceAndYearSettings(Paragraph string) {
        try {
            string.setFont(PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN)).setFontSize(32);

        }catch (Exception e){
        }
        string.setHorizontalAlignment(HorizontalAlignment.CENTER);
    }

    void setTextSettings() {
        setTitleSettings(title);
        setSubtitleSettings(subtitle);
        setTopicSettings(topic);
    }





}
