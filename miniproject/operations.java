package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import android.database.Cursor;
import android.widget.EditText;
import android.widget.Toast;



public class operations extends AppCompatActivity {

    Button myinsert, myview, myupdate, mydelete, mysearch;
    EditText fname, lname, i, myphone, mybname;
    DataBaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operations);

                i = (EditText) findViewById(R.id.empid);
                fname = (EditText) findViewById(R.id.firstname);
                lname = (EditText) findViewById(R.id.lastname);
                myphone = (EditText) findViewById(R.id.phn);
                mybname = (EditText) findViewById(R.id.branch);

                myinsert = (Button) findViewById(R.id.insert);
                myview = (Button) findViewById(R.id.view);
                myupdate = (Button) findViewById(R.id.update);
                mydelete = (Button) findViewById(R.id.delete);
//                mysearch = (Button) findViewById(R.id.search);

                DB = new DataBaseHelper(this);

                myinsert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String id = i.getText().toString();
                        String first = fname.getText().toString();
                        String last = lname.getText().toString();
                        String p = myphone.getText().toString();
                        String br = mybname.getText().toString();

                        Boolean checkinsertdata = DB.insertuserdata(id,first,last,p,br);
                        if(checkinsertdata)
                            Toast.makeText(operations .this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(operations .this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();

                    }
                });

                myview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Cursor res = DB.getdata();
                        if(res.getCount()==0){
                            Toast.makeText(operations .this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("ID :"+res.getString(0)+"\n");
                            buffer.append("First Name :"+res.getString(1)+"\n");
                            buffer.append("Last Name :"+res.getString(2)+"\n\n");
                            buffer.append("Phone Number :"+res.getString(3)+"\n\n");
                            buffer.append("Branch Name :"+res.getString(4)+"\n\n");

                        }

                        AlertDialog.Builder builder = new AlertDialog.Builder(operations .this);
                        builder.setCancelable(true);
                        builder.setTitle("User Entries");
                        builder.setMessage(buffer.toString());
                        builder.show();
                    }
                });

                myupdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = i.getText().toString();
                        String first = fname.getText().toString();
                        String last = lname.getText().toString();
                        String p= myphone.getText().toString();
                        String br = mybname.getText().toString();

                        Boolean checkupdatedata = DB.updateuserdata(id,first,last,p,br);
                        if(checkupdatedata)
                            Toast.makeText(operations .this, "Entry Updated", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(operations .this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
                    }
                });

                mydelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String id = i.getText().toString();

                        Boolean checkudeletedata = DB.deletedata(id);
                        if(checkudeletedata)

                            Toast.makeText(operations.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(operations .this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();

                    }
                });

//                mysearch.setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(operations.this, searchdata.class);
//                        startActivity(intent);
//
//                    }
//                });

            }
        }


