package com.example.assign2_multiplescreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

/*
 * Second screen, mostly a test for now
 */
public class LineGraph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_graph);

        float[] xVals = getIntent().getExtras().getFloatArray("x values");
        float[] yVals = getIntent().getExtras().getFloatArray("y values");

        // in this example, a LineChart is initialized from xml
        LineChart chart = (LineChart) findViewById(R.id.chart);

        List<Entry> entries = new ArrayList<>();
        for (int i=0; i<xVals.length; i++) {
            // turn your data into Entry objects
            entries.add(new Entry(xVals[i], yVals[i]));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Data"); // add entries to dataset
        dataSet.setColor(0xFF000000);
        dataSet.setValueTextColor(0xFF000000);

        dataSet.setValueTextSize(16f);

        LineData lineData = new LineData(dataSet);

        chart.setData(lineData);
        chart.getDescription().setEnabled(false);
        chart.invalidate(); // refresh

        //intent for screen to left - DataEntry
        Intent dataEntryIntent = new Intent(this, DataEntry.class);
        dataEntryIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);//again, nifty trick to restore old page, not make a new one

        //intent for screen to right, BarGraph
        Intent barGraphIntent = new Intent(this, BarGraph.class);
        barGraphIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        //send values to bar graph
        //dataentry doesn't need
        barGraphIntent.putExtra("x values", xVals);
        barGraphIntent.putExtra("y values", yVals);

        findViewById(android.R.id.content).getRootView().setOnTouchListener(new SwipeListener(this) {
            @Override
            public void onSwipeLeft() {
                //System.out.println("swiped left");
                startActivity(barGraphIntent);
            }

            @Override
            public void onSwipeRight() {
                //System.out.println("swiped right");
                startActivity(dataEntryIntent);
            }
        });


    }

}