package com.example.junior;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseController {

    private final FirebaseAuth auth;

    FirebaseFirestore firebase;
    DatabaseReference database;

    public FirebaseController() {
        this.auth = FirebaseAuth.getInstance();
        this.database = FirebaseDatabase.getInstance("https://junior-e9e5e-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
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


}
