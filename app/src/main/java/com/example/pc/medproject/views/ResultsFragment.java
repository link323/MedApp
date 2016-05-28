package com.example.pc.medproject.views;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.pc.medproject.R;
import com.example.pc.medproject.ResultsBloodPressureTableHelper;
import com.example.pc.medproject.ResultsDataBloodPressure;
import com.example.pc.medproject.ResultsDiabeticTableHelper;

/**
 * Created by PC on 25.04.2016.
 */
public class ResultsFragment extends Fragment{
    public ResultsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.result_fragment, container, false);
        final TableLayout tableLayout = (TableLayout) view.findViewById(R.id.header_table);

        RadioGroup group = (RadioGroup) view.findViewById(R.id.radioGroup);
        group.clearCheck();

        final RadioButton buttonDiabetic = (RadioButton) view.findViewById(R.id.radioButtonDiabetic);
        RadioButton buttonPressure = (RadioButton) view.findViewById(R.id.radioButtonBlood);

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if(null!=rb && checkedId > -1){
                    if(checkedId == R.id.radioButtonDiabetic) {
                        tableLayout.removeAllViews();
                        Toast.makeText(getContext(), "cukrzyca!!!!!", Toast.LENGTH_SHORT).show();
                        ResultsDiabeticTableHelper helper = new ResultsDiabeticTableHelper(tableLayout, getContext());
                        helper.addHeaders();
                        helper.addData();
                    }
                    else if(checkedId == R.id.radioButtonBlood){
                        tableLayout.removeAllViews();
                        Toast.makeText(getContext(),"ciśnienie!!!!!", Toast.LENGTH_SHORT).show();
                        ResultsBloodPressureTableHelper helper = new ResultsBloodPressureTableHelper(tableLayout, getContext());
                        helper.addHeaders();
                        helper.addData();
                    }
                }

            }
        });

        return view;
    }
}
