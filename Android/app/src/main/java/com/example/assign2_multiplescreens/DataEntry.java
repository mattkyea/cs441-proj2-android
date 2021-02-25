package com.example.assign2_multiplescreens;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

//aka the MainActivity. I've defined swiping left as moving your finger from right to left, and right as vice versa.
//however, the act of swiping left feels as if we're moving right (like moving the current page to the left), thus some confusion on my part and/or I might have named these wrong
//so, our pages go: DataEntry <- RadarChart <- PieChart <- BarGraph <- LineGraph <- DataEntry
public class DataEntry extends AppCompatActivity {

    float[] xVals = new float[5];
    float[] yVals = new float[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        //intent for screen to left - RadarChart
        Intent radarChartIntent = new Intent(this, RadarChart.class);

        //repeat for screen to right - LineGraph
        Intent lineGraphIntent = new Intent(this, LineGraph.class);


        //code to grab values, start passing around

        //make new SwipeListener to override what to do on left and right at this screen
        findViewById(android.R.id.content).getRootView().setOnTouchListener(new SwipeListener(this) {
            @Override
            public void onSwipeLeft() {
                //print val in x1 and go to new screen
                //.out.println("swiped left");
                readValues();
                lineGraphIntent.putExtra("x values", xVals);
                lineGraphIntent.putExtra("y values", yVals);
                startActivity(lineGraphIntent);//there's a version with a "Bundle" - can we use to pass values?

            }

            @Override
            public void onSwipeRight() {
                //System.out.println("swiped right");
                readValues();
                radarChartIntent.putExtra("x values", xVals);
                radarChartIntent.putExtra("y values", yVals);
                startActivity(radarChartIntent);
            }
        });
    }

    public void readValues(){
        TextView x1 = (TextView) findViewById(R.id.x1);
        TextView x2 = (TextView) findViewById(R.id.x2);
        TextView x3 = (TextView) findViewById(R.id.x3);
        TextView x4 = (TextView) findViewById(R.id.x4);
        TextView x5 = (TextView) findViewById(R.id.x5);

        TextView y1 = (TextView) findViewById(R.id.y1);
        TextView y2 = (TextView) findViewById(R.id.y2);
        TextView y3 = (TextView) findViewById(R.id.y3);
        TextView y4 = (TextView) findViewById(R.id.y4);
        TextView y5 = (TextView) findViewById(R.id.y5);

        xVals[0] = Float.parseFloat(x1.getText().toString());
        xVals[1] = Float.parseFloat(x2.getText().toString());
        xVals[2] = Float.parseFloat(x3.getText().toString());
        xVals[3] = Float.parseFloat(x4.getText().toString());
        xVals[4] = Float.parseFloat(x5.getText().toString());

        yVals[0] = Float.parseFloat(y1.getText().toString());
        yVals[1] = Float.parseFloat(y2.getText().toString());
        yVals[2] = Float.parseFloat(y3.getText().toString());
        yVals[3] = Float.parseFloat(y4.getText().toString());
        yVals[4] = Float.parseFloat(y5.getText().toString());
    }



    }




