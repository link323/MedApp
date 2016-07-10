package com.example.pc.medproject.views;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.medproject.DataBaseAdapter;
import com.example.pc.medproject.DateTime;
import com.example.pc.medproject.Max;
import com.example.pc.medproject.Mean;
import com.example.pc.medproject.Median;
import com.example.pc.medproject.Min;
import com.example.pc.medproject.R;
import com.example.pc.medproject.ResultsDataBloodPressure;
import com.example.pc.medproject.ResultsDataDiabetic;

import java.util.List;

/**
 * Created by PC on 29.06.2016.
 */
public class StatisticFragment extends Fragment{
    public StatisticFragment() {
    }

    TextView averageDiabeticResult, medianOfDiabeticResult, maxOfDiabeticResult, minOfDiabeticResult;
    TextView averagePressureResult, medianOfPressureResult, maxOfPressureResult, minOfPressureResult;
    ImageButton buttonDiabeticDiagram, buttonPressureDiagram;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.statistic_fragment, container, false);

        Median median = new Median();
        Mean mean = new Mean();
        Max max = new Max();
        Min min = new Min();

        DataBaseAdapter db = new DataBaseAdapter(getContext());
        db = db.open();
        DateTime datetime = new DateTime();

        List<ResultsDataDiabetic> listDiabetic =
                db.getAllDiabeticResults(datetime.findLastMonthDate(), datetime.findActualDate());
        List<ResultsDataBloodPressure> listPressure =
                db.getAllBloodPressureResults(datetime.findLastMonthDate(), datetime.findActualDate());

        buttonDiabeticDiagram = (ImageButton) view.findViewById(R.id.buttonDiagramDiabetic);
        buttonDiabeticDiagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "test", Toast.LENGTH_SHORT).show();
            }
        });

        buttonPressureDiagram = (ImageButton) view.findViewById(R.id.buttonDiagramPressure);
        buttonPressureDiagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "test", Toast.LENGTH_SHORT).show();
            }
        });

        medianOfDiabeticResult = (TextView) view.findViewById(R.id.medianaWynik1);
        averageDiabeticResult = (TextView) view.findViewById(R.id.sredniaWynik1);
        maxOfDiabeticResult = (TextView) view.findViewById(R.id.maxWynik1);
        minOfDiabeticResult = (TextView) view.findViewById(R.id.minWynik1);

        medianOfDiabeticResult.setText(median.countMedianOfDiabebetic(listDiabetic));
        averageDiabeticResult.setText(mean.countMeanOfDiabetic(listDiabetic));
        maxOfDiabeticResult.setText(max.countMaxOfDiabetic(listDiabetic));
        minOfDiabeticResult.setText(min.countMinOfDiabetic(listDiabetic));

        medianOfPressureResult = (TextView) view.findViewById(R.id.medianaWynik2);
        averagePressureResult = (TextView) view.findViewById(R.id.sredniaWynik2);
        maxOfPressureResult = (TextView) view.findViewById(R.id.maxWynik2);
        minOfPressureResult = (TextView) view.findViewById(R.id.minWynik2);

        medianOfPressureResult.setText(median.countMedianOfPressure(listPressure));
        averagePressureResult.setText(mean.countMeanOfPressure(listPressure));
        maxOfPressureResult.setText(max.countMaxOfPressure(listPressure));
        minOfPressureResult.setText(min.countMinOfPressure(listPressure));
        return view;
    }
}
