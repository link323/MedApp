package com.example.pc.medproject;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 09.06.2016.
 */
public class StatisticsTask {
    Context ctx;
    List<Integer> listOfCountedElements;
    int counterHipoglicemia = 0, counterCorrect = 0, counterHiperglicemia = 0;
    //na czczo
    private int lowerMarginCorrectBF = 70, upperMarginCorrectBF = 100, upperMarginUncorrectBF = 125;
    //120 min po
    private int lowerMarginCorrectAF = 70, upperMarginCorrectAF = 139, upperMarginUncorrectAF = 199;

    private int optymalne = 120, prawidlowe =130, wysokiePrawidlowe = 139;
    private int nadcisnienie1stopnia = 159, nadcisnienie2stopnia = 179;
    public StatisticsTask(Context ctx){
        this.ctx = ctx;
    }

    public List<Integer> countSavedDiabeticData() {
        DataBaseAdapter db = new DataBaseAdapter(ctx);
        db = db.open();
        List<ResultsDataDiabetic> lista = db.getAllDiabeticResults();

        for(int i=0; i<lista.size(); i++) {
            String date = lista.get(i).getDate();
            int result = Integer.valueOf(lista.get(i).getResult());
            boolean beforeFood = Boolean.valueOf(lista.get(i).getBeforeFood());

            if(beforeFood && result<lowerMarginCorrectBF){//<70
                counterHipoglicemia++;
            }
            else if(beforeFood && result>lowerMarginCorrectBF && result<upperMarginCorrectBF){//>70 <100
                counterCorrect++;
            }
            else if(beforeFood && result>upperMarginCorrectBF){//>100
                counterHiperglicemia++;
            }

            if(!beforeFood && result<lowerMarginCorrectAF){//<70
                counterHipoglicemia++;
            }
            else if(!beforeFood && result>lowerMarginCorrectAF && result<upperMarginCorrectAF){//>70 <140
                counterCorrect++;
            }
            else if(!beforeFood && result>upperMarginCorrectAF){//>140
                counterHiperglicemia++;
            }
        }

        listOfCountedElements = new ArrayList<>();
        listOfCountedElements.add(counterHipoglicemia);
        listOfCountedElements.add(counterCorrect);
        listOfCountedElements.add(counterHiperglicemia);

        Log.d("statystyki ",counterHipoglicemia+" "+counterCorrect+" "+counterHiperglicemia);
        return listOfCountedElements;
    }
    public List<Integer> countSavedPressureData() {
        DataBaseAdapter db = new DataBaseAdapter(ctx);
        db = db.open();
        List<ResultsDataBloodPressure> lista = db.getAllBloodPressureResults();

        for(int i=0; i<lista.size(); i++) {
            String date = lista.get(i).getDate();
            int result1 = Integer.valueOf(lista.get(i).getResult1());
            int result2 = Integer.valueOf(lista.get(i).getResult2());


        }

        listOfCountedElements = new ArrayList<>();
        listOfCountedElements.add(counterHipoglicemia);
        listOfCountedElements.add(counterCorrect);
        listOfCountedElements.add(counterHiperglicemia);

        Log.d("statystyki ",counterHipoglicemia+" "+counterCorrect+" "+counterHiperglicemia);
        return listOfCountedElements;
    }
}
