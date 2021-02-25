package com.example.assign2_multiplescreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;

import java.util.ArrayList;
import java.util.List;

public class RadarChart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar_chart);

        //graph code

        float[] xVals = getIntent().getExtras().getFloatArray("x values");
        float[] yVals = getIntent().getExtras().getFloatArray("y values");


        com.github.mikephil.charting.charts.RadarChart radarChart = findViewById(R.id.chart);

        List<RadarEntry> entries = new ArrayList<>();
        for (int i=0; i<xVals.length; i++) {
            // turn your data into Entry objects
            entries.add(new RadarEntry(yVals[i]));
            System.out.println(xVals[i] +","+yVals[i]);
        }

        RadarDataSet set = new RadarDataSet(entries, "Data");
        set.setColor(Color.rgb(255,0,0));

        set.setValueTextSize(16f);

        RadarData data = new RadarData(set);
        radarChart.setData(data);

        radarChart.setWebColor(Color.rgb(255,0,0));
        radarChart.setWebAlpha(255);
        radarChart.setWebColorInner(Color.rgb(0,0,255));

        radarChart.setDrawWeb(true);
        radarChart.getDescription().setEnabled(false);
        radarChart.invalidate(); // refresh


        //intent for screen to left - PieChart
        Intent pieChartIntent = new Intent(this, PieChart.class);

        //intent for screen to right, DataEntry (i.e. back to start)
        Intent dataEntryIntent = new Intent(this, DataEntry.class);
        //this line lets us open the existing activity (i.e. values will persist when we return to it)
        dataEntryIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);//found this at https://stackoverflow.com/questions/18049284/how-to-go-to-an-already-existing-activity-from-a-different-one/18049394

        pieChartIntent.putExtra("x values", xVals);
        pieChartIntent.putExtra("y values", yVals);

        findViewById(android.R.id.content).getRootView().setOnTouchListener(new SwipeListener(this) {
            @Override
            public void onSwipeLeft() {
                //System.out.println("swiped left");
                startActivity(dataEntryIntent);
            }

            @Override
            public void onSwipeRight() {
                //System.out.println("swiped right");
                startActivity(pieChartIntent);
            }
        });

    }
}