package com.example.projet.Culture_Activities.module1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.projet.R;
import com.example.projet.model.DatabaseClient;
import com.example.projet.model.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class culture_module1_quizz extends Activity {

    private TextView txtquestion;
    private TextView numquestion;
    private Button btnsuivant;

    // choix de réponse au question + question
    private RadioButton choix1;
    private RadioButton choix2;
    private RadioButton choix3;
    private RadioButton choix4;
    private RadioGroup rbGroup;
    List<Question> allquestions;
    private Question questionActuel;

    // bd
    private DatabaseClient mDb;

    // dialog de l'actitivé
    private culture_module1_win_dialog correctDialog;
    private culture_module1_loose_dialog wrongDialog;
    private culture_module1_timer_dialog timerDialog;

    // score du joueur
    private int score=0;
    private TextView textViewScore;
    private int correctAns=0;

    // theme du quiz
    public static final String THEME_KEY = "theme_key";
    private String theme;

    // indice de la question actuelle
    int i =0;

    // timer du quiz
    CountDownTimer count;
    long timeleft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.culture_module1_quizz);


        // Initalisation d'un timer, temps pour repondre a chaque question
        count= new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                TextView temps = findViewById(R.id.culture_module1_timer);
                temps.setText("00:" + millisUntilFinished / 1000);
                timeleft = millisUntilFinished / 1000;
            }
            public void onFinish() {
                timesup();
            } // si temps ecoulé appelle timesup
        };


        // Initialisation de la bd + current user
        mDb = DatabaseClient.getInstance(getApplicationContext());


        // get Intent le theme
        theme = getIntent().getStringExtra("theme_key");


        //requete a la bd des question avec le theme choisis
        getQuestions(theme);

    }

    // renvoie les questions de la bd par rapport a un theme
    private void getQuestions(String theme) {

        class GetQuestions extends AsyncTask<Void, Void, List<Question>> {

            @Override
            protected List<Question> doInBackground(Void... voids) {
                List<Question> questionList = mDb.getAppDatabase()
                        .questionDao()
                        .getQuestionsTheme(theme);
                return questionList;
            }

            @Override
            protected void onPostExecute(List<Question> questions) {
                super.onPostExecute(questions);

                // Mettre à jour l'adapter avec la liste de taches
                allquestions = questions;
                Collections.shuffle(allquestions);
                init();
                updateVue();
            }
        }

        //////////////////////////
        // IMPORTANT bien penser à executer la demande asynchrone
        // Création d'un objet de type GetQuestions et execution de la demande asynchrone
        GetQuestions gu = new GetQuestions();
        gu.execute();
    }


    // Iniitalisation de tout les composant de quizz
    private void init() {
        numquestion = findViewById(R.id.culture_module1_question_total);
        txtquestion = findViewById(R.id.culture_module1_question);
        btnsuivant = findViewById(R.id.culture_module1_next);


        // choix de réponse a la question
        choix1 = findViewById(R.id.culture_module1_reponse1);
        choix2 = findViewById(R.id.culture_module1_reponse2);
        choix3 = findViewById(R.id.culture_module1_reponse3);
        choix4 = findViewById(R.id.culture_module1_reponse4);
        rbGroup = findViewById(R.id.culture_module1_choix);


        // dialog du quizz
        wrongDialog =  new culture_module1_loose_dialog(this);
        correctDialog = new culture_module1_win_dialog(this);
        timerDialog = new culture_module1_timer_dialog(this);

        // score quizz
        textViewScore = findViewById(R.id.culture_module1_score);

        // set Onclick Update la couleur des boutton au click pour montrer une selection
        rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){

                    case R.id.culture_module1_reponse1:
                        // le boutton 1 est selectionné
                        choix1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.selected_option_a));
                        choix2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_b));
                        choix3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_c));
                        choix4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_d));
                        break;

                    case R.id.culture_module1_reponse2:
                        // le boutton 2 est selectionné
                        choix1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_a));
                        choix2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.selected_option_b));
                        choix3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_c));
                        choix4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_d));
                        break;

                    case R.id.culture_module1_reponse3:
                        // le boutton 3 est selectionné
                        choix1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_a));
                        choix2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_b));
                        choix3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.selected_option_c));
                        choix4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_d));
                        break;

                    case R.id.culture_module1_reponse4:
                        // le boutton 4 est selectionné
                        choix1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_a));
                        choix2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_b));
                        choix3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_c));
                        choix4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.selected_option_d));
                        break;

                }

            }
        });
    }


    //update vue reset la vue par rapport à une question actuelle
    private void updateVue() {

        // commence le compteur
        count.start();

        // get la question actuelle
        questionActuel = allquestions.get(i);

        // set la question actuelle
        numquestion.setText("Question: "+ (i+1)+"/10");
        txtquestion.setText(questionActuel.getQuestion());


        // update la vue des reponse proposé
        List<String> choix_propose = new ArrayList<String>();
        choix_propose.add(questionActuel.getBonneReponse());        // set les reponse de la question
        choix_propose.add(questionActuel.getReponse1());
        choix_propose.add(questionActuel.getReponse2());
        choix_propose.add(questionActuel.getReponse3());
        Collections.shuffle(choix_propose);
        choix1.setText(choix_propose.get(0));       // montre reponse 1 de la question
        choix2.setText(choix_propose.get(1));       // montre reponse  2 de la question
        choix3.setText(choix_propose.get(2));       // montre reponse 3 de la question
        choix4.setText(choix_propose.get(3));       // montre reponse 4 de la question


        // reset les selection précédente
        rbGroup.clearCheck();
        choix1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_a));
        choix2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_b));
        choix3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_c));
        choix4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_d));
        choix1.setTextColor(Color.parseColor("#000000"));
        choix2.setTextColor(Color.parseColor("#000000"));
        choix3.setTextColor(Color.parseColor("#000000"));
        choix4.setTextColor(Color.parseColor("#000000"));
    }


    //Boutton suivant -> question suivante increment le nombre de question et call dialog en fonction du resultat
    public void BouttonSuivant(View view) {
        RadioButton bufferbutton = findViewById(rbGroup.getCheckedRadioButtonId());

        // si quelque chose est selectionné
        if (bufferbutton!=null){
            count.cancel();
            // check si le text du boutton tapé correspond à la réponse
            if (questionActuel.getBonneReponse()!=bufferbutton.getText()){
                showResult(bufferbutton, false);
            }else{
                showResult(bufferbutton, true);
            }
        }
        // renvoie un toast si aucune reponse est selectionné
        else{
            Toast.makeText(this, "Selectionne une réponse", Toast.LENGTH_SHORT).show();
        }

    }


    public void questionSuivante(){
        // si la question actuelle est la 10 eme on arrete l'activité
        if (i==9){
            result();
        }else{ // sinon update la vue avec une nouvelle question
            i++;
            if (i==9){
                btnsuivant.setText("Terminer");
            }
            updateVue();
        }
    }



    private void showResult(RadioButton buttonchecked, boolean win) {
        // update visuellement en fonction du resultat + ajout score + start dialog approprie
        if (win){
            buttonchecked.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_answer_correct));
            buttonchecked.setTextColor(Color.WHITE);
            correctAns++;
            score += timeleft + 20 ;  // score = score + temps restant + 20;
            textViewScore.setText("Score: " + String.valueOf(score));
            correctDialog.correctDialog(score,this);
        }else{
            buttonchecked.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_answer_wrong));
            buttonchecked.setTextColor(Color.WHITE);
            wrongDialog.WrongDialog(questionActuel.getBonneReponse(),this);
        }
    }


    // le temps est écoulé
    private void timesup() {
        RadioButton bufferbutton = findViewById(rbGroup.getCheckedRadioButtonId());
        if (bufferbutton!=null){ // si un boutton est selectionné
            if (questionActuel.getBonneReponse()!=bufferbutton.getText()){  // regarde le resultat
                showResult(bufferbutton, false);                        // affiche en fonction du resultat
            }else{
                showResult(bufferbutton, true);
            }
        }
        else{
            timerDialog.timerDialog(questionActuel.getBonneReponse(),this); // affiche le dialog temps écoulé
        }
    }


    // Intent vers activité de fin montrant le resultat total
    private void result() {
        Intent endIntent = new Intent(this, com.example.projet.Culture_Activities.module1.culture_module1_end.class);
        endIntent.putExtra(com.example.projet.Culture_Activities.module1.culture_module1_end.CORRECT_KEY, correctAns);
        endIntent.putExtra(com.example.projet.Culture_Activities.module1.culture_module1_end.SCORE_KEY, score);
        endIntent.putExtra(com.example.projet.Culture_Activities.module1.culture_module1_end.THEME_KEY, theme);
        startActivity(endIntent);
    }


    @Override
    public void onBackPressed() {
        // cancel count + retour au home de l'exercice
        count.cancel();
        Intent quizHomeIntent = new Intent(this, com.example.projet.Culture_Activities.module1.culture_module1_home.class);
        startActivity(quizHomeIntent);
    }

}
