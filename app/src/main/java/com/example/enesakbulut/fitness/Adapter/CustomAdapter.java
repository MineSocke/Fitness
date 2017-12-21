package com.example.enesakbulut.fitness.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.enesakbulut.fitness.ListData;
import com.example.enesakbulut.fitness.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Enes on 14.12.2017.
 */

public class CustomAdapter extends BaseAdapter{

    Context con;
    ArrayList<ListData> data = new ArrayList<>();
    LayoutInflater layoutInflater;

    public CustomAdapter(Context context, ArrayList dataList){
        this.con = context;
        this.data = dataList;
        layoutInflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        //return null;
        View row = layoutInflater.inflate(R.layout.list_item, null, true);
        TextView tv1 = (TextView) row.findViewById(R.id.tv1);
        TextView tv2 = (TextView) row.findViewById(R.id.tv2);

        tv1.setText(data.get(position).getName());
        tv2.setText(data.get(position).getNumber());

        return row;
    }
}
