package com.example.projet.Diverse_Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.projet.R;
import com.example.projet.model.App;
import com.example.projet.model.DatabaseClient;
import com.example.projet.model.User;

public class memory_end extends Activity {


    public static final String DIFFICULTY_KEY = "difficulty_key";
    public static final String MEMO_KEY = "memo_key";
    public static final String TIME_KEY = "time_key";
    private DatabaseClient mDb;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory_end);

        // get database + current user
        mDb = DatabaseClient.getInstance(getApplicationContext());
        user = ((App) getApplication()).getUser();

        // initialise les temps par rapport au temps pris sur l'activité
        int sec = getIntent().getIntExtra("time_key",0);
        int minutes = sec / 60;
        sec = sec % 60;
        TextView timerTextView = findViewById(R.id.memory_end_timer);
        timerTextView.setText("Tu as complété le jeu en " + String.format("%d", minutes) + " minute(s) et "+ String.format("%d", sec) + " seconde(s).");


        // si il a coché memo modifie le score par rapport a la difficulté
        if (getIntent().getBooleanExtra("memo_key",true)){
            switch (getIntent().getStringExtra("difficulty_key")){
                case "facil":
                    if (user.getDiver_score_facil()!=-1){
                        if (user.getDiver_score_facil()>getIntent().getIntExtra("time_key",0)){ //Update le meilleur des deux score
                            user.setDiver_score_facil(getIntent().getIntExtra("time_key",0));
                        }
                    }
                    else{ // si le score = -1 (score non initalisé)
                        user.setDiver_score_facil(getIntent().getIntExtra("time_key",0));
                    }
                    break;
                case "normal":
                    if (user.getDiver_score_normal()!=-1){
                        if (user.getDiver_score_normal()>getIntent().getIntExtra("time_key",0)){    //Update le meilleur des deux score
                            user.setDiver_score_normal(getIntent().getIntExtra("time_key",0));
                        }
                    }
                    else{   // si le score = -1 (score non initalisé)
                        user.setDiver_score_normal(getIntent().getIntExtra("time_key",0));
                    }
                    break;
                case "difficil":
                    if (user.getDiver_score_difficil()!=-1){
                        if (user.getDiver_score_difficil()>getIntent().getIntExtra("time_key",0)) { //Update le meilleur des deux score
                            user.setDiver_score_difficil(getIntent().getIntExtra("time_key", 0));
                        }
                    }
                    else{   // si le score = -1 (score non initalisé)
                        user.setDiver_score_difficil(getIntent().getIntExtra("time_key", 0));
                    }
                    break;
            }
            updateUser();
        }

    }


    public void replay(View view) {
        // return home du jeu
        Intent memory_home_Intent = new Intent(this, memory_home.class);
        startActivity(memory_home_Intent);
        finish();
    }


    public void others_exercices(View view) {
        // return au main activity
        Intent mainActivityIntent = new Intent(this, com.example.projet.MainActivity.class);
        startActivity(mainActivityIntent);
        finish();
    }


    @Override
    public void onBackPressed() {
        Intent memory_home_Intent = new Intent(this, memory_home.class);
        startActivity(memory_home_Intent);
        finish();
    }


    private void updateUser() {

        class UpdateUser extends AsyncTask<Void, Void, User> {

            @Override
            protected User doInBackground(Void... voids) {
                mDb.getAppDatabase()
                        .userDao()
                        .updateUser(user);
                return user;
            }

        }
        //////////////////////////
        // IMPORTANT bien penser à executer la demande asynchrone
        // Création d'un objet de type UpdateUser et execution de la demande asynchrone
        UpdateUser gu = new UpdateUser();
        gu.execute();
    }

}
