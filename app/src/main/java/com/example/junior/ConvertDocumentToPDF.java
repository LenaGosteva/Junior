package com.example.junior;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConvertDocumentToPDF {
    public UsersDocument document;

    Document documentPDF = new Document();
    Font fontHeader = FontFactory.getFont(FontFactory.TIMES_BOLD, 24, BaseColor.BLACK);
    Font fontNameOfDocument = FontFactory.getFont(FontFactory.TIMES_BOLD, 36, BaseColor.BLACK);
    Font fontSubtitle = FontFactory.getFont(FontFactory.TIMES, 20, BaseColor.BLACK);
    Font fontTopic = FontFactory.getFont(FontFactory.TIMES, 16, BaseColor.BLACK);
    Font fontTopicBold = FontFactory.getFont(FontFactory.TIMES, 16, BaseColor.BLACK);

    Paragraph title;
    Paragraph subtitle;
    Paragraph topic;

    void documentFieldsToPDF() throws IOException, DocumentException {
//         PDDocument document = new PDDocument();
//         PDPage page = new PDPage();
//         document.addPage(page);
//
//         PDPageContentStream contentStream = new PDPageContentStream(document, page);
//
//         contentStream.setFont(PDType1Font.COURIER, 12);
//         contentStream.beginText();
//         contentStream.showText("Hello World");
//         contentStream.endText();
//         contentStream.close();
//
//         document.save("pdfBoxHelloWorld.pdf");
//         document.close();
        openDocumentWithNewPage();
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

    void documentDoMainPage() throws DocumentException, IOException {
        openDocumentWithNewPage();
        for (String key : document.mainInfo.keySet()) {
            Paragraph writer = new Paragraph(document.mainInfo.get(key));
            switch (key) {
                case "name":
                case "type":
                    setTextNameSettings(writer);
                    documentPDF.add(writer);
                    break;
                case "executor":
                    writer = new Paragraph("Выполняет: "+writer.toString());
                    setPersonsSettings(writer);
                    documentPDF.add(writer);
                    break;
                case "inspector":
                    writer = new Paragraph("Руководитель: "+writer.toString());
                    setPersonsSettings(writer);
                    documentPDF.add(writer);
                    break;
                case "place and year":
                    setPlaceAndYearSettings(writer);
                    documentPDF.add(writer);
                    break;
                case "organisation":
                    setTextOrganisationSettings(writer);
                    documentPDF.add(writer);
                    break;
            }
            documentPDF.close();
        }

    }

    void setTitleSettings(Paragraph string) {
        string.setFont(fontHeader);
        string.setAlignment(Element.ALIGN_CENTER);
    }

    void setSubtitleSettings(Paragraph string) {
        string.setFont(fontSubtitle);
    }

    void setTopicSettings(Paragraph string) {
        string.setFont(fontTopic);
        string.setIndentationLeft(4);
    }

    void setTextOrganisationSettings(Paragraph string) {
        string.setFont(fontSubtitle);
        string.setAlignment(Element.ALIGN_CENTER);
    }

    void setTextNameSettings(Paragraph string) {
        string.setFont(fontNameOfDocument);
        string.setAlignment(Element.ALIGN_CENTER);
    }

    void setPersonsSettings(Paragraph string) {
        string.setFont(fontTopic);
        string.setAlignment(Element.ALIGN_RIGHT);
    }

    void setPlaceAndYearSettings(Paragraph string) {
        string.setFont(fontTopicBold);
        string.setAlignment(Element.ALIGN_CENTER);
    }

    void setTextSettings() {
        setTitleSettings(title);
        setSubtitleSettings(subtitle);
        setTopicSettings(topic);
    }

    void openDocumentWithNewPage() throws DocumentException, IOException {
        openDocumentWithoutNewPage();
        documentPDF.newPage();
    }

    void openDocumentWithoutNewPage() throws IOException, DocumentException {
        PdfWriter.getInstance(documentPDF, Files.newOutputStream(Paths.get(document.getNameOfDocument() + ".pdf")));
        documentPDF.open();
    }
}
