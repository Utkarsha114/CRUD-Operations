package com.example.miniproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText myemail, mypassword;
    Button mylogin;
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myemail = (EditText) findViewById(R.id.email);
        mypassword = (EditText) findViewById(R.id.password);
        mylogin = (Button) findViewById(R.id.login);
        db = new DataBaseHelper(this);


        mylogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = myemail.getText().toString();
                String pass = mypassword.getText().toString();
                Cursor c = db.checkusernamepassword(user, pass);

                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(login.this, "Enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else if(c.getCount()!=0){
                    c.moveToNext();
                    String userName = c.getString(0);
                    String userPassword = c.getString(1);

                    if (userName.equals("") && userPassword.equals("")){
                        Toast.makeText(login.this, "Login not successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(login.this, login.class);
                        startActivity(intent);
                    } else if (userName.equals("utkarsha@gmail.com") && userPassword.equals("123")) {
                        Toast.makeText(login.this, "Login successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(login.this, operations.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(login.this, "Login successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(login.this, userUpdate.class);
                        startActivity(intent);
                    }

                }
                Toast.makeText(login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
