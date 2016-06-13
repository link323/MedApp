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
        List<Integer> list = statistic.countSavedPressureData();

        BarChart barChart = (BarChart) findViewById(R.id.chart);
        barChart.setDescription("POZIOM CIŚNIENIA KRWI W OSTATNIM MIESIĄCU");
        // HorizontalBarChart barChart= (HorizontalBarChart) findViewById(R.id.chart);
        ArrayList<BarEntry> entriesFirstGroup = new ArrayList<>();
        entriesFirstGroup.add(new BarEntry(list.get(0), 0));
        entriesFirstGroup.add(new BarEntry(list.get(1), 1));
        entriesFirstGroup.add(new BarEntry(list.get(2), 2));
        entriesFirstGroup.add(new BarEntry(list.get(3), 0));
        entriesFirstGroup.add(new BarEntry(list.get(4), 1));
        entriesFirstGroup.add(new BarEntry(list.get(5), 2));

        ArrayList<BarEntry> entriesSecondGroup = new ArrayList<>();
        entriesSecondGroup.add(new BarEntry(list.get(6), 0));
        entriesSecondGroup.add(new BarEntry(list.get(7), 1));
        entriesSecondGroup.add(new BarEntry(list.get(8), 2));
        entriesSecondGroup.add(new BarEntry(list.get(9), 0));
        entriesSecondGroup.add(new BarEntry(list.get(10), 1));
        entriesSecondGroup.add(new BarEntry(list.get(11), 2));

        BarDataSet dataset1 = new BarDataSet(entriesFirstGroup, "ciśnienie skurczowe");
        dataset1.setColors(ColorTemplate.COLORFUL_COLORS);

        BarDataSet dataset2 = new BarDataSet(entriesFirstGroup, "ciśnienie rozkurczowe");
        dataset2.setColors(ColorTemplate.COLORFUL_COLORS);

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("optymalne");
        labels.add("prawidłowe");
        labels.add("wysokie\nprawidłowe");
        labels.add("nadciśnienie\nłagodne");
        labels.add("nadciśnienie\numiarkowane");
        labels.add("nadciśnienie\nciężkie");

        ArrayList<BarDataSet> dataset = new ArrayList<>();
        dataset.add(dataset1);
        dataset.add(dataset2);

        //entries.add(new BarEntry(6f, 2));
        BarData data = new BarData(labels, dataset);
        barChart.setData(data);
        barChart.animateY(5000);
        barChart.setBackgroundColor(Color.WHITE);
        barChart.setVisibleXRangeMaximum(6);
        barChart.setVisibleXRange(6,6);
    }
}