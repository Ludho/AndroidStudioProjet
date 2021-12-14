package com.example.projet.Maths_Activities;

public class Calcul {

    private int operande1;
    private int operande2;
    private String operation;
    public Calcul(int operande1, int operande2, String operation) {
        this.operande1 = operande1;
        this.operande2 = operande2;
        this.operation = operation;
    }

    public String getOperation(){ return operation; }

    public int getOperande1(){
        return this.operande1;
    }

    public int getOperande2(){
        return this.operande2;
    }

}
