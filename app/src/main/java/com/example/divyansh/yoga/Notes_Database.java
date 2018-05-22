package com.example.divyansh.yoga;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import java.util.ArrayList;

public class Notes_Database {

    private ArrayList<String> arrayList = new ArrayList<>();
    NoteData noteData;
    private Context context;

    public Notes_Database(Context context){
        noteData = new NoteData(context);
        this.context = context;
    }

    public long insertData(String data){

        SQLiteDatabase db = noteData.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(noteData.ADDDATA,data);
        long id = db.insert(noteData.TABLE_NAME,null,contentValues);

        return id;
    }

    public ArrayList<String> getAllData(){

        SQLiteDatabase db = noteData.getWritableDatabase();
        //select id, name, password from student
        String[] columns = {NoteData.UID, NoteData.ADDDATA};
        Cursor cursor = db.query(NoteData.TABLE_NAME,columns,null,null,null,null,null);

        StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext()){

            int id  = cursor.getInt(0);
            String name = cursor.getString(1);
            arrayList.add(name);
        }

        return arrayList;
    }



    static class NoteData extends SQLiteOpenHelper{


        private static final String DATABASE_NAME="MyDatabase";
        private static final String TABLE_NAME="yog_data";
        private static final int DATABASE_VERSION=1;
        private static final String UID="_id";
        private static final String ADDDATA="AddData";
        private static final String CREATE_QUERY ="CREATE TABLE "+TABLE_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ADDDATA+" VARCHAR(255));";
        private static final String DROP_QUERY="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;


        public NoteData(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            Log.d("DEBUG","NoteData Constructor has called");
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            try {
                Log.d("DEBUG", "OnCreate of NoteData has called");
                sqLiteDatabase.execSQL(CREATE_QUERY);
            }catch (Exception e)
            {
                Toast.makeText(context,"Error in onCreate of NoteData",Toast.LENGTH_SHORT).show();

            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            try{

                Log.d("DEBUG","OnUpgrade of NoteData has called");
                sqLiteDatabase.execSQL(DROP_QUERY);
                onCreate(sqLiteDatabase);

            }catch (Exception e){
                Toast.makeText(context,"Error in onUpgrade of NoteData",Toast.LENGTH_SHORT).show();

            }
        }
    }
}
