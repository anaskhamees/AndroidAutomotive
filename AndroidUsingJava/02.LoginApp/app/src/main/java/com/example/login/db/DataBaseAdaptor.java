package com.example.login.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.login.Message;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseAdaptor {

    static DataBaseHelper dbHelper;
    private boolean englishFlag = true;
    public DataBaseAdaptor(Context _context){
        dbHelper = new DataBaseHelper(_context);

    }



    public long insertMessage(Message m){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.PHONE, m.getPhone());
        contentValues.put(DataBaseHelper.MESSAGE, m.getMessage());
        long id = db.insert(DataBaseHelper.LAB_TABLE_NAME, null, contentValues);
        return id;
    }


    public Message getMessageByPhone(String phone){
        Message message = new Message();
        Cursor c;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {DataBaseHelper.MESSAGE,};
        //query (String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
        c = db.query(DataBaseHelper.LAB_TABLE_NAME, columns, DataBaseHelper.PHONE+" = ?", new String[]{phone}, null, null, null);

        if ( c.moveToNext()) {
            message.setMessage(c.getString(0));
            message.setPhone(phone);
        }
        return message;
    }



    static class DataBaseHelper extends SQLiteOpenHelper{
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "Lab2_db.db";

        private static final String LAB_TABLE_NAME = "Lab2";
        private static final String UID = "_id";
        private static final String PHONE = "phone";
        private static final String  MESSAGE= "message";


        private static final String CREATE_LAB_TABLE = "CREATE TABLE " + LAB_TABLE_NAME + " (" +UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                PHONE+" TEXT, "+ MESSAGE + " TEXT);";



        public DataBaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL(CREATE_LAB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub

        }
    }


}
