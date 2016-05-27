package com.example.pc.medproject.views;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.pc.medproject.DataBaseAdapter;
import com.example.pc.medproject.R;
import com.example.pc.medproject.ResultsData;

import java.util.ArrayList;
import java.util.List;

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
        View view = inflater.inflate(R.layout.result_fragment, container, false);
        TableLayout tableLayout = (TableLayout) view.findViewById(R.id.header_table);
        addHeaders(tableLayout);
        addData(tableLayout);
        return view;
    }

    public void addHeaders(TableLayout tableLayout){
        TableRow tr_head = new TableRow(getContext());
        tr_head.setBackgroundColor(Color.GRAY);
        tr_head.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        TextView label_date = new TextView(getContext());
        label_date.setText("DATA");
        label_date.setTextColor(Color.WHITE);
        label_date.setPadding(5, 5, 5, 5);
        label_date.setGravity(View.TEXT_ALIGNMENT_CENTER);
        tr_head.addView(label_date);

        TextView label_result = new TextView(getContext());
        label_result.setText("WYNIK");
        label_result.setTextColor(Color.WHITE);
        label_result.setPadding(5, 5, 5, 5);
        label_result.setGravity(View.TEXT_ALIGNMENT_CENTER);
        tr_head.addView(label_result);

        TextView label_before = new TextView(getContext());
        label_before.setText("PRZED\nPOSIŁKIEM");
        label_before.setTextColor(Color.WHITE);
        label_before.setPadding(5, 5, 5, 5);
        label_before.setGravity(View.TEXT_ALIGNMENT_CENTER);
        tr_head.addView(label_before);

        tableLayout.addView(tr_head, new TableLayout.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
    }

    public void addData(TableLayout tableLayout){
        Integer count=0;
        DataBaseAdapter db = new DataBaseAdapter(getContext());
        db = db.open();
        List<ResultsData> lista = db.getAllContacts();

        for(int i=0; i<lista.size(); i++){
            String date = lista.get(0).getDate();
            String result = String.valueOf(lista.get(0).getResult());
            String state = String.valueOf(lista.get(0).getBeforeFood());
//        while (cursor.moveToNext()) {
//            String date = formatdate(cursor.getString(2));// get the first variable
//            Double weight_kg = roundTwoDecimals(cursor.getDouble(4));// get the second variable
// Create the table row
            TableRow tr = new TableRow(getContext());
            if(i%2!=0) tr.setBackgroundColor(Color.GRAY);
//            tr.setId(100+count);
            tr.setLayoutParams(new TableLayout.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

//Create two columns to add as table data
            // Create a TextView to add date
            TextView labelDATE = new TextView(getContext());
            labelDATE.setId(200 + count);
            labelDATE.setText(date);
            labelDATE.setPadding(2, 0, 5, 0);
            labelDATE.setTextColor(Color.RED);
            tr.addView(labelDATE);

            TextView labelRESULTS = new TextView(getContext());
            labelRESULTS.setText(result);
            labelRESULTS.setId(200 + count);
            labelRESULTS.setTextColor(Color.RED);
            tr.addView(labelRESULTS);

            TextView labelBEFORE = new TextView(getContext());
            labelBEFORE.setText(state);
            labelBEFORE.setTextColor(Color.RED);
            labelBEFORE.setId(200 + count);
            tr.addView(labelBEFORE);

// finally add this to the table row
            tableLayout.addView(tr, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            count++;
        }
    }
}
