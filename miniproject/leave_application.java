package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class leave_application extends AppCompatActivity {

    EditText i,fname, lname,phone,br, myfrmdate, mytodate;
    Button leave;
    DataBaseHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_application);

                i = (EditText) findViewById(R.id.id);
                fname = findViewById(R.id.firstname);
                lname= findViewById(R.id.lastname);
                phone= findViewById(R.id.phn);
                br = findViewById(R.id.branch);
               myfrmdate = findViewById(R.id.frmdate);
               mytodate = findViewById(R.id.todate);

                leave=findViewById(R.id.eleave);

                leave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = i.getText().toString();
                        String firstname = fname.getText().toString();
                        String lastname = lname.getText().toString();
                        String phn = phone.getText().toString();
                        String bname =br.getText().toString();
                        String frmdt = myfrmdate.getText().toString();
                        String todt = mytodate.getText().toString();

                        Boolean checkleavedata = DB.empleavedata(id, firstname, lastname, phn, bname, frmdt, todt);
                        if (checkleavedata == true)
                            Toast.makeText(leave_application.this, "Application Sent", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(leave_application.this, "Enter Valid credentials", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }




