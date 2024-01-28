package com.example.junior.Controllers;

import android.net.Uri;

import com.example.junior.Classes.UsersDocument;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;

import java.util.HashMap;

public class AuthController {
    private final FirebaseAuth auth;

    FirebaseFirestore firebase;
    DatabaseReference database;
    DatabaseReference usersBase;
    FirebaseStorage storage;
    // Create a storage reference from our app
    StorageReference storageRef;
    StorageReference usersStorage;

    public AuthController() {
        this.auth = FirebaseAuth.getInstance();
        // Get a non-default Storage bucket
        this.storage = FirebaseStorage.getInstance("gs://junior-2752e.appspot.com");
        this.database = FirebaseDatabase.getInstance("https://junior-2752e-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        this.storageRef = storage.getReference();
        if (isAuth()) {
            usersBase = database.child("users").child(getUser().getUid());
            this.usersStorage = storageRef.child("users").child(getUser().getUid());
        }
    }

    public AuthController(FirebaseAuth auth) {
        this.auth = auth;
    }

    public boolean isAuth() {
        return auth.getCurrentUser() != null;
    }

    public FirebaseUser getUser() {
        return auth.getCurrentUser();
    }

    public void addDocument(UsersDocument document, OnCompleteListener listener) {
        if (isAuth()) {
            addHeaderHashMap(document.nameOfDocument, document.getMainInfo(), listener);
            addMainTextHashMap(document.nameOfDocument, document.getFields(), listener);
            addNameOfDoc(document.nameOfDocument, document.getNameOfDocument(), listener);
            addPatheOfDoc(document.nameOfDocument, document.getPathOfDocument(), listener);
            addTypeOfDoc(document.nameOfDocument, document.getType(), listener);
        }
    }

    public void addDocumentStorage(String name, Uri file, OnCompleteListener listener) {
        if (isAuth())
            storageRef.child("users").child(getUser().getUid()).child(name).putFile(file).addOnCompleteListener(listener);
    }

    public void addDocumentGson(UsersDocument document, OnCompleteListener listener) {
        if (isAuth()) {
            String doc = new Gson().toJson(document);
            database.child("users").child(getUser().getUid()).child(document.nameOfDocument + "_gson").setValue(doc).addOnCompleteListener(listener);
        }
    }

    public void getDocumentGson(String document, OnCompleteListener<DataSnapshot> listener) {
        if (isAuth())
            database.child("users").child(getUser().getUid()).child(document).get().addOnCompleteListener(listener);
    }


    private void addMainTextHashMap(String doc, HashMap<String, String> list, OnCompleteListener listener) {

        database.child("users").child(getUser().getUid()).child(doc).child("fields").setValue(list).addOnCompleteListener(listener);
    }

    private void addHeaderHashMap(String doc, HashMap<String, String> list, OnCompleteListener listener) {
        database.child("users").child(getUser().getUid()).child(doc).child("mainInfo").setValue(list).addOnCompleteListener(listener);
    }

    private void addNameOfDoc(String doc, String name, OnCompleteListener listener) {
        database.child("users").child(getUser().getUid()).child(doc).child("nameOfDocument").setValue(name).addOnCompleteListener(listener);
    }

    private void addPatheOfDoc(String doc, String path, OnCompleteListener listener) {
        database.child("users").child(getUser().getUid()).child(doc).child("pathOfDocument").setValue(path).addOnCompleteListener(listener);
    }

    private void addTypeOfDoc(String doc, String type, OnCompleteListener listener) {
        database.child("users").child(getUser().getUid()).child(doc).child("type").setValue(type).addOnCompleteListener(listener);
    }

    public void addUserToDb(String name, OnCompleteListener listener) {
        if (isAuth()) {
            database.child("users").child(getUser().getUid()).setValue(getUser().getUid()).addOnCompleteListener(listener);
            database.child("users").child(getUser().getUid()).child("name").setValue(name).addOnCompleteListener(listener);

        }
    }

    public void getName(OnCompleteListener<DataSnapshot> listener) {
        if (isAuth())
            database.child("users").child(getUser().getUid()).child("name").get().addOnCompleteListener(listener);
    }


    public void registerUser(String email, String password, OnCompleteListener listener) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(listener);
    }


    public void updateName(String name) {
        if (isAuth()) {
            getUser().updateProfile(new UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build()
            );
            database.child("users").child(getUser().getUid()).child("nickname").setValue(name);

        }


    }

    public void sendMailWithNewPassword(String email, OnCompleteListener<Void> listener) {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.sendPasswordResetEmail(email).addOnCompleteListener(listener);
    }

    public void enterUser(String email, String password, OnCompleteListener<AuthResult> listener) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(listener);
    }

    public void singOut() {
        auth.signOut();
    }


    public FirebaseAuth getAuth() {
        return auth;
    }

    public FirebaseFirestore getFirebase() {
        return firebase;
    }

    public void setFirebase(FirebaseFirestore firebase) {
        this.firebase = firebase;
    }

    public DatabaseReference getDatabase() {
        return database;
    }

    public void setDatabase(DatabaseReference database) {
        this.database = database;
    }
}
