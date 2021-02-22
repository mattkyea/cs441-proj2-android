package com.example.assign2_multiplescreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.MotionEventCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Intent act_action = new Intent(this, MainActivity2.class);
        //startActivity(act_action);

        Intent act_action = new Intent(this, MainActivity2.class);
        startActivity(act_action);
        TextView x1 = (TextView) findViewById(R.id.x1);
        System.out.println("hi");
        System.out.println(x1.getText().toString());

        //mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        SwipeListener sl = new SwipeListener(this);
        findViewById(android.R.id.content).getRootView().setOnTouchListener(sl);

        /*
        findViewById(android.R.id.content).getRootView().setOnTouchListener(new SwipeListener(this) {
            @Override
            public void onSwipeLeft() {
                System.out.println("swiped left");
                System.out.println(x1.getText().toString());


            }

            @Override
            public void onSwipeRight() {
                System.out.println("swiped right");
            }
        });
        */

    }


/*
    private GestureDetectorCompat mDetector;


    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d(DEBUG_TAG,"onDown: " + event.toString());
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
            float xDistance = event2.getX() - event1.getX();
            if(xDistance < 0){
                System.out.println("swiped right");
            }else if (xDistance > 0) {
                System.out.println("swiped left");
            }
            return true;
        }
*/

    }




