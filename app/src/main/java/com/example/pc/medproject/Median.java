package com.example.pc.medproject;

import android.util.Log;

import java.util.Arrays;
import java.util.List;

/**
 * Created by PC on 10.07.2016.
 */
public class Median {

    public String countMedianOfPressure(List<ResultsDataBloodPressure> list){
        int[] result1 = new int[list.size()];
        int[] result2 = new int[list.size()];
        int medianResult1, medianResult2;

        for(int i=0; i<list.size(); i++){
            result1[i] = list.get(i).getResult1();
            result2[i] = list.get(i).getResult2();
        }
        Arrays.sort(result1);
        Arrays.sort(result2);

        medianResult1 = median(result1);
        medianResult2 = median(result2);
        Log.d("mediana ", medianResult1 + " " + medianResult2);
        return ""+medianResult1+"/"+medianResult2;
    }

    public String countMedianOfDiabebetic(List<ResultsDataDiabetic> list){
        int[] result = new int[list.size()];
        int medianResult;

        for(int i=0; i<list.size(); i++){
            result[i] = list.get(i).getResult();
        }
        Arrays.sort(result);

        medianResult = median(result);
        Log.d("mediana ", ""+medianResult);
        return ""+medianResult;
    }

    public int median(int[] table){
        int middle1 = table.length/2;
        if (table.length%2 == 1) {
            return table[middle1];
        } else {
            return (table[middle1-1] + table[middle1]) / 2;
        }
    }
}
