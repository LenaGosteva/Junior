package com.example.junior.Activities;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ThemedSpinnerAdapter;

import com.example.junior.R;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.junior.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private FirebaseAuth mAuth;
    GoogleSignInClient googleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        GoogleSignInOptions gso = new
//                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken("220168828586-7e58dqb3ppnvep31i9tfn8d1aqerp5qb.apps.googleusercontent.com")
//                .requestEmail()
//                .build();
//
//        // Build a GoogleSignInClient with the options specified by gso.
//         googleSignInClient = GoogleSignIn.getClient(this, gso);
//        mAuth = controller.getAuth();
//
//        if (mAuth.getCurrentUser()!=null){
//        findViewById(R.id.singButton).setOnClickListener(fg->{
//            Intent singInIntent = googleSignInClient.getSignInIntent();
//            startActivityForResult(singInIntent,RESULT_CODE_SINGIN);
//        });}else{
//            new AlertDialog.Builder(this)
//                    .setMessage("Для корректной работы приложения требуется вход с помощью гугл." +
//                            " Вы можете отказаться от этого, но тогда Ваши данные будут сохранены только на этом устройстве")
//                    .setPositiveButton("Войти", (dialogInterface, i) -> {
//
//                        Intent singInIntent = googleSignInClient.getSignInIntent();
//                        startActivityForResult(singInIntent,RESULT_CODE_SINGIN);
//                    })
//                    .setNegativeButton("Отказаться", (dialogInterface, i) -> {
//                        dialogInterface.dismiss();
//                    }).create().show();
//        }
        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_profile, R.id.nav_files, R.id.nav_settings)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == RESULT_CODE_SINGIN) {        //just to verify the code
//            //create a Task object and use GoogleSignInAccount from Intent and write a separate method to handle singIn Result.
//
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            handleSignInResult(task);
//        }
//    }
//    private void handleSignInResult(Task<GoogleSignInAccount> task) {
//
//        //we use try catch block because of Exception.
//        try {
//            GoogleSignInAccount account = task.getResult(ApiException.class);
//            Toast.makeText(MainActivity.this,"Signed In successfully",Toast.LENGTH_LONG).show();
//            //SignIn successful now show authentication
//            firebaseGoogleAuth(account);
//
//        } catch (ApiException e) {
//            e.printStackTrace();
//            Toast.makeText(MainActivity.this,"SignIn Failed!!!",Toast.LENGTH_LONG).show();
//            firebaseGoogleAuth(null);
//        }
//    }
//    private void firebaseGoogleAuth(GoogleSignInAccount account) {
//        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
//        //here we are checking the Authentication Credential and checking the task is successful or not and display the message
//        //based on that.
//        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()){
//                    Toast.makeText(MainActivity.this,"successful",Toast.LENGTH_LONG).show();
//                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
//                    updateUI(firebaseUser);
//                }
//                else {
//                    Toast.makeText(MainActivity.this,"Failed!",Toast.LENGTH_LONG).show();
//                    updateUI(null);
//                }
//            }
//        });
//    }
//    private void updateUI(FirebaseUser fUser) {
//
//        //getLastSignedInAccount returned the account
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
//        if (account !=null){
//            String personName = account.getDisplayName();
//            String personGivenName = account.getGivenName();
//            String personEmail = account.getEmail();
//            String personId = account.getId();
//
//            Toast.makeText(MainActivity.this,personName + "  " + personEmail,Toast.LENGTH_LONG).show();
//        }
//    }

}