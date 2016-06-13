package com.example.pc.medproject.views;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import com.example.pc.medproject.R;
import com.example.pc.medproject.StatisticsTask;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 09.06.2016.
 */
public class DiabeticDiagramActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diagram);

        StatisticsTask statistic = new StatisticsTask(getApplicationContext());
        List<Integer> list = statistic.countSavedDiabeticData();

        BarChart barChart = (BarChart) findViewById(R.id.chart);
        barChart.setDescription("POZIOM CURKU WE KRWI W OSTATNIM MIESIĄCU");
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(list.get(0), 0));
        entries.add(new BarEntry(list.get(1), 1));
        entries.add(new BarEntry(list.get(2), 2));

        barChart.fitScreen();
        BarDataSet dataset = new BarDataSet(entries, "poziom cukru we krwi");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("hipoglikemia");
        labels.add("prawidłowy\nwynik");
        labels.add("hiperglikemia");

        BarData data = new BarData(labels, dataset);
        barChart.setData(data);
        barChart.animateY(5000);
        barChart.setBackgroundColor(Color.WHITE);

    }
}