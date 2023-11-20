package com.example.junior.Controllers;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;

public class AuthController {
    private final FirebaseAuth auth;

    FirebaseFirestore firebase;
    DatabaseReference database;

    public AuthController() {
        this.auth = FirebaseAuth.getInstance();
        this.database = FirebaseDatabase.getInstance("https://junior-e9e5e-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
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



    public void getAllDaysFromDb(OnCompleteListener<DataSnapshot> listener) {
        if (isAuth())
            database.child("users")
                    .child(getUser().getUid())
                    .child("days")
                    .get().addOnCompleteListener(listener);
    }
    public void getAllSubjectsFromDb(OnCompleteListener<DataSnapshot> listener) {
        if (isAuth())
            database.child("users")
                    .child(getUser().getUid())
                    .child("subjects")
                    .get().addOnCompleteListener(listener);
    }
    public void getAllTasksFromSubject(String subject, OnCompleteListener<DataSnapshot> listener) {
        if (isAuth()) {
            database.child("users")
                    .child(getUser().getUid())
                    .child("subjects")
                    .child(subject)
                    .child("tasks").get().addOnCompleteListener(listener);
        }
    }
    public void addUserToDb(String name,OnCompleteListener listener) {
        if (isAuth()) {
            database.child("users").child(getUser().getUid()).setValue(getUser().getUid()).addOnCompleteListener(listener);
            database.child("users").child(getUser().getUid()).child("name").setValue(name).addOnCompleteListener(listener);

        }
    }
    public void getName(OnCompleteListener<DataSnapshot> listener){
        if (isAuth())
        database.child("users").child(getUser().getUid()).child("name").get().addOnCompleteListener(listener);
    }
    public void getDayFromDB(String date,OnCompleteListener<DataSnapshot> listener) {
        if (isAuth())
            database.child("users").child(getUser().getUid()).child("days").child(date).get().addOnCompleteListener(listener);
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
