package com.example.pc.medproject;

import android.util.Log;

import java.util.Arrays;
import java.util.List;

/**
 * Created by PC on 10.07.2016.
 */
public class Max {
    public int countMaxOfDiabetic(List<ResultsDataDiabetic> listDiabetic) {
        int[] result = new int[listDiabetic.size()];

        for(int i=0; i<listDiabetic.size(); i++){
            result[i] = listDiabetic.get(i).getResult();
        }
        Arrays.sort(result);
        Log.d("max ", "");
        return result[listDiabetic.size()-1];
    }

    public int countMaxOfPressure(List<ResultsDataBloodPressure> listPressure) {
        int[] result1 = new int[listPressure.size()];
        int[] result2 = new int[listPressure.size()];
        int medianResult1, medianResult2;

        for(int i=0; i<listPressure.size(); i++){
            result1[i] = listPressure.get(i).getResult1();
            result2[i] = listPressure.get(i).getResult2();
        }
        Arrays.sort(result1);
        Arrays.sort(result2);
        return 0;
    }
}
