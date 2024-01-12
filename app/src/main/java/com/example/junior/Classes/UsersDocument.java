package com.example.junior.Classes;

import java.util.HashMap;

public class UsersDocument {
    public String nameOfDocument = "Документ";

    /*
        todo | It's better to call this field "titlePageInfo",
        todo | because can be confused with something related with main text on New Activity
    */
    public HashMap<String, String> mainInfo;

    // todo It's better to call this field "mainTextInfo" to maintain uniformity
    public HashMap<String, String> fields;

    public String pathOfDocument = "Документ"; // todo | where is a path?

    public String type = "Документ";

    public UsersDocument(String nameOfDocument, HashMap<String, String> mainInfo, HashMap<String, String> fields) {
        this.nameOfDocument = nameOfDocument;
        this.mainInfo = mainInfo;
        this.fields = fields;
    }

    public UsersDocument() {
        this.fields = new HashMap<>();
        this.mainInfo = new HashMap<>();
    }

    public void setPathOfDocument(String pathOfDocument) {
        this.pathOfDocument = pathOfDocument;
    }

    public String getNameOfDocument() {
        return nameOfDocument;
    }

    public HashMap<String, String> getMainInfo() {
        return mainInfo;
    }

    public HashMap<String, String> getFields() {
        return fields;
    }

    public String getPathOfDocument() {
        return pathOfDocument;
    }

    public String getType() {
        return type;
    }
}