package com.example.divyansh.yoga;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Custom_Adapter extends ArrayAdapter {
    private int resource;
    private Context context;
    private ArrayList<Yoga_Pojo> arrayList;
    private View view;
    private ListView list;
    private LayoutInflater inflater;

    public Custom_Adapter(@NonNull Context context, int resource, ArrayList<Yoga_Pojo> arrayList) {
        super(context, resource,arrayList);

        Log.d("DEBUG","Custom_Adapter constructor has called");
        this.arrayList = arrayList;
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Log.d("DEBUG","getView is called");
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(resource,null,false);

        CircleImageView circleImageView = (CircleImageView)view.findViewById(R.id.profileimageid);
        TextView name = (TextView)view.findViewById(R.id.listtextid);

        Yoga_Pojo yoga_pojo = arrayList.get(position);

        name.setText(yoga_pojo.getName());

        Glide.with(context).load(yoga_pojo.getImage()).into(circleImageView);

        return view;
    }

}

