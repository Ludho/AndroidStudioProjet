package com.example.projet.Maths_Activities.module3;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projet.Maths_Activities.Calcul;
import com.example.projet.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class maths_module3_calcul extends Activity {

    // diffuculté selectionné
    public static final String DIFFICULTY_KEY = "difficulty_key";
    private String difficulte;

    // calculs et réponses
    private ArrayList<Calcul> calculs = new ArrayList<Calcul>();
    private ArrayList<Integer> reponses = new ArrayList<Integer>();
    private ArrayList<Integer> reponsesPrecedente = new ArrayList<Integer>();

    // indice calcul actuelle
    private int i=0;

    // si il faut afficher une correction
    private boolean correction=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maths_module3_calcul);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

        // get difficulté
        difficulte = getIntent().getStringExtra("difficulty_key");

        // si restart d'instance
        if (savedInstanceState!=null){

            // get l'indice pour laquelle on etait
            i = savedInstanceState.getInt("i");

            // get si il faut afficher une correction
            correction = savedInstanceState.getBoolean("correction");

            if (correction){

                // si il faut afficher correction
                // instancier les array pour que l'affiche se fasse
                reponsesPrecedente = savedInstanceState.getIntegerArrayList("reponses");
                reponses = savedInstanceState.getIntegerArrayList("reponses");

            }else{

                // sinon instancier normalement
                reponses = savedInstanceState.getIntegerArrayList("reponses");

            }
            // get les operands des precedent calcul
            ArrayList<Integer> op1 = savedInstanceState.getIntegerArrayList("operand1");
            ArrayList<Integer> op2 = savedInstanceState.getIntegerArrayList("operand2");
            ArrayList<String> op = savedInstanceState.getStringArrayList("operation");

            // creation des meme calculs
            initCalculSave(op1,op2,op);

        }else{
            // init les calcul en les créant
            initCalcul();
        }

        // update la vue avec le calcul actuelle a afficher
        updateVue();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


        ArrayList<Integer> op1 = new ArrayList<Integer>();
        ArrayList<Integer> op2 = new ArrayList<Integer>();
        ArrayList<String> op = new ArrayList<String>();


        // save les calculs actuelle get les operand a part
        for (Calcul cal : calculs){
            op1.add(cal.getOperande1());
            op2.add(cal.getOperande2());
            op.add(cal.getOperation());
        }

        // save dans le bundle
        outState.putIntegerArrayList("operand1", op1);
        outState.putIntegerArrayList("operand2", op2);
        outState.putStringArrayList("operation", op);


        // save les réponses tapé
        outState.putIntegerArrayList("reponses", reponses);

        // save l'indice du culcul actuel
        outState.putInt("i",i);

        // save si il faut afficher une correction
        outState.putBoolean("correction",correction);
    }


    // init les calcul avec des valeurs non random
    private void initCalculSave(ArrayList<Integer> op1, ArrayList<Integer> op2, ArrayList<String> op) {
        for (int i =0; i<10;i++){
            calculs.add(new Calcul(op1.get(i),op2.get(i),op.get(i)));
            reponses.add(-1);
        }
    }


    // return un calcul en fontion de la difficulté
    public Calcul getCalcul() {
        // Creation operand 1,2 et de l'operation
        int operand1 = 0;
        int operand2 = 0;
        String operation = "*"; // default l'operation est une multiplication
        int max;        // valeur max operand;
        int min;        // valeur min operand;
        Random r = new Random();
        if (difficulte.equals("facile")){ // si facil
            operation = "*";
            max = 10;                   // operand entre 0 et 10
            min = 0;
            operand1 = r.nextInt((max - min) + 1) + min;
            operand2 = r.nextInt((max - min) + 1) + min;
        }
        else if(difficulte.equals("normal")){ // si normale
            operation = "*";
            max = 12;                           // operand entre 2 et 12
            min = 2;
            operand1 = r.nextInt((max - min) + 1) + min;
            operand2 = r.nextInt((max - min) + 1) + min;
        }
        else{
            operation=getOperand();     // si difficil get random operand
            max = 13;
            min = 2;
            if (operation=="/"){
                // si operand = /
                // initialise d'abord le 2eme operant
                operand2 = r.nextInt((max - min) + 1) + min;
                // premiere operent = 2eme operant * un entier
                operand1 = operand2 * (r.nextInt((max - min) + 1) + min);
            }else{
                operand1 = r.nextInt((max - min) + 1) + min;
                operand2 = r.nextInt((max - min) + 1) + min;
            }
        }

        Calcul calcul = new Calcul(operand1, operand2, operation);
        return calcul;
    }


    // return un operand en fonction de la difficulté
    public String getOperand(){

        // difficulté facile et normal les operation sont des multiplication
        if (difficulte == "facile"){
            return "*";
        }
        else if(difficulte == "normal"){
            return "*";
        }

        // sinon ce sont des multiplication et division
        else{
            Random r = new Random();
            String [] arr = {"*", "/"};
            return arr[r.nextInt((1 ) + 1)];
        }
    }


    //initialise les 10 calculs
    public void initCalcul(){
        for (int i =0; i<10;i++){
            calculs.add(getCalcul());
            reponses.add(-1);
        }
    }


    // retour à la question précedent
    public void maths_module3_precedent(View view) {
        setReponses();
        i--;
        updateVue();
    }

    // question suivante
    public void maths_module3_suivant(View view) {
        if (i==9){
            setReponses(); // set les réponses
            endActivity(); // fin de l'activité
        }else{
            setReponses();  // set les réponses
            i++;            // question suivante
            updateVue();    // update avec nouveau calcul
        }
    }



    public void setReponses(){
        // get la reponse tapé
        EditText reponse = (EditText) findViewById(R.id.maths_module3_reponse);

        // si une réponse a été tapé
        if(!TextUtils.isEmpty(reponse.getText())) {

            // add la réponse tapé du calcul actuel
            reponses.set( i, Integer.parseInt(String.valueOf(reponse.getText())) );

            // clear le text
            reponse.setText("");
        }
        // sinon rien faire

    }



    public void updateVue(){

        // update vue par rapport a la question actuelle / 10
        TextView txt = findViewById(R.id.maths_module3_calcul_nb);
        txt.setText(i+1+"/10");


        // si il faut afficher la correction
        if (correction){
            ImageView img = findViewById(R.id.maths_module3_indication);
            img.setVisibility(View.VISIBLE);
            if (estError(reponsesPrecedente,i)){
                // show une croix si c'est faut
                img.setImageDrawable(getResources().getDrawable( R.drawable.wrong32 ));
            }else{
                // sinon un truc vert qui dit bien joué
                img.setImageDrawable(getResources().getDrawable( R.drawable.right32));
            }
        }


        Button previous = findViewById(R.id.maths_module3_precedent);
        Button next = findViewById(R.id.maths_module3_suivant);


        // si on est a la premiere question rend invisible le boutton précedent
        if (i==0){
            previous.setVisibility(View.GONE);
        }else{previous.setVisibility(View.VISIBLE);}


        // si a la derniere question le boutton suivant devient valider
        if(i==9){
            next.setText("Valider");
        }else{ next.setText("Suivant"); }

        // set l'expression du calcul
        TextView calcul = (TextView) findViewById(R.id.maths_module3_calcul);
        calcul.setText(calculs.get(i).getOperande1()+" " +calculs.get(i).getOperation()+" "+ calculs.get(i).getOperande2()+ " = ");

        // si il y a  une reponse  pour le calcul(i) set L'edit txt avec la réponse tapée
        if (reponses.get(i)!=-1){
            EditText reponse = (EditText) findViewById(R.id.maths_module3_reponse);
            reponse.setText(reponses.get(i).toString());
        }
    }



    // return si la réponse à la question(i) est fausse
    public boolean estError(List<Integer> reponses, int i){
        // si l'operand est une multiplication"
        if (calculs.get(i).getOperation()=="*"){
            // compare avec le resultat tapé
            if(calculs.get(i).getOperande1() * calculs.get(i).getOperande2()!=reponses.get(i)){
                return true;
            }
        }else { // sinon division
            // compare avec le resultat tapé
            if(calculs.get(i).getOperande1() / calculs.get(i).getOperande2()!=reponses.get(i)){
                return true;
            }
        }
        return false;
    }


    // fin de l'acitivité, compte les erreur
    public void endActivity(){
        updateVue();

        // compte les erreurs
        int nberror=0;
        for(int i = 0; i<10;i++){
            if (estError(reponses,i)){
                nberror++;
            }
        }

        // set la correction a true (affiche des indication si faux ou juste)
        correction=true;
        // initalise ancienne réponse
        reponsesPrecedente = reponses;


        // si il y a des faute -> loser intent
        if (nberror>0){
            Intent loserIntent = new Intent(this, com.example.projet.Maths_Activities.module3.maths_module3_loser.class);
            loserIntent.putExtra(com.example.projet.Maths_Activities.module3.maths_module3_loser.ERROR_KEY, nberror);
            startActivity(loserIntent);
            updateVue();
        }
        else{       // sinon winner
            Intent winnerIntent = new Intent(this, com.example.projet.Maths_Activities.module3.maths_module3_winner.class);
            winnerIntent.putExtra(com.example.projet.Maths_Activities.module3.maths_module3_winner.DIFFICULTY_KEY, difficulte);
            startActivity(winnerIntent);
        }
    }


    @Override
    public void onBackPressed() {
        Intent mathsmodule3home = new Intent(this, maths_module3_home.class);
        startActivity(mathsmodule3home);
        finish();
    }
}
