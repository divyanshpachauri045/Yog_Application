package com.example.divyansh.yoga;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class notesactivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    private Notes_Database notes_database;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ListView l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notesactivity);

        notes_database = new Notes_Database(this);
        l = findViewById(R.id.notelistid);
        arrayList = notes_database.getAllData();
        ArrayAdapter<String> adapter = new ArrayAdapter(notesactivity.this,android.R.layout.simple_list_item_1,arrayList);
        l.setAdapter(adapter);

        floatingActionButton = findViewById(R.id.floatingbuttonid);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater inflater = getLayoutInflater();
                View v = inflater.inflate(R.layout.custom_dialog_box,null);
                AlertDialog.Builder alertbox = new AlertDialog.Builder(notesactivity.this);
                final EditText editText = v.findViewById(R.id.dialogtext);
                Button b = v.findViewById(R.id.addtext);
                alertbox.setView(v);
                alertbox.show();

                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String s = editText.getText().toString();
                        long id = notes_database.insertData(s);

                        Intent i = new Intent(notesactivity.this,notesactivity.class);
                        startActivity(i);

                        if(id<0){
                            Toast.makeText(notesactivity.this,"Unsuccess",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(notesactivity.this,"Success",Toast.LENGTH_SHORT).show();
                        }

                    }});
                }
        });
    }
}
