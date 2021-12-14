package com.example.projet.Maths_Activities;

import java.util.ArrayList;
import java.util.List;

public class TableDeMultiplication {

    List multiplications = new ArrayList<Calcul>();


    public TableDeMultiplication(int table) {
        initTable(table);
    }

    private void initTable(int table) {
        for (int i = 1; i <= 10 ; i++) {
            multiplications.add(new Calcul(i, table,"x"));
        }

    }

    public List<Calcul> getMultiplications(){
        return this.multiplications;
    }

}
