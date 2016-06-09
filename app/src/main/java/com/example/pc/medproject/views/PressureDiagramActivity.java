package com.example.pc.medproject.views;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pc.medproject.DataBaseAdapter;
import com.example.pc.medproject.R;
import com.example.pc.medproject.ResultsDataBloodPressure;
import com.example.pc.medproject.StatisticsTask;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 09.06.2016.
 */
public class PressureDiagramActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diagram);

        StatisticsTask statistic = new StatisticsTask(getApplicationContext());
        List<Integer> list = statistic.countSavedData();

        BarChart barChart = (BarChart) findViewById(R.id.chart);
        barChart.setDescription("POZIOM CURKU WE KRWI W OSTATNIM MIESIĄCU");
        // HorizontalBarChart barChart= (HorizontalBarChart) findViewById(R.id.chart);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(list.get(0), 0));
        entries.add(new BarEntry(list.get(1), 1));
        entries.add(new BarEntry(list.get(2), 2));

        //entries.add(new BarEntry(6f, 2));
        BarDataSet dataset = new BarDataSet(entries, "poziom cukru we krwi");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("hipoglikemia");
        labels.add("prawidłowy");
        labels.add("hiperglikemia");

        BarData data = new BarData(labels, dataset);
        barChart.setData(data);
        barChart.animateY(5000);
        barChart.setBackgroundColor(Color.WHITE);

    }
}