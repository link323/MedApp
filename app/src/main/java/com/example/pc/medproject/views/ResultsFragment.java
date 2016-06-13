package com.example.pc.medproject.views;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;

import com.example.pc.medproject.R;
import com.example.pc.medproject.ResultsBloodPressureTableHelper;
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

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if(null!=rb && checkedId > -1){
                    if(checkedId == R.id.radioButtonDiabetic) {
                        tableLayout.removeAllViews();
                        ResultsDiabeticTableHelper helper = new ResultsDiabeticTableHelper(tableLayout, getContext());
                        helper.addHeaders();
                        helper.addData();
                    }
                    else if(checkedId == R.id.radioButtonBlood){
                        tableLayout.removeAllViews();
                        ResultsBloodPressureTableHelper helper = new ResultsBloodPressureTableHelper(tableLayout, getContext());
                        helper.addHeaders();
                        helper.addData();
                    }
                    else if(checkedId == R.id.radioButtonDiagramBlood){
                        tableLayout.removeAllViews();
                        Intent intentDiagram=new Intent(view.getContext(),PressureDiagramActivity.class);
                        startActivity(intentDiagram);
                        group.clearCheck();
                    }
                    else if(checkedId == R.id.radioButtonDiagramDiabetic){
                        tableLayout.removeAllViews();
                        Intent intentDiagram=new Intent(view.getContext(),DiabeticDiagramActivity.class);
                        startActivity(intentDiagram);
                        group.clearCheck();
                    }
                }

            }
        });

        return view;
    }
}
