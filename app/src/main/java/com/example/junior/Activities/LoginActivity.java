package com.example.junior.Activities;



import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.junior.Activities.MainActivity;
import com.example.junior.Controllers.AuthController;
import com.example.junior.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AuthController authController = new AuthController();
        binding.enter.setOnClickListener(enter->{
            String email = String.valueOf(binding.email.getText());
            String password = String.valueOf(binding.password.getText());
            if (!email.isEmpty() && !password.isEmpty()) {
                authController.enterUser(email, password, task -> {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(this, MainActivity.class));
                    }else{
                        authController.registerUser(email, password, authResult -> {
                            if (authResult.isSuccessful()) {
                                authController.addUserToDb(email, task1 -> {
                                    startActivity(new Intent(this, MainActivity.class));
                                });
                            } else {
                                Toast.makeText(this, Objects.requireNonNull(authResult.getException()).getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                Log.e("NKJLKO", authResult.getException().getLocalizedMessage());
                            }
                        });
                    }

                });
            } else {
                Toast.makeText(this.getApplicationContext(), "Заполните обязательные поля!", Toast.LENGTH_SHORT).show();
            }

        });

        binding.forgot.setOnClickListener(pass -> {
            if (!binding.email.getText().toString().isEmpty()) {

                authController.sendMailWithNewPassword(binding.email.getText().toString(), task -> {
                    if (task.isSuccessful())
                        Toast.makeText(this, "Письмо успешно отправлено", Toast.LENGTH_LONG).show();
                    else if (task.isCanceled())
                        Toast.makeText(this, "Упс! Кажется, что-то пошло не так...", Toast.LENGTH_LONG).show();
                });
            }else{
                Toast.makeText(this, "Введите почту и попробуйте снова!", Toast.LENGTH_SHORT).show();
            }
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getApplicationContext());
            alertDialog.setTitle("Изменение пароля").setMessage("Введите почту");


//            final EditText input = new EditText(getApplicationContext());
//            input.setLayoutParams(new LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.MATCH_PARENT,
//                    LinearLayout.LayoutParams.MATCH_PARENT));
//            alertDialog.setView(input)
//                    .setPositiveButton("Изменить",
//                            (dialog, which) -> {
//                                authController.sendMailWithNewPassword(input.getText().toString(), task -> {
//                                    if (task.isSuccessful())
//                                        Toast.makeText(App.getInstance().getApplicationContext(), "Письмо успешно отправлено", Toast.LENGTH_LONG).show();
//                                    else if (task.isCanceled())
//                                        Toast.makeText(App.getInstance().getApplicationContext(), "Упс! Кажется, что-то пошло не так...", Toast.LENGTH_LONG).show();
//                                });
//                                dialog.dismiss();
//                            })
//                    .setNegativeButton("Закрыть",
//                            (dialog, which) -> dialog.cancel())
//                    .show();
//
//
        });


    }
}