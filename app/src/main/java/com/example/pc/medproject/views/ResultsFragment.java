package com.example.pc.medproject.views;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.pc.medproject.R;

import java.util.List;

/**
 * Created by PC on 25.04.2016.
 */
public class ResultsFragment extends Fragment{
    public ResultsFragment() {
        // Required empty public constructor
    }

    TableLayout tl;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.result_fragment, container, false);
        TableLayout tl = (TableLayout) view.findViewById(R.id.header_table);
        addHeaders();
        addData();
        return view;
    }
    public void setText(String string){
        TextView view = (TextView) getView().findViewById(R.id.textView);
        view.setText("blabla");
    }

    public void addHeaders(){
//        TableRow tr_head = new TableRow(this);
//        tr_head.setId(10);
//        tr_head.setBackgroundColor(Color.GRAY);
//        tr_head.setLayoutParams(new TableLayout.LayoutParams(
//                TableRow.LayoutParams.FILL_PARENT,
//                LayoutParams.WRAP_CONTENT));
    }

    public void addData(){

    }
}
