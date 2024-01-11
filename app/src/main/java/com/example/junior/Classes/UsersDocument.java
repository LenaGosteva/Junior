package com.example.junior.Classes;

import android.net.Uri;

import java.util.HashMap;

public class UsersDocument {
       public String nameOfDocument = "Документ";
       public String pathOfDocument = "Документ";
       public String type = "Документ";

       public HashMap<String, String> mainInfo;
       public HashMap<String, String> fields;

       public String getNameOfDocument() {
          return nameOfDocument;
       }

       public void setNameOfDocument(String nameOfDocument) {
          this.nameOfDocument = nameOfDocument;
       }

       public String getType() {
          return type;
       }

       public void setType(String type) {
          this.type = type;
       }

       public HashMap<String, String> getFields() {
          return fields;
       }

       public void setFields(HashMap<String, String> fields) {
          this.fields = fields;
       }
       public UsersDocument(){
          this.type = "Документ";
          this.nameOfDocument = "Документ";
          this.fields = new HashMap<>();
          this.mainInfo = new HashMap<>();
       }

       public String getPathOfDocument() {
          return pathOfDocument;
       }

       public void setPathOfDocument(String pathOfDocument) {
          this.pathOfDocument = pathOfDocument;
       }

       public HashMap<String, String> getMainInfo() {
          return mainInfo;
       }

       public void setMainInfo(HashMap<String, String> mainInfo) {
          this.mainInfo = mainInfo;
       }

       public UsersDocument(String nameOfDocument,  HashMap<String, String>  mainInfo, HashMap<String, String> fields) {
          this.nameOfDocument = nameOfDocument;
          this.fields = fields;
          this.mainInfo = mainInfo;
       }

    }