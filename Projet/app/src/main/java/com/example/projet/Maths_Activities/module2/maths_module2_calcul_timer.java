package com.example.projet.Maths_Activities.module2;

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

public class maths_module2_calcul_timer extends Activity {

    // taille des Operands et operation
    public static final String OPERAND_KEY = "operand_key";
    public static final String NUM1_KEY = "num1_key";
    public static final String NUM2_KEY = "num2_key";
    public static final String NUM4_KEY = "num4_key";
    public static final String NUM5_KEY = "num5_key";
    private boolean num1;
    private boolean num2;
    private boolean num4;
    private boolean num5;

    // calculs et réponses
    private Calcul calcul;
    private Integer reponse = -1;

    // indice de question actuelle
    private int nbCalcul =0;

    // compteur de 1 min avant la fin de l'exo
    private CountDownTimer count;

    // nombre d'erreur commis
    private int nberror =0;

    // temps
    private long timeRestant=60000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maths_module2_calcul_timer);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

        // initialise taille des Operands
        num1 =  getIntent().getBooleanExtra("num1_key",false);
        num2 = getIntent().getBooleanExtra("num2_key",false);
        num4 =  getIntent().getBooleanExtra("num4_key",false);
        num5 = getIntent().getBooleanExtra("num5_key",false);

        if (savedInstanceState!=null){

            // initialise le nombre d'erreur
            nberror = savedInstanceState.getInt("nberror");

            // initialise le nombre de calcul fait
            nbCalcul = savedInstanceState.getInt("nbCalcul");

            // initialise le calcul en cours
            calcul = new Calcul(savedInstanceState.getInt("operand1"),savedInstanceState.getInt("operand2"),savedInstanceState.getString("operation"));

            // initalise la reponse tapé
            EditText reponsetxt = (EditText) findViewById(R.id.maths_module2_reponse_timer);
            if (savedInstanceState.getInt("reponse")!=-1){
                reponsetxt.setText(String.valueOf(savedInstanceState.getInt("reponse")));
            }

            // initialise le temps restant
            timeRestant= savedInstanceState.getLong("timeLeft");

            // taille des Operands
            num1 =  savedInstanceState.getBoolean("num1");
            num2 = savedInstanceState.getBoolean("num2");
            num4 =  savedInstanceState.getBoolean("num4");
            num5 = savedInstanceState.getBoolean("num5");

        }else{
            // ajout d'un premier calcul
            addCalcul();
        }

        updateVue();

        // creation compteur de 1 minute
        count = new CountDownTimer(timeRestant, 1000) {

            public void onTick(long millisUntilFinished) {
                TextView temps = findViewById(R.id.maths_module2_calcul_time);
                temps.setText("secondes restantes : " + millisUntilFinished / 1000);
                timeRestant = millisUntilFinished;
            }

            public void onFinish() {
                finExercice();
            } // on finish fin de l'exo
        };

        // se lance a la creation
        count.start();
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


        //save nombre de chiffre d'operand
        outState.putBoolean("num1",num1);
        outState.putBoolean("num2",num2);
        outState.putBoolean("num3",num4);
        outState.putBoolean("num4",num5);

        // save nberror
        outState.putInt("nberror",nberror);

        // save nombre de calcul fait
        outState.putInt("nbCalcul", nbCalcul);

        // save temps restant
        outState.putLong("timeLeft", timeRestant);
        count.cancel();

        // get reponse tapé
        EditText reponsetxt = (EditText) findViewById(R.id.maths_module2_reponse_timer);
        // si une réponse a été tapé
        if(!TextUtils.isEmpty(reponsetxt.getText())) {
            // add la réponse tapé du calcul actuel
            this.reponse = Integer.parseInt(String.valueOf(reponsetxt.getText()));
        }
        outState.putInt("reponse", this.reponse);

    }




    // update vue avec un nouveau calcul
    private void updateVue() {

        // set l'expression du nouveau calcul
        TextView calcultxt = (TextView) findViewById(R.id.maths_module2_calcul_timer);
        calcultxt.setText(calcul.getOperande1()+" " +calcul.getOperation()+" "+ calcul.getOperande2()+ " = ");

    }


    // return un operand en fonction du nombre de chiffre cocher
    public int getRandomNumberOperand(boolean operand1) {

        boolean n2; // le nombre est à 2 chiffre
        boolean n3; // le nombre est à 3 chiffre
        int max;

        // si on veut l'operand 1
        if (operand1){
            // regarder combien de chiffre on veut
            n3 = num1;
            n2 = num2;
        }
        // sinon on veut l'operand 2
        else{
            // regarder combien de chiffre on veut
            n3 = num4;
            n2 = num5;
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

    // Creation d'un nouvel calcul
    public void addCalcul(){

        // get opérands
        int op1 = getRandomNumberOperand(true);
        int op2 = getRandomNumberOperand(false);


        // on met toujours le plus grand operand à gauche afin d'éviter les calculs négative
        if (op1<op2){
            calcul = new Calcul(op2,op1,getOperand());
        }else{
            calcul = new Calcul(op1,op2,getOperand());
        }
        reponse=-1;
    }


    // Button suivant initialise les reponses + ajoute calcul + incrément le nombre de calcul + update vue
    public void maths_module2_suivant(View view) {
        setReponses();
        addCalcul();
        nbCalcul++;
        updateVue();
    }


    public void setReponses(){

        // get la reponse du user
        EditText reponsetxt = (EditText) findViewById(R.id.maths_module2_reponse_timer);

        // si rien est mis reponse = vide;
        if(!TextUtils.isEmpty(reponsetxt.getText())) {
            reponse = Integer.parseInt(String.valueOf(reponsetxt.getText()));
            reponsetxt.setText("");
        }
        // calcul directement si la réponse est fausse
        if (estError()){
            nberror++;
        }
    }


    // return true si la réponse à la question(i) est fausse
    public boolean estError(){

        // check l'opperation du calcul
        if (calcul.getOperation()=="+"){
            // si l'operation est une addition, additionne les deux opérend
            // et compare avec la réponse tapé
            if(calcul.getOperande1() + calcul.getOperande2()!=reponse){
                return true;
            }

            // sinon l'operation est une soustraction, soustrait les deux opérend
            // et compare avec la réponse tapé
        }else {
            // operand le plus grand est toujours a gauche pour eviter des valeurs negative
            if (calcul.getOperande1() < calcul.getOperande2()){
                // inverse les deux opérands
                if(calcul.getOperande2() - calcul.getOperande1()!=reponse){
                    return true;
                }
            }else {
                if(calcul.getOperande1() - calcul.getOperande2()!=reponse){
                    return true;
                }
            }
        }
        return false;
    }


    // call fin de l'exo
    private void finExercice() {
        Intent endIntent = new Intent(this, com.example.projet.Maths_Activities.module2.maths_module2_end.class);
        endIntent.putExtra(com.example.projet.Maths_Activities.module2.maths_module2_end.CORRECT_KEY, nbCalcul -nberror);
        endIntent.putExtra(maths_module2_end.NBCALCUL_KEY, nbCalcul);
        startActivity(endIntent);
    }

    @Override
    public void onBackPressed() {
        count.cancel();
        finish();
        Intent mathsmodule2home = new Intent(this, maths_module2_home.class);
        startActivity(mathsmodule2home);
    }
}
