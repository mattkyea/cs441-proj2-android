package com.example.assign2_multiplescreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class BarGraph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_graph);

        //graph code
        // in this example, a LineChart is initialized from xml
        BarChart chart = (BarChart) findViewById(R.id.chart);

        //YourData[] dataObjects = ...;
        Integer [] dataObjects = {1, 2, 3, 4};

        List<BarEntry> entries = new ArrayList<>();
        int curr = 0;
        for (Integer data : dataObjects) {
            // turn your data into Entry objects
            entries.add(new BarEntry(curr, data));
            curr++;
        }

        BarDataSet dataSet = new BarDataSet(entries, "Label"); // add entries to dataset
        dataSet.setColor(0xFF000000);
        dataSet.setValueTextColor(0xFF000000);

        BarData lineData = new BarData(dataSet);
        chart.setData(lineData);
        chart.invalidate(); // refresh


        //intent for screen to left - LineGraph
        Intent lineGraphIntent = new Intent(this, LineGraph.class);
        lineGraphIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);//again, nifty trick to restore old page, not make a new one

        //intent for screen to right, PieChart
        Intent pieChartIntent = new Intent(this, PieChart.class);
        pieChartIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        findViewById(android.R.id.content).getRootView().setOnTouchListener(new SwipeListener(this) {
            @Override
            public void onSwipeLeft() {
                System.out.println("swiped left");
                startActivity(pieChartIntent);
            }

            @Override
            public void onSwipeRight() {
                System.out.println("swiped right");
                startActivity(lineGraphIntent);
            }
        });

    }
}