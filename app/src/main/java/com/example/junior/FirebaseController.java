package com.example.junior;

import android.net.Uri;

import com.example.junior.Classes.UsersDocument;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.itextpdf.text.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class FirebaseController {

    private final FirebaseAuth auth;

    DatabaseReference database = FirebaseDatabase.getInstance("https://junior-e9e5e-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
    FirebaseStorage storage = FirebaseStorage.getInstance("gs://junior-e9e5e.appspot.com/");
    // Create a storage reference from our app
    StorageReference storageRef;

    public FirebaseController() {
        this.auth = FirebaseAuth.getInstance();
        storageRef = storage.getReference(this.auth.getUid());
    }
    public void saveToDB(UsersDocument document,File file,  OnCompleteListener listener) throws FileNotFoundException {
        if (isAuth()){
            database.child(auth.getUid()).child(document.getNameOfDocument()).child("titlePage").setValue(document.mainInfo).addOnCompleteListener(listener);
            storageRef.child(auth.getUid()).child(document.pathOfDocument).putFile(Uri.fromFile(file));
        }
    }



    public FirebaseController(FirebaseAuth auth) {
        this.auth = auth;
    }

    public boolean isAuth() {
        return auth.getCurrentUser() != null;
    }

    public FirebaseUser getUser() {
        return auth.getCurrentUser();
    }
    public void registerUser(String email, String password, OnCompleteListener listener) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(listener);
        storageRef = storage.getReference(this.auth.getUid());

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

}
