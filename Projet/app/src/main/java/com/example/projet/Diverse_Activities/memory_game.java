package com.example.projet.Diverse_Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import com.example.projet.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class memory_game extends Activity {
    public static final String DIFFICULTY_KEY = "difficulty_key";
    public static final String MEMO_KEY = "memo_key";
    private String difficulte;
    private int row;        // nb de ligne d'image
    private int index1 =-1; // premier selection
    private int index2 =-1; // deuxime selection
    private List<ImageView> cartes = new ArrayList<ImageView>();
    private List<Integer> fruits = new ArrayList<Integer>();
    private CountDownTimer count2;
    private CountDownTimer count5;
    private boolean memo;
    private TextView timerTextView;
    private long startTime = 0;
    private int secTotales;
    private int found=0;

    //runs without a timer by reposting this handler at the end of the runnable
    private Handler timerHandler = new Handler();
    private Runnable timerRunnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory_activity);
        init(); // initialise l'activité
    }

    private void init() {



        // initialisation du text montrant le temps depuis lancement du jeu
        timerTextView = findViewById(R.id.timer_memory);
        timerRunnable = new Runnable() { // Runnable changé la valeur du temps
            @Override
            public void run() {
                long millis = System.currentTimeMillis() - startTime;
                int seconds = (int) (millis / 1000);
                secTotales = seconds;
                int minutes = seconds / 60;
                seconds = seconds % 60;

                timerTextView.setText(String.format("%d:%02d", minutes, seconds));

                timerHandler.postDelayed(this, 500);
            }
        };



        // initialisation count2 -> timer qui fait attentre 1 seconde
        count2 = new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
                unlockButton();
            }
        };



        // initialisation count5 -> timer qui fait attentre 5 seconde
        count5 = new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
                hideEveryImage();
            }
        };



        // initialisation boolean memo si memo = true on veut modifier le score du joueur
        memo = getIntent().getBooleanExtra("memo_key",false);



        // initialisation de la difficulté en fonction de celle selectionné, on set le nombre de ligne de cases
        difficulte = getIntent().getStringExtra("difficulty_key");
        switch (difficulte){
            case "facil":
                row = 4; // si difficulté est facile 4 ligne de fruit
                break;
            case "normal":
                row = 5; // si difficulté est facile 5 ligne de fruit
                break;
            case "difficil":
                row = 6;    // si difficulté est facile 6 ligne de fruit
                break;
        }
        for (int i = 0; i<row*2;i++){
            //rajoute pour le nombre de cartes, deux i qui réprésente un fruit i.
            fruits.add(i); // paire de fruits
            fruits.add(i);
        }
        Collections.shuffle(fruits); // shuffle fruit pour random



        // initalisation de la table en fonction du nombre de ligne
        TableLayout tableLayout = findViewById(R.id.table_vue);
        for (int i = 0; i<row;i++){
            TableRow tableRowbuffer = new TableRow(getApplicationContext()); // Creation d'une nouvelle ligne tout les 4 cartes ajouté
            for (int j = 0; j<4;j++){

               ImageView imgbuffer = new ImageView(getApplicationContext()); // Creation d'un nouvelle Img
               imgbuffer.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.memory_case)); // set background

                imgbuffer.setOnClickListener(new View.OnClickListener() {  // set Onclick listener apelle selectBox si l'image est cliquée
                    @Override
                    public void onClick(View v) {
                        selectBox(v);
                    }
                });
                cartes.add(imgbuffer); // ajoute à un array l'img crée

                // Initialiser la table crée et ajout  l'image crée
                tableRowbuffer.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT));
                tableRowbuffer.addView(imgbuffer);
            }
            //ajout de la ligne crée à la table
            tableLayout.addView(tableRowbuffer);
        }


        // si memo On montre pendant 5 sec la postion de tout les fruits
        if(memo){
            showEveryImage();
        }else{  // sinon commence directement
            startCount();
        }
    }



    // debut timer (temps de jeu)
    private void startCount() {
        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);
    }



    // fin timer (temps de jeu)
    private void stopCount(){
        timerHandler.removeCallbacks(timerRunnable);
    }



    // fin du timer on cache les fruits qui on été montré
    private void hideEveryImage() {
        for (ImageView img : cartes){
            img.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.memory_case));
            img.setImageDrawable(null);
            img.setClickable(true);
        }
        startCount(); // commence le count (temps de jeu)
    }



    // fonction appellé si memo est true, montre pendant 5 sec la postion des fruits
    private void showEveryImage() {
        // une image fruit est associé à sa View et partage un meme Index dans leur array respective
        for (int i =0;i<row*4;i++){
            cartes.get(i).setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.memory_case_selected)); // background cartes selectionné
            switch (fruits.get(i)){                                                                                             // affichage des fruits associé
                case 0:
                    cartes.get(i).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fruit1));
                    break;
                case 1:
                    cartes.get(i).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fruit2));
                    break;
                case 2:
                    cartes.get(i).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fruit3));
                    break;
                case 3:
                    cartes.get(i).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fruit4));
                    break;
                case 4:
                    cartes.get(i).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fruit5));
                    break;
                case 5:
                    cartes.get(i).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fruit6));
                    break;
                case 6:
                    cartes.get(i).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fruit7));
                    break;
                case 7:
                    cartes.get(i).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fruit8));
                    break;
                case 8:
                    cartes.get(i).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fruit9));
                    break;
                case 9:
                    cartes.get(i).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fruit10));
                    break;
                case 10:
                    cartes.get(i).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fruit11));
                    break;
                case 11:
                    cartes.get(i).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fruit12));
                    break;
            }
            cartes.get(i).setClickable(false); // block le click
        }
        count5.start(); // commence le timer de 5 sec
    }



    // si on click sur une carte l'enregistre et montre son fruit
    public void selectBox(View view){
        ImageView imageV = (ImageView) view;
        int fr = cartes.indexOf(imageV); // get son Index
        if (fr != index1) {             // get son fruit par rapport à l'indexe // Index = premiere selection sinon = -1
            imageV.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.memory_case_selected));  // set les vue neccessaire pour montrer une selection
            switch (fruits.get(fr)) {
                case 0:
                    imageV.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fruit1));
                    break;
                case 1:
                    imageV.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fruit2));
                    break;
                case 2:
                    imageV.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fruit3));
                    break;
                case 3:
                    imageV.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fruit4));
                    break;
                case 4:
                    imageV.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fruit5));
                    break;
                case 5:
                    imageV.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fruit6));
                    break;
                case 6:
                    imageV.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fruit7));
                    break;
                case 7:
                    imageV.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fruit8));
                    break;
                case 8:
                    imageV.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fruit9));
                    break;
                case 9:
                    imageV.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fruit10));
                    break;
                case 10:
                    imageV.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fruit11));
                    break;
                case 11:
                    imageV.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fruit12));
                    break;
            }
            if (index1 == -1) { // si index1 n'est pas initalise
                index1 = fr;    //initialise l'index
            } else {        // sinon il est deja initialiser donc le click représente un second click
                index2 = fr;    // initalise l'index
                lockButton();  // block pendant 1 sec pour voir le resultat des cartes selectionner
            }
        }
    }



    //deblock le click des cartes
    private void unlockButton(){
        for (ImageView img: cartes){
            img.setClickable(true);
        }
        verif(); // verifie si les deux cartes selectionner son identique
    }



    // block les click des cartes
    private void lockButton() {

        for (ImageView img: cartes){
            img.setClickable(false);
        }
        count2.start(); // debut du count de 1 sec
    }



    // verifie si les index des cartes correspond avec celle des fruit
    private void verif() {
        if (fruits.get(index1) == fruits.get(index2)){ // si les carte son identique
            found+=2; // carte trouvé
            cartes.get(index1).setOnClickListener(new View.OnClickListener() { // supprime Onclick listenner pour ces cartes
                @Override
                public void onClick(View v) {
                }
            });
            cartes.get(index2).setOnClickListener(new View.OnClickListener() {  // supprime Onclick listenner pour ces cartes
                @Override
                public void onClick(View v) {
                }
            });
            if (found==row*4){ // si toute les carte son trouvé fin du jeu
                stopGame();
            }
        }
        else { //si les cartes sont pas identique reset leurs vue et les deselectionne
            cartes.get(index1).setImageDrawable(null);
            cartes.get(index1).setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.memory_case));
            cartes.get(index2).setImageDrawable(null);
            cartes.get(index2).setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.memory_case));
        }

        index1=-1; // deselectionne
        index2=-1;
    }



    // Intent stop le jeu -> vue de fin
    private void stopGame() {
        stopCount();
        Intent memoryEndIntent = new Intent(this, memory_end.class);
        memoryEndIntent.putExtra(memory_end.DIFFICULTY_KEY,difficulte);
        memoryEndIntent.putExtra(memory_end.MEMO_KEY,memo);
        memoryEndIntent.putExtra(memory_end.TIME_KEY,secTotales);
        startActivity(memoryEndIntent);
        finish();
    }


    @Override
    public void onBackPressed() {
        count2.cancel();
        count5.cancel();
        Intent memory_homeIntent = new Intent(this, memory_home.class);
        startActivity(memory_homeIntent);
        finish();
    }

}
