package com.example.enesakbulut.fitness;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;


public class Tab2 extends Fragment {

    ImageView imageView;
    TextView textView;
    int workoutid;
    int progress;

    ImageView[] imageViews;
    TextView[] textViews;
    LinearLayout linearLayout1;
    LinearLayout linearLayout2;
    LinearLayout linearLayout3;
    WorkoutData workoutData = new WorkoutData();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);

        linearLayout1 = (LinearLayout) view.findViewById(R.id.linearLayoutTab1);
        linearLayout2 = (LinearLayout) view.findViewById(R.id.linearLayoutTab2);
        linearLayout3 = (LinearLayout) view.findViewById(R.id.linearLayoutTab3);


        SharedPreferences workoutidShared = this.getActivity().getSharedPreferences("workoutid", 0);
        workoutid = workoutidShared.getInt("workoutid", 0);

        SharedPreferences sharedProgresslist = this.getActivity().getSharedPreferences("sharedProgresslist", 0);
        progress = sharedProgresslist.getInt(String.valueOf(workoutid), 0);


        initializeProgressDots();
        return view;
    }

    public void initializeProgressDots(){
        imageViews = new ImageView[15];
        textViews = new TextView[15];
        for (int i = 0; i<15; i++){
            imageViews[i] = new ImageView(this.getActivity());
            imageViews[i].setTag(i);
            imageViews[i].setLayoutParams(new ViewGroup.LayoutParams(100, 100));
            imageViews[i].setImageResource(R.drawable.blackcircle);

            textViews[i] = new TextView(this.getActivity());
            textViews[i].setTag(i);
            textViews[i].setLayoutParams(new ViewGroup.LayoutParams(100, 100));
            textViews[i].setText(String.valueOf(i+1));



            if(i<5){
                View view = LayoutInflater.from(this.getActivity()).inflate(R.layout.progress_buttons, linearLayout1, false);

                imageView = (ImageView) view.findViewById(R.id.imageViewRound);
                textView = (TextView) view.findViewById(R.id.textViewRound);

                imageView.setImageResource(R.drawable.blackcircle);
                if (i<= progress && progress>0) {
                    imageView.setAlpha(0.5f);
                }

                textView.setTextSize(22);
                textView.setText(String.valueOf(i+1));


                linearLayout1.addView(view);
                //linearLayout1.addView(imageViews[i]);


            }else if(i<10){

                View view = LayoutInflater.from(this.getActivity()).inflate(R.layout.progress_buttons, linearLayout2, false);

                imageView = (ImageView) view.findViewById(R.id.imageViewRound);
                textView = (TextView) view.findViewById(R.id.textViewRound);


                imageView.setImageResource(R.drawable.blackcircle);

                if (i<= progress) {
                    imageView.setAlpha(0.5f);
                }

                textView.setTextSize(22);
                textView.setText(String.valueOf(i+1));


                linearLayout2.addView(view);
                //linearLayout2.addView(imageViews[i]);


            }else if(i<15){

                View view = LayoutInflater.from(this.getActivity()).inflate(R.layout.progress_buttons, linearLayout3, false);

                imageView = (ImageView) view.findViewById(R.id.imageViewRound);
                textView = (TextView) view.findViewById(R.id.textViewRound);

                imageView.setImageResource(R.drawable.blackcircle);

                if (i<= progress) {
                    imageView.setAlpha(0.5f);
                }

                textView.setTextSize(22);
                textView.setText(String.valueOf(i+1));


                linearLayout3.addView(view);

                //linearLayout3.addView(imageViews[i]);
            }
        }
    }
}
