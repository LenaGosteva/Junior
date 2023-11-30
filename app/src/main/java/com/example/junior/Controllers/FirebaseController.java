package com.example.junior.Controllers;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.example.junior.Activities.App;
import com.example.junior.Adapters.FieldsAdapter;
import com.example.junior.Classes.UsersDocument;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileNotFoundException;

public class FirebaseController {

    private final FirebaseAuth auth;
    private GoogleSignIn client;
    GoogleSignInOptions gso;
    GoogleSignInClient googleSignInClient;
    DatabaseReference database;
     StorageReference storage;
    // Create a storage reference from our app
    StorageReference storageRef;
    Activity activity;

    public FirebaseController() {
        FirebaseApp.initializeApp(App.getInstance());
        this.auth = FirebaseAuth.getInstance(FirebaseApp.initializeApp(App.getInstance())
);
        gso = new
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("220168828586-7e58dqb3ppnvep31i9tfn8d1aqerp5qb.apps.googleusercontent.com")
                .requestEmail()
                .build();
        database = FirebaseDatabase.getInstance("https://junior-2752e-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        googleSignInClient = GoogleSignIn.getClient(App.getInstance(), gso);
        storage = FirebaseStorage.getInstance("gs://junior-2752e.appspot.com").getReference();
    }
    public void saveToDB(UsersDocument document, File file, OnCompleteListener listener) throws FileNotFoundException {
        if (isAuth()){
            database.child(auth.getUid()).child(document.getPathOfDocument()).child("titlePage").setValue(document.getMainInfo()).addOnCompleteListener(listener);
            database.child(auth.getUid()).child(document.getPathOfDocument()).child("mainText").setValue(document.getFields()).addOnCompleteListener(listener);
            database.child(auth.getUid()).child(document.getPathOfDocument()).child("name").setValue(document.getFields()).addOnCompleteListener(listener);
            storage.child(auth.getUid()).child(document.pathOfDocument).putFile(Uri.fromFile(file));
        }
    }
//    public void getAllFromPDFDB(UsersDocument document,File file,  OnCompleteListener listener) throws FileNotFoundException {
//        if (isAuth()){
//            List<String> names = new ArrayList<>();
//            database.child(auth.getUid()).get().addOnCompleteListener(gh->{
//                for (DataSnapshot item :gh.getResult().getChildren()) {
//                    names.add(Objects.requireNonNull(item.getValue(UsersDocument.class)).getPathOfDocument());
//                    storageRef.child(auth.getUid()).getFile((Objects.requireNonNull(item.getValue(UsersDocument.class)).getPathOfDocument()).getBytes();
//                }
//
//
//            });
//
//
//        }
//    }



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

    }

    public void sendMailWithNewPassword(String email, OnCompleteListener<Void> listener) {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.sendPasswordResetEmail(email).addOnCompleteListener(listener);
    }

    public void enterUser(String email, String password, OnCompleteListener<AuthResult> listener) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(listener);
    } public void enterUserGoogle() {
        Intent singInIntent = googleSignInClient.getSignInIntent();
        activity.startActivityForResult(singInIntent,App.RESULT_CODE_SINGIN);

    }

    public void singOut() {
        auth.signOut();
    }


    public FirebaseAuth getAuth() {
        return auth;
    }

}
