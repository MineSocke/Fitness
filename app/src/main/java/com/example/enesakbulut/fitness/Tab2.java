package com.example.enesakbulut.fitness;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class Tab2 extends Fragment {

    ImageView[] imageViews;
    LinearLayout linearLayout1;
    LinearLayout linearLayout2;
    LinearLayout linearLayout3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);

        linearLayout1 = (LinearLayout) view.findViewById(R.id.linearLayoutTab1);
        linearLayout2 = (LinearLayout) view.findViewById(R.id.linearLayoutTab2);
        linearLayout3 = (LinearLayout) view.findViewById(R.id.linearLayoutTab3);

        initializeProgressDots();
        return view;
    }

    public void initializeProgressDots(){
        imageViews = new ImageView[15];
        for (int i = 0; i<15; i++){
            imageViews[i] = new ImageView(this.getActivity());
            imageViews[i].setTag(i);
            imageViews[i].setLayoutParams(new ViewGroup.LayoutParams(100, 100));
            imageViews[i].setImageResource(R.drawable.blackcircle);
            if(i<5){
                linearLayout1.addView(imageViews[i]);
            }else if(i<10){
                linearLayout2.addView(imageViews[i]);
            }else if(i<15){
                linearLayout3.addView(imageViews[i]);
            }
        }
    }
}
