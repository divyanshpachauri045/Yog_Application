package com.example.divyansh.yoga;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView yoga;
    private ImageView exercise;
    private Toolbar toolbar;
    private TextView notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.app_barid);
        notes = findViewById(R.id.notestextid);


        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,notesactivity.class);
                startActivity(intent);
            }
        });

        yoga = findViewById(R.id.mainyogaid);
        exercise = findViewById(R.id.mainexerciseid);

        yoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,MainListActivity.class);
                i.putExtra("imageclickdata","yoga");
                startActivity(i);

            }
        });
        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this,MainListActivity.class);
                i.putExtra("imageclickdata","exercise");
                startActivity(i);
            }});
        }

}
