package com.example.projet.Maths_Activities.module2;

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

import com.example.projet.Maths_Activities.Calcul;

public class maths_module2_calcul extends Activity {


    // taille des Operands et operation
    public static final String OPERAND_KEY = "operand_key";
    public static final String NUM1_KEY = "num1_key";
    public static final String NUM2_KEY = "num2_key";
    public static final String NUM4_KEY = "num4_key";
    public static final String NUM5_KEY = "num5_key";

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
        setContentView(R.layout.maths_module2_calcul);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

        // si restart d'instance
        if (savedInstanceState!=null){

            // get l'indice pour laquelle on etait
            i = savedInstanceState.getInt("i");

            // get si il faut afficher une correction
            correction = savedInstanceState.getBoolean("correction");

            // si il faut afficher correction
            if (correction){

                // instancier les arrays de réponses précédente pour que l'affiche d'indication se fasse
                reponsesPrecedente = savedInstanceState.getIntegerArrayList("reponses");
                reponses = savedInstanceState.getIntegerArrayList("reponses");

            }else{

                // sinon instancier normalement
                reponses = savedInstanceState.getIntegerArrayList("reponses");

            }


            // get les operands et operation des precedent calcul
            ArrayList<Integer> op1 = savedInstanceState.getIntegerArrayList("operand1");
            ArrayList<Integer> op2 = savedInstanceState.getIntegerArrayList("operand2");
            ArrayList<String> op = savedInstanceState.getStringArrayList("operation");

            // init avec les précédente valeur
            initCalculSave(op1,op2,op);

        }else{
            // init les calcul en les créants
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



    // return un operand en fonction du nombre de chiffre cocher
    public int getRandomNumberOperand(boolean operand1) {

        boolean n2; // le nombre est à 2 chiffre
        boolean n3; // le nombre est à 3 chiffre
        int max;

        // si on veut l'operand 1
        if (operand1){ // regarder combien de chiffre on veut
            n3 = getIntent().getBooleanExtra("num1_key",false);
            n2 = getIntent().getBooleanExtra("num2_key",false);
        }

        else{  // sinon on veut l'operand 2
            n3 = getIntent().getBooleanExtra("num4_key",false); // regarder combien de chiffre on veut
            n2 = getIntent().getBooleanExtra("num5_key",false); // regarder combien de chiffre on veut
        }

        if(n3){
            max=999;          // si 3 cocher max = 999
        }else{
            if (n2){
                max=99;         // si 2 cocher max = 999
            }else{
                max=9;          // defauly max = 9
            }
        }

        // random par rapport au max
        Random r = new Random();
        return r.nextInt((max - 0) + 1) + 0;
    }


    // return operand en fonction de ceux selectionné au home
    public String getOperand(){
        int ope = getIntent().getIntExtra("operand_key",0);

        // si il selection addition et soustraction randomizer entre les deux operand
        if (ope == 2){
            Random r = new Random();
            String [] arr = {"-", "+"};
            return arr[r.nextInt((1 ) + 1)];
        }

        // sinon = celui associé
        else if(ope ==1){
            return "+";
        }
        else{
            return "-";
        }

    }


    // initialisation des 10 calculs
    public void initCalcul(){

        for (int i =0; i<10;i++){

            // get operands
            int op1 = getRandomNumberOperand(true);
            int op2 = getRandomNumberOperand(false);


            // on met toujours le plus grand operand à gauche afin d'éviter les calculs négative
            if (op1<op2){
                calculs.add(new Calcul(op2,op1,getOperand()));
            }else{
                calculs.add(new Calcul(op1,op2,getOperand()));
            }

            // add réponse
            reponses.add(-1);
        }

    }


    // retour à la question précedent
    public void maths_module2_precedent(View view) {
        // set la réponse tapé
        setReponses();
        i--;
        updateVue(); // update la vue par rapport au nouveau i
    }

    // question suivante
    public void maths_module2_suivant(View view) {
        if (i==9){ // si 10eme question fin de l'activité
            setReponses();
            endActivity();
        }else{ // sinon on set la reponse ecrite
            setReponses();
            i++;         // incrémente le calcul
            updateVue(); // update par rapport au calcul
        }
    }


    public void setReponses(){
        // get la réponse ecrite
        EditText reponse = (EditText) findViewById(R.id.maths_module2_reponse);
        if(!TextUtils.isEmpty(reponse.getText())) { // si different de vide reponse = vide

            // add la réponse tapé du calcul actuel
            reponses.set( i, Integer.parseInt(String.valueOf(reponse.getText())) );

            // clear le text
            reponse.setText("");
        }
        // sinon rien faire

    }


    public void updateVue(){

        // update vue par rapport a la question actuelle / 10
        TextView txt = findViewById(R.id.maths_module2_calcul_nb);
        txt.setText(i+1+"/10");

        // si il faut afficher la correction
        if (correction){
            // affiche l'img d'indication
            ImageView img = findViewById(R.id.maths_module2_indication);
            img.setVisibility(View.VISIBLE);
            if (estError(reponsesPrecedente,i)){
                // show une croix si c'est faut
                img.setImageDrawable(getResources().getDrawable( R.drawable.wrong32 ));
            }else{
                // sinon un truc vert qui dit bien joué
                img.setImageDrawable(getResources().getDrawable( R.drawable.right32));
            }
        }

        Button previous = findViewById(R.id.maths_module2_precedent);
        Button next = findViewById(R.id.maths_module2_suivant);

        // si on est a la premiere question rend invisible le boutton précedent
        if (i==0){
            previous.setVisibility(View.GONE);
        }else{previous.setVisibility(View.VISIBLE);}

        // si a la derniere question le boutton suivant devient valider
        if(i==9){
            next.setText("Valider");
        }else{ next.setText("Suivant"); }

        // set l'expression du calcul
        TextView calcul = (TextView) findViewById(R.id.maths_module2_calcul);
        calcul.setText(calculs.get(i).getOperande1()+" " +calculs.get(i).getOperation()+" "+ calculs.get(i).getOperande2()+ " = ");

        // si il y a  une reponse  pour le calcul(i)
        // set L'edit txt avec la réponse tapée précedament
        if (reponses.get(i)!=-1){
            EditText reponse = (EditText) findViewById(R.id.maths_module2_reponse);
            reponse.setText(reponses.get(i).toString());
        }

    }


    // return true si la réponse à la question(i) est fausse
    public boolean estError(List<Integer> reponses, int i){

        // check l'opperation du calcul
        if (calculs.get(i).getOperation()=="+"){
            // si l'operation est une addition, additionne les deux opérend
            // et compare avec la réponse tapé
            if(calculs.get(i).getOperande1() + calculs.get(i).getOperande2()!=reponses.get(i)){
                return true;
            }

            // sinon l'operation est une soustraction, soustrait les deux opérend
            // et compare avec la réponse tapé
        }else {
            // operand le plus grand est toujours a gauche pour eviter des valeurs negative
            if (calculs.get(i).getOperande1() < calculs.get(i).getOperande2()){
                // inverse les deux opérands
                if(calculs.get(i).getOperande2() - calculs.get(i).getOperande1()!=reponses.get(i)){
                    return true;
                }
            }else {
                if(calculs.get(i).getOperande1() - calculs.get(i).getOperande2()!=reponses.get(i)){
                    return true;
                }
            }
        }
        return false;
    }


    // fin de l'acitivé
    public void endActivity(){

        updateVue();

        // calcul du nombre d'erreur
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


        if (nberror>0){  // si il y a des faute -> loser intent
            Intent loserIntent = new Intent(this, com.example.projet.Maths_Activities.module2.maths_module2_loser.class);
            loserIntent.putExtra(com.example.projet.Maths_Activities.module1.maths_module1_loser.ERROR_KEY, nberror);
            startActivity(loserIntent);
            updateVue();
        }
        else{ // sinon winner
            Intent winnerIntent = new Intent(this, com.example.projet.Maths_Activities.module2.maths_module2_winner.class);
            startActivity(winnerIntent);
        }
    }


    @Override
    public void onBackPressed() {
        Intent module2IntentHome = new Intent(this, maths_module2_home.class);
        startActivity(module2IntentHome);
        finish();
    }
}
