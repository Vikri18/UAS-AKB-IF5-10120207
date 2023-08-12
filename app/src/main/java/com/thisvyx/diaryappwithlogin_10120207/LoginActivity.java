package com.thisvyx.diaryappwithlogin_10120207;
/*
NIM : 10120207
Nama : Vikri Frediansyah
Kelas : IF 5
 */


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText etEmailLogin, etPasswordLogin;
    Button btnLogin ;
    TextView btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmailLogin = findViewById(R.id.et_email_login);
        etPasswordLogin = findViewById(R.id.et_password_login);

        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(v -> loginUser());

        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent =new Intent(LoginActivity.this ,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loginUser() {
        String email = etEmailLogin.getText().toString();
        String password = etPasswordLogin.getText().toString();

        boolean isValidated = validateDataLogin(email,password);

        if(!isValidated){
            return;
        }

        loginUserInFirebase(email,password);
    }

    private void loginUserInFirebase(String email, String password) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    if (firebaseAuth.getCurrentUser().isEmailVerified()){
                        Intent intent;
                        intent =new Intent(LoginActivity.this ,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        //email not verified
                        Toast.makeText(LoginActivity.this,"Email belum diverifikasi, silahkan verifikasi email terlebih dahulu",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    //login failed
                    Toast.makeText(LoginActivity.this,"Email dan Password Salah atau Akun belum dibuat",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    boolean validateDataLogin(String email, String password){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmailLogin.setError("Masukan Email Dengan Benar");
            return false;
        }
        if(password.length()<6){
            etPasswordLogin.setError("Password tidak boleh kurang dari 6 huruf");
            return false;
        }
        return true;
    }
}