package com.example.assign2_multiplescreens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // in this example, a LineChart is initialized from xml
        LineChart chart = (LineChart) findViewById(R.id.chart);

        //YourData[] dataObjects = ...;
        Integer [] dataObjects = {1, 2, 3, 4};

        List<Entry> entries = new ArrayList<>();
        int curr = 0;
        for (Integer data : dataObjects) {
            // turn your data into Entry objects
            entries.add(new Entry(curr, data));
            curr++;
        }

        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
        dataSet.setColor(0xFF000000);
        dataSet.setValueTextColor(0xFF000000);

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate(); // refresh
    }
}