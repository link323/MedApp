package com.example.pc.medproject;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

/**
 * Created by PC on 28.05.2016.
 */
public class ResultsBloodPressureTableHelper implements ResultsTable{

    TableLayout tableLayout;
    Context context;

    public ResultsBloodPressureTableHelper(TableLayout layout, Context _context){
        tableLayout = layout;
        context = _context;
    }

    public void addHeaders(){
        TableRow tr_head = new TableRow(context);
        tr_head.setBackgroundColor(Color.rgb(26,31,177));
        tr_head.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        TextView label_date = new TextView(context);
        label_date.setText("DATA");
        label_date.setTextColor(Color.WHITE);
        label_date.setPadding(5, 5, 5, 5);
        label_date.setGravity(View.TEXT_ALIGNMENT_CENTER);
        tr_head.addView(label_date);

        TextView label_result = new TextView(context);
        label_result.setText("SKURCZOWE");
        label_result.setTextColor(Color.WHITE);
        label_result.setPadding(5, 5, 5, 5);
        label_result.setGravity(View.TEXT_ALIGNMENT_CENTER);
        tr_head.addView(label_result);

        TextView label_before = new TextView(context);
        label_before.setText("ROZKURCZOWE");
        label_before.setTextColor(Color.WHITE);
        label_before.setPadding(5, 5, 5, 5);
        label_before.setGravity(View.TEXT_ALIGNMENT_CENTER);
        tr_head.addView(label_before);

        tableLayout.addView(tr_head, new TableLayout.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
    }

    public void addData(){
        Integer count=0;
        DataBaseAdapter db = new DataBaseAdapter(context);
        db = db.open();
        DateTime datetime = new DateTime();

        List<ResultsDataBloodPressure> lista = db.getAllBloodPressureResults(datetime.findLastMonthDate(), datetime.findActualDate());

        for(int i=0; i<lista.size(); i++){
            String date = lista.get(i).getDate();
            String result1 = String.valueOf(lista.get(i).getResult1());
            String result2 = String.valueOf(lista.get(i).getResult2());

            TableRow tr = new TableRow(context);
            if(i%2!=0){
                tr.setBackgroundColor(Color.rgb(0,96,250));
            }else {
                tr.setBackgroundColor(Color.rgb(16,112,224));

            }
            tr.setLayoutParams(new TableLayout.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            TextView labelDATE = new TextView(context);
            labelDATE.setId(200 + count);
            labelDATE.setText(date);
            labelDATE.setPadding(2, 0, 5, 0);
            labelDATE.setTextColor(Color.WHITE);
            tr.addView(labelDATE);

            TextView labelRESULTS = new TextView(context);
            labelRESULTS.setText(result1);
            labelRESULTS.setId(200 + count);
            tr.addView(labelRESULTS);

            TextView labelBEFORE = new TextView(context);
            labelBEFORE.setText(result2);
            if(Integer.parseInt(result1)<140 || Integer.parseInt(result2)<90){
                labelBEFORE.setTextColor(Color.GREEN);
                labelRESULTS.setTextColor(Color.GREEN);
            }else {
                labelBEFORE.setTextColor(Color.RED);
                labelRESULTS.setTextColor(Color.RED);
            }
            labelBEFORE.setId(200 + count);
            tr.addView(labelBEFORE);

            tableLayout.addView(tr, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            count++;
        }
        db.close();
    }
}
