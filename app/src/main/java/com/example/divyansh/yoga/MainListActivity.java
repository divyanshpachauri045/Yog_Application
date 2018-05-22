package com.example.divyansh.yoga;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainListActivity extends AppCompatActivity {

    private ArrayList<Yoga_Pojo> arrayList = new ArrayList<>();
    private ListView listView;
    private String URL = "http://test.netparam.in/FortiTest/services/getCat";
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private Custom_Adapter adapter;
    private Intent intent;
    private Toolbar toolbar;
    private TextView notes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        toolbar = findViewById(R.id.app_barid);
        notes = findViewById(R.id.notestextid);

        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainListActivity.this,notesactivity.class);
                startActivity(intent);
            }
        });

        intent = getIntent();
        final String imageData = intent.getStringExtra("imageclickdata");

        Log.d("DEBUG",imageData);

        requestQueue = Volley.newRequestQueue(this);
        stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("item");

                    Log.d("DEBUG",String.valueOf(jsonArray.length()));


                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String image = jsonObject1.getString("img");
                        String name = jsonObject1.getString("category");
                        String description = jsonObject1.getString("description");

                        Yoga_Pojo yoga_pojo = new Yoga_Pojo();
                        yoga_pojo.setImage(image);
                        yoga_pojo.setName(name);
                        yoga_pojo.setDescription(description);

                        arrayList.add(yoga_pojo);

                    }
                    Log.d("DEBUG","OUT of Response");

                    listView = findViewById(R.id.listid);
                    adapter = new Custom_Adapter(MainListActivity.this,R.layout.custom_list,arrayList);
                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            Yoga_Pojo yoga_pojo = arrayList.get(i);
                            String webData = yoga_pojo.getDescription();

                            Intent webintent = new Intent(MainListActivity.this,WebViewActivity.class);
                            webintent.putExtra("WebData",webData);
                            startActivity(webintent);
                        }
                    });

                }catch (Exception e){
                    Toast.makeText(MainListActivity.this,"Network Error",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainListActivity.this,"NetWork Error",Toast.LENGTH_SHORT).show();
            }
        }){
            // send category to load json data .....

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Log.d("DEBUG","Data is sending to server");

                Map<String, String> params = new HashMap<>();
                params.put("cat",imageData);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}
