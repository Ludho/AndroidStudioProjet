package com.example.projet;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.projet.model.App;
import com.example.projet.model.User;

public class score extends Activity {

    private User user;
    private LinearLayout table;
    private LinearLayout addition;
    private LinearLayout quiz;
    private  LinearLayout memo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_activity);


        user = ((App) getApplication()).getUser();

        memo = findViewById(R.id.score_memo_profile);
        table = findViewById(R.id.score_table);
        addition = findViewById(R.id.score_addition);
        quiz = findViewById(R.id.score_quiz_profile);
        initScore();

        Spinner spinner = findViewById(R.id.score_spinner);
        String[] arraySpinner = new String[] {"Table de multiplication", "Addition/Multiplication", "Quizz","Correspondance"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        table.setVisibility(View.VISIBLE);
        addition.setVisibility(View.GONE);
        quiz.setVisibility(View.GONE);
        memo.setVisibility(View.GONE);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch (position){
                    case 0:
                        table.setVisibility(View.VISIBLE);
                        addition.setVisibility(View.GONE);
                        quiz.setVisibility(View.GONE);
                        memo.setVisibility(View.GONE);
                        break;
                    case 1:
                        table.setVisibility(View.GONE);
                        addition.setVisibility(View.VISIBLE);
                        quiz.setVisibility(View.GONE);
                        memo.setVisibility(View.GONE);
                        break;
                    case 2:
                        table.setVisibility(View.GONE);
                        addition.setVisibility(View.GONE);
                        quiz.setVisibility(View.VISIBLE);
                        memo.setVisibility(View.GONE);
                        break;
                    case 3:
                        table.setVisibility(View.GONE);
                        addition.setVisibility(View.GONE);
                        quiz.setVisibility(View.GONE);
                        memo.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
    }

    private void initScore() {

        RelativeLayout relativeBuffer;
        String tables = user.getMaths_1_score();    // affiche les trophée pour l'activité des tables de multiplication
        for (int i = 0; i<9;i++){                   // affiche des trophée pour chaque table de multiplication finit
            if (tables.charAt(i)=='1'){
                switch (i){
                    case 0:
                        relativeBuffer = findViewById(R.id.table1);
                        break;
                    case 1:
                        relativeBuffer = findViewById(R.id.table2);
                        break;
                    case 2:
                        relativeBuffer = findViewById(R.id.table3);
                        break;
                    case 3:
                        relativeBuffer = findViewById(R.id.table4);
                        break;
                    case 4:
                        relativeBuffer = findViewById(R.id.table5);
                        break;
                    case 5:
                        relativeBuffer = findViewById(R.id.table6);
                        break;
                    case 6:
                        relativeBuffer = findViewById(R.id.table7);
                        break;
                    case 7:
                        relativeBuffer = findViewById(R.id.table8);
                        break;
                    default:
                        relativeBuffer = findViewById(R.id.table9);
                        break;
                }
                relativeBuffer.setVisibility(View.VISIBLE);
            }
        }

        TextView textViewbuffer;

        // set score module 2 de maths
        textViewbuffer = findViewById(R.id.score_addition_addition);
        textViewbuffer.setText("Addition fait en 1 minute : " + user.getMaths_2_score());

        // set score module 3 de maths
        textViewbuffer = findViewById(R.id.score_addition_facile);
        textViewbuffer.setText("facile : " + user.getMaths_3_score_facile());
        textViewbuffer = findViewById(R.id.score_addition_normale);
        textViewbuffer.setText("normal : " + user.getMaths_3_score_normale());
        textViewbuffer = findViewById(R.id.score_addition_difficile);
        textViewbuffer.setText("difficile : " + user.getMaths_3_score_difficile());


        // set score quizz
        textViewbuffer = findViewById(R.id.score_quiz_profile_histoire);
        textViewbuffer.setText("histoire : "+ user.getCulture_1_score_histoire());
        textViewbuffer = findViewById(R.id.score_quiz_profile_francais);
        textViewbuffer.setText("français : "+ user.getCulture_1_score_francais());
        textViewbuffer = findViewById(R.id.score_quiz_profile_geographie);
        textViewbuffer.setText("géographie : "+ user.getCulture_1_score_geographie());


        // set score jeu memo
        int sec = user.getDiver_score_facil();  // get les sec et transformer en minute
        if(sec == -1){
            sec = 0;
        }
        int minutes = sec / 60;
        sec = sec % 60;
        textViewbuffer = findViewById(R.id.score_memo_profile_facil);
        textViewbuffer.setText("facile : " + String.format("%d", minutes) + " minute(s) et "+ String.format("%d", sec) + " seconde(s).");

        sec = user.getDiver_score_normal(); // get les sec et transformer en minute
        if(sec == -1){
            sec = 0;
        }
        minutes = sec / 60;
        sec = sec % 60;
        textViewbuffer = findViewById(R.id.score_memo_profile_normal);
        textViewbuffer.setText("normal : " + String.format("%d", minutes) + " minute(s) et "+ String.format("%d", sec) + " seconde(s).");
        textViewbuffer = findViewById(R.id.score_memo_profile_difficil);
        sec = user.getDiver_score_difficil();
        if(sec == -1){  // get les sec et transformer en minute
            sec = 0;
        }
        minutes = sec / 60;
        sec = sec % 60;
        textViewbuffer.setText("difficile : " + String.format("%d", minutes) + " minute(s) et "+ String.format("%d", sec) + " seconde(s).");

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void returnButton(View view) {
        finish();
    }
}
