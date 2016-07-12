package com.example.pc.medproject;

import java.util.Arrays;
import java.util.List;

/**
 * Created by PC on 10.07.2016.
 */
public class Mean {
    public String countMeanOfDiabetic(List<ResultsDataDiabetic> list) {
        int[] result = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i).getResult();
        }
        Arrays.sort(result);
        String meanResult = String.valueOf(mean(result));

        return meanResult;
    }

    public String countMeanOfPressure(List<ResultsDataBloodPressure> list) {
        int[] result1 = new int[list.size()];
        int[] result2 = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            result1[i] = list.get(i).getResult1();
            result2[i] = list.get(i).getResult2();
        }
        Arrays.sort(result1);
        Arrays.sort(result2);
        String meanResult1 = String.valueOf(mean(result1));
        String meanResult2 = String.valueOf(mean(result2));

        return meanResult1+"/"+meanResult2;
    }

    public static int mean(int[] m) {
        int sum = 0;
        for (int i = 0; i < m.length; i++) {
            sum += m[i];
        }
        return sum / m.length;
    }

}
