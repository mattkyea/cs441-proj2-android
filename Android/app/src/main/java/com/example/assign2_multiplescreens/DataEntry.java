package com.example.assign2_multiplescreens;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

//aka the MainActivity. I've defined swiping left as moving your finger from right to left, and right as vice versa.
//however, the act of swiping left feels as if we're moving right (like moving the current page to the left), thus some confusion on my part and/or I might have named these wrong
//so, our pages go: DataEntry <- RadarChart <- PieChart <- BarGraph <- LineGraph <- DataEntry
public class DataEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        //intent for screen to left - RadarChart
        Intent radarChartIntent = new Intent(this, RadarChart.class);
        //this line lets us open the existing activity (i.e. values will persist when we return to it)
        radarChartIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);//found this at https://stackoverflow.com/questions/18049284/how-to-go-to-an-already-existing-activity-from-a-different-one/18049394

        //repeat for screen to right - LineGraph
        Intent lineGraphIntent = new Intent(this, LineGraph.class);
        lineGraphIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        //code to grab values, start passing around

        TextView x1 = (TextView) findViewById(R.id.x1);
        System.out.println("hi");
        System.out.println(x1.getText().toString());

        //make new SwipeListener to override what to do on left and right at this screen
        findViewById(android.R.id.content).getRootView().setOnTouchListener(new SwipeListener(this) {
            @Override
            public void onSwipeLeft() {
                //print val in x1 and go to new screen
                System.out.println("swiped left");
                System.out.println(x1.getText().toString());
                startActivity(lineGraphIntent);//there's a version with a "Bundle" - can we use to pass values?

            }

            @Override
            public void onSwipeRight() {
                System.out.println("swiped right");
                startActivity(radarChartIntent);
            }
        });


    }



    }




