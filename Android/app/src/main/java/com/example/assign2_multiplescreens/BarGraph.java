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


        float[] xVals = getIntent().getExtras().getFloatArray("x values");
        float[] yVals = getIntent().getExtras().getFloatArray("y values");
        //graph code
        // in this example, a LineChart is initialized from xml
        BarChart chart = (BarChart) findViewById(R.id.chart);

        List<BarEntry> entries = new ArrayList<>();
        for (int i=0; i<xVals.length; i++) {
            // turn your data into Entry objects
            entries.add(new BarEntry(xVals[i], yVals[i]));
        }

        BarDataSet dataSet = new BarDataSet(entries, "Data"); // add entries to dataset
        dataSet.setColor(0xFF000000);
        dataSet.setValueTextColor(0xFF000000);
        dataSet.setValueTextSize(16f);

        BarData lineData = new BarData(dataSet);
        lineData.setValueTextSize(16f);
        chart.setData(lineData);
        chart.getDescription().setEnabled(false);
        chart.invalidate(); // refresh


        //intent for screen to left - LineGraph
        Intent lineGraphIntent = new Intent(this, LineGraph.class);
        lineGraphIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);//again, nifty trick to restore old page, not make a new one
        //we don't want to restore page for the graphs though, so that we redraw in case of data change

        //intent for screen to right, PieChart
        Intent pieChartIntent = new Intent(this, PieChart.class);

        //send values to both
        //we need to still send to line graph because its possible to come FROM pie chart/other direction
        lineGraphIntent.putExtra("x values", xVals);
        lineGraphIntent.putExtra("y values", yVals);

        pieChartIntent.putExtra("x values", xVals);
        pieChartIntent.putExtra("y values", yVals);

        findViewById(android.R.id.content).getRootView().setOnTouchListener(new SwipeListener(this) {
            @Override
            public void onSwipeLeft() {
                //System.out.println("swiped left");
                startActivity(pieChartIntent);
            }

            @Override
            public void onSwipeRight() {
                //System.out.println("swiped right");
                startActivity(lineGraphIntent);
            }
        });

    }
}