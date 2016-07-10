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
    DateTime dateTime = new DateTime();

    private int counterHipoglicemia = 0, counterCorrect = 0, counterHiperglicemia = 0;
    private int counterOptymalne1 = 0, counterPrawidlowe1 = 0, counterWysokiePrawidlowe1 = 0;
    private int counterNadcisnienie1st1 = 0, counterNadcisnienie2st1 = 0,  counterNadcisnienie3st1 = 0;
    private int counterOptymalne2 = 0, counterPrawidlowe2 = 0, counterWysokiePrawidlowe2 = 0;
    private int counterNadcisnienie1st2 = 0, counterNadcisnienie2st2 = 0,  counterNadcisnienie3st2 = 0;
    //na czczo
    private int lowerMarginCorrectBF = 70, upperMarginCorrectBF = 100, upperMarginUncorrectBF = 125;
    //120 min po
    private int lowerMarginCorrectAF = 70, upperMarginCorrectAF = 139, upperMarginUncorrectAF = 199;

    //skurczowe
    private int optymalne1 = 120, prawidlowe1 = 130, wysokiePrawidlowe1 = 139;
    private int nadcisnienie1stopnia1 = 159, nadcisnienie2stopnia1 = 179;
    //rozkurczowe
    private int optymalne2 = 80, prawidlowe2 = 85, wysokiePrawidlowe2 = 89;
    private int nadcisnienie1stopnia2 = 99, nadcisnienie2stopnia2 = 109;

    public StatisticsTask(Context ctx){
        this.ctx = ctx;
    }

    public List<Integer> countSavedDiabeticData() {
        DataBaseAdapter db = new DataBaseAdapter(ctx);
        db = db.open();
        List<ResultsDataDiabetic> lista = db.getAllDiabeticResults(dateTime.findLastMonthDate(), dateTime.findActualDate());

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
        List<ResultsDataBloodPressure> lista = db.getAllBloodPressureResults(dateTime.findLastMonthDate(), dateTime.findActualDate());

        for(int i=0; i<lista.size(); i++) {
            String date = lista.get(i).getDate();
            int result1 = Integer.valueOf(lista.get(i).getResult1());
            int result2 = Integer.valueOf(lista.get(i).getResult2());

            if(result1<optymalne1){
                counterOptymalne1++;
            }
            else if(result1<prawidlowe1){
                counterPrawidlowe1++;
            }
            else if(result1<wysokiePrawidlowe1){
                counterWysokiePrawidlowe1++;
            }
            else if(result1<nadcisnienie1stopnia1){
                counterNadcisnienie1st1++;
            }
            else if(result1<nadcisnienie2stopnia1){
                counterNadcisnienie2st1++;
            }
            else {
                counterNadcisnienie3st1++;
            }

            if(result2<optymalne2){
                counterOptymalne2++;
            }
            else if(result2<prawidlowe2){
                counterPrawidlowe2++;
            }
            else if(result2<wysokiePrawidlowe2){
                counterWysokiePrawidlowe2++;
            }
            else if(result2<nadcisnienie1stopnia2){
                counterNadcisnienie1st2++;
            }
            else if(result2<nadcisnienie2stopnia2){
                counterNadcisnienie2st2++;
            }
            else {
                counterNadcisnienie3st2++;
            }
        }
        listOfCountedElements = new ArrayList<>();
        listOfCountedElements.add(counterOptymalne1);
        listOfCountedElements.add(counterPrawidlowe1);
        listOfCountedElements.add(counterWysokiePrawidlowe1);
        listOfCountedElements.add(counterNadcisnienie1st1);
        listOfCountedElements.add(counterNadcisnienie2st1);
        listOfCountedElements.add(counterNadcisnienie3st1);
        listOfCountedElements.add(counterOptymalne2);
        listOfCountedElements.add(counterPrawidlowe2);
        listOfCountedElements.add(counterWysokiePrawidlowe2);
        listOfCountedElements.add(counterNadcisnienie1st2);
        listOfCountedElements.add(counterNadcisnienie2st2);
        listOfCountedElements.add(counterNadcisnienie3st2);

        return listOfCountedElements;
    }
}
