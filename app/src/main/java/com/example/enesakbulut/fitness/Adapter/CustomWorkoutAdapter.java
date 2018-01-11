package com.example.enesakbulut.fitness.Adapter;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.enesakbulut.fitness.ListData;
import com.example.enesakbulut.fitness.R;
import com.example.enesakbulut.fitness.WorkoutDataList;

import java.util.ArrayList;

/**
 * Created by Enes on 09.01.2018.
 */

public class CustomWorkoutAdapter extends BaseAdapter{
    Context con;
    ArrayList<WorkoutDataList> data = new ArrayList<>();
    LayoutInflater layoutInflater;

    public CustomWorkoutAdapter(Context context, ArrayList dataList){
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
        View row = layoutInflater.inflate(R.layout.listworkouts_item, null, true);
        ImageView iv4 = (ImageView) row.findViewById(R.id.iv4);
        TextView tv3 = (TextView) row.findViewById(R.id.tv3);

        Log.e("WTF: ", "I get launched!");

        iv4.setImageResource(data.get(position).getWorkoutListMap());
        tv3.setText(Integer.toString(data.get(position).getProgress()));
        Log.e("HAX", data.get(position).getWorkoutListMap() + "");
        return row;
    }


}
