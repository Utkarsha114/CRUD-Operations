package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText username, password, repassword;
    Button mylogin, myregister;
    DataBaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


                DB = new DataBaseHelper(this);

                mylogin = (Button) findViewById(R.id.login);
                myregister = (Button) findViewById(R.id.register);

                username = (EditText) findViewById(R.id.lemail);
                password = (EditText) findViewById(R.id.lpassword);
                repassword = (EditText) findViewById(R.id.lrepassword);

                mylogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String user = username.getText().toString();
                        String pass = password.getText().toString();
                        String repass = repassword.getText().toString();

                        if(user.equals("")||pass.equals("")||repass.equals(""))
                            Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                        else{
                            if(pass.equals(repass)){
                                Boolean checkuser = DB.checkusername(user);
                                if(!checkuser){
                                    Boolean insert = DB.insertData(user, pass);

                                    if(insert){
                                        Toast.makeText(MainActivity.this, "Registered successfully.\nPlease Login to continue", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(MainActivity.this, "Registration failed.\nTry again", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else{
                                    Toast.makeText(MainActivity.this, "User already exists! please Login", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(MainActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                            }
                        } }
                });


                myregister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), login.class);
                        startActivity(intent);
                    }
                });
            }
        }

