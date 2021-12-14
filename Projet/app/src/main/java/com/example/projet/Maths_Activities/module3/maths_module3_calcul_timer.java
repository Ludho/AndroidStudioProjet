package com.example.projet.Maths_Activities.module3;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projet.Maths_Activities.Calcul;
import com.example.projet.R;

import java.util.Random;

public class maths_module3_calcul_timer  extends Activity {


    // difficulté
    public static final String DIFFICULTY_KEY = "difficulty_key";
    String difficulte;

    // calcul et réponse
    private Calcul calcul;
    private Integer reponse = -1;

    // nombre d'erreur commis
    private int nberror =0;

    private int nbCalcul = 0;

    // compteur de 1 min avant la fin de l'exo
    private CountDownTimer count;
    private long timeRestant = 60000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maths_module3_calcul_timer);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

        // set la difficulté selectionné
        difficulte = getIntent().getStringExtra("difficulty_key");


        if (savedInstanceState!=null){

            // initialise difficulté
            difficulte = savedInstanceState.getString("difficulte");

            // initialise le nombre de calcul fait
            nbCalcul = savedInstanceState.getInt("nbCalcul");

            // initialise le calcul en cours
            calcul = new Calcul(savedInstanceState.getInt("operand1"),savedInstanceState.getInt("operand2"),savedInstanceState.getString("operation"));

            // initalise la reponse tapé
            EditText reponsetxt = (EditText) findViewById(R.id.maths_module3_reponse_timer);
            if (savedInstanceState.getInt("reponse")!=-1){
                reponsetxt.setText(String.valueOf(savedInstanceState.getInt("reponse")));
            }

            // initialise nberror
            nberror =savedInstanceState.getInt("nberror");

            // initialise le temps restant
            timeRestant= savedInstanceState.getLong("timeLeft");

        }else {
            // ajout d'un premier calcul random
            addCalcul();
        }

        updateVue();

        // creation compteur de 1 minute
        count = new CountDownTimer(timeRestant, 1000) {

            public void onTick(long millisUntilFinished) {
                TextView temps = findViewById(R.id.maths_module3_calcul_time);
                temps.setText("secondes restantes : " + millisUntilFinished / 1000);
                timeRestant = millisUntilFinished;
            }

            public void onFinish() {
                finExercice();
            }
        }.start();  // se lance à la création
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


        Integer op1 ;
        Integer op2 ;
        String op ;

        // save le calcul actuelle get les operand a part
        op1 = calcul.getOperande1();
        op2 = calcul.getOperande2();
        op = calcul.getOperation();


        // save dans le bundle
        outState.putInt("operand1", op1);
        outState.putInt("operand2", op2);
        outState.putString("operation", op);

        // save nberror
        outState.putInt("nberror",nberror);

        //save difficulté
        outState.putString("difficulte", difficulte);


        // save nombre de calcul fait
        outState.putInt("nbCalcul", nbCalcul);

        // save temps restant
        outState.putLong("timeLeft", timeRestant);
        count.cancel();

        // get reponse tapé
        EditText reponsetxt = (EditText) findViewById(R.id.maths_module3_reponse_timer);
        // si une réponse a été tapé
        if(!TextUtils.isEmpty(reponsetxt.getText())) {
            // add la réponse tapé du calcul actuel
            this.reponse = Integer.parseInt(String.valueOf(reponsetxt.getText()));
        }
        outState.putInt("reponse", this.reponse);

    }



    private void updateVue() {
        // update expression avec nouveau calcul
        TextView calcul = (TextView) findViewById(R.id.maths_module3_calcul_timer);
        calcul.setText(this.calcul.getOperande1()+" " +this.calcul.getOperation()+" "+ this.calcul.getOperande2()+ " = ");
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



    public void addCalcul(){
        // creation d'un nouveau calcul
        calcul = getCalcul();
        reponse = -1;
    }


    // question suivante
    public void maths_module3_suivant(View view) {
        setReponses();
        addCalcul();
        nbCalcul++;
        updateVue();
    }


    public void setReponses(){
        // get reponse tapé
        EditText reponsetxt = (EditText) findViewById(R.id.maths_module3_reponse_timer);

        // si une réponse a été tapé
        if(!TextUtils.isEmpty(reponsetxt.getText())) {

            // add la réponse tapé du calcul actuel
            this.reponse = Integer.parseInt(String.valueOf(reponsetxt.getText()));

            // clear le text
            reponsetxt.setText("");
        }
        // calcul l'erreur directement
        if (estError()){
            nberror++;
        }
    }

    public boolean estError(){
        // return si la réponse à la question(i) est correct
        if (this.calcul.getOperation()=="*"){ // si l'operand est une multiplication"
            if(this.calcul.getOperande1() * this.calcul.getOperande2()!=reponse){ // compare avec le resultat tapé
                this.reponse=-1;
                return true;
            }
        }else { // sinon division
            if(this.calcul.getOperande1() / this.calcul.getOperande2()!=reponse){ // compare avec le resultat tapé
                this.reponse=-1;
                return true;
            }
        }
        this.reponse=-1;
        return false;
    }



    private void finExercice() {
        Intent endIntent = new Intent(this, com.example.projet.Maths_Activities.module3.maths_module3_end.class);
        endIntent.putExtra(com.example.projet.Maths_Activities.module3.maths_module3_end.CORRECT_KEY, nbCalcul -nberror);
        endIntent.putExtra(com.example.projet.Maths_Activities.module3.maths_module3_end.DIFFICULTY_KEY, difficulte);
        endIntent.putExtra(maths_module3_end.NBCALCUL_KEY, nbCalcul);
        startActivity(endIntent);
    }

    @Override
    public void onBackPressed() {
        finish();
        count.cancel();
        Intent mathsmodule3home = new Intent(this, maths_module3_home.class);
        startActivity(mathsmodule3home);
    }
}