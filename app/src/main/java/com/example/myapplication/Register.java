package com.example.myapplication;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.model.Details;
import com.example.myapplication.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    TextInputEditText editTextEmail,editTextPassword,editConfirmPassword;
    Button btnRegister;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView loginNow;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editConfirmPassword = findViewById(R.id.confirmPassword);
        btnRegister = findViewById(R.id.btn_register);
        progressBar = findViewById(R.id.progressBar);
        loginNow = findViewById(R.id.clickToLogin);
        loginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email,password,confirmPassword;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());
                confirmPassword = String.valueOf(editConfirmPassword.getText());
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this,"Không được để trống Email",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Register.this,"Không được để trống Mật khẩu",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if(TextUtils.isEmpty(confirmPassword)){
                    Toast.makeText(Register.this,"Không được để trống Mật khẩu",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                System.out.println(password);
                System.out.println(confirmPassword);
                if(!password.equals(confirmPassword)){
                    Toast.makeText(Register.this,"Hai lần nhập mật khẩu không giống nhau!",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }


                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Random rand = new Random();
                                int rand_int1 = rand.nextInt(1000000);
                                User user = new User(rand_int1,"Default",editTextEmail.getText().toString(),"Defallt","0000");
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference("User");
                                final Handler handler = new Handler(Looper.getMainLooper());
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        myRef.child(String.valueOf(rand_int1)).setValue(user, new DatabaseReference.CompletionListener() {
                                            @Override
                                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

                                            }
                                        });
                                        //Details details = new Details(UserId,FoodId,food.getName(),food.getPrice(),count,food.getImage());
                                    }
                                },1000);
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(Register.this,"Đăng ký thành công!",Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Register.this, "Đăng ký thất bại!",
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });
    }
}