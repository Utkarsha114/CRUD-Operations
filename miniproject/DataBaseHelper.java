package com.example.miniproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

public class DataBaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "employee.db";

        public DataBaseHelper(Context context){

            super(context,DATABASE_NAME,null,1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create Table users(id TEXT primary key, password TEXT)");
            db.execSQL("create Table Userdetails(id TEXT primary key, fname TEXT, lname TEXT, p String, br String)");
            db.execSQL("create Table empleave(id TEXT primary key, fname TEXT, lname TEXT, branch TEXT, purpose TEXT, fromdt TEXT, todt TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db,  int  i, int i1) {
            db.execSQL("drop Table if exists users");
            db.execSQL("drop Table if exists Userdetails");
            db.execSQL("drop Table if exists empleave");
        }

        public boolean insertData(String email,String pass){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv  = new ContentValues();

            cv.put("id",email);
            cv.put("password",pass);

            long result = db.insert("users",null,cv);

            if(result==-1){
                return false;
            }
            return  true;
        }

        public Boolean checkusername(String email) {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("Select * from users where id = ?", new String[]{email});
            if (cursor.getCount() > 0)
                return true;
            else
                return false;
        }

        public Cursor checkusernamepassword(String email, String password){
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("Select * from users where id = ? and password = ?", new String[] {email,password});
            return cursor;
        }

        public Boolean insertuserdata(String id, String first, String last, String p, String br)
        {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put("id", id);
            contentValues.put("fname", first);
            contentValues.put("lname",last);
            contentValues.put("p",p);
            contentValues.put("br",br);

            long result= db.insert("Userdetails", null, contentValues);
            if(result==-1){
                return false;
            }
            else{
                return true;
            }
        }

        public Boolean updateuserdata(String id, String firstname, String lastname, String phn, String bname)
        {
            SQLiteDatabase DB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", id);
            contentValues.put("fname", firstname);
            contentValues.put("lname",lastname);
            contentValues.put("p",phn);
            contentValues.put("br",bname);

            System.out.println(id+" "+firstname+" "+lastname+" "+phn+" "+bname);
                long result = DB.update("Userdetails", contentValues, "id=?", new String[]{id});
                if (result == -1) {
                    return false;
                }
            return true;
        }

        public Boolean deletedata (String id)
        {
            SQLiteDatabase DB = this.getWritableDatabase();
            Cursor cursor = DB.rawQuery("Select * from Userdetails where id = ?", new String[]{id});
            if (cursor.getCount() > 0) {
                long result = DB.delete("Userdetails", "id=?", new String[]{id});
                if (result == -1) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        }

        public Cursor getdata ()
        {
            SQLiteDatabase DB = this.getWritableDatabase();
            Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
            return cursor;
        }

        public Boolean empleavedata(String id, String firstname, String lastname, String phn, String bname, String from, String to) {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put("id", id);
            contentValues.put("fname", firstname);
            contentValues.put("lname",lastname);
            contentValues.put("phone",phn);
            contentValues.put("br",bname);
            contentValues.put("frm",from);
            contentValues.put("to",to);

            long result= db.insert("empleave", null, contentValues);
            if(result==-1){
                return false;
            }
            else{
                return true;
            }

        }

//    public Cursor getWordMatches(String query, String[] columns) {
//        String selection = COL_WORD + " MATCH ?";
//        String[] selectionArgs = new String[] {query+"*"};
//
//        return query(selection, selectionArgs, columns);
//    }
//
//    private Cursor query(String selection, String[] selectionArgs, String[] columns) {
//        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
//        builder.setTables(FTS_VIRTUAL_TABLE);
//
//        Cursor cursor = builder.query(DataBaseHelper.getReadableDatabase(),
//                columns, selection, selectionArgs, null, null, null);
//
//        if (cursor == null) {
//            return null;
//        } else if (!cursor.moveToFirst()) {
//            cursor.close();
//            return null;
//        }
//        return cursor;
//    }

        public Cursor search(String query) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor;
            cursor = db.rawQuery("select * from Userdetails where id=?",new String[]{query});
            return cursor;
        }
    }




//        public Boolean searchdata(String id) {
//
//            public List<Contact> search(String keyword) {
//                List<Contact> contacts = null;
//                try {
//                    SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//                    Cursor cursor = sqLiteDatabase.rawQuery("select * from " + contactTable + " where " + nameColumn + " like ?", new String[] { "%" + keyword + "%" });
//                    if (cursor.moveToFirst()) {
//                        contacts = new ArrayList<Contact>();
//                        do {
//                            Contact contact = new Contact();
//                            contact.setId(cursor.getInt(0));
//                            contact.setName(cursor.getString(1));
//                            contact.setPhone(cursor.getString(2));
//                            contact.setAddress(cursor.getString(3));
//                            contact.setEmail(cursor.getString(4));
//                            contact.setDescription(cursor.getString(5));
//                            contacts.add(contact);
//                        } while (cursor.moveToNext());
//                    }
//                } catch (Exception e) {
//                    contacts = null;
//                }
//                return contacts;
//            }

//
//        }
//}


