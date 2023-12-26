package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;


public class userUpdate extends AppCompatActivity {
    EditText i,fname, lname,phone,br;
    Button update;
    DataBaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update);


                i = (EditText) findViewById(R.id.id);
                fname = (EditText) findViewById(R.id.firstname);
                lname= (EditText) findViewById(R.id.lastname);
                phone= (EditText) findViewById(R.id.phn);
                br = (EditText)  findViewById(R.id.branch);

                update= (Button) findViewById(R.id.upd);

                DB = new DataBaseHelper(this);

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = i.getText().toString();
                        String firstname = fname.getText().toString();
                        String lastname = lname.getText().toString();
                        String phn = phone.getText().toString();
                        String bname = br.getText().toString();

                        System.out.println("S:"+id+" "+firstname+" "+lastname+" "+phn+" "+bname);
                        boolean checkupdatedata = DB.updateuserdata(id,firstname,lastname,phn,bname);
                        if (checkupdatedata) {
                            Toast.makeText(userUpdate.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(userUpdate.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        }



