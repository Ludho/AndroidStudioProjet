package com.example.projet.Culture_Activities.module1;

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

public class culture_module1_end extends Activity {
    public static final String CORRECT_KEY = "correct_key";
    public static final String SCORE_KEY = "score_key";
    public static final String THEME_KEY = "theme_key";
    private User user;
    private DatabaseClient mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.culture_module1_end);


        // set le text par rapport au score obtenue
        TextView nbcorrect = findViewById(R.id.culture_module1_nb_error_timer);     // TextView affiche nombre de réponses correctes
        TextView score = findViewById(R.id.culture_module1_score);                  // TextView affiche score
        score.setText("score : "+ getIntent().getIntExtra("score_key",0));  // set le score
        nbcorrect.setText("Vous avez répondu juste à "+ getIntent().getIntExtra("correct_key",0)+" question sur 10"); // set txt nb reponses corr


        //Initialisation bd + current user
        mDb = DatabaseClient.getInstance(getApplicationContext());
        user = ((App) getApplication()).getUser();



        // modifier current user score par rapport au theme choisis
        switch (getIntent().getStringExtra("theme_key")){
            case "francais":
                if (user.getCulture_1_score_francais()<getIntent().getIntExtra("score_key",0)){
                    user.setCulture_1_score_francais(getIntent().getIntExtra("score_key",0));   // si il a un score plus elever set le noveau score
                }
                break;
            case "geographie":
                if (user.getCulture_1_score_geographie()<getIntent().getIntExtra("score_key",0)){
                    user.setCulture_1_score_geographie(getIntent().getIntExtra("score_key",0)); // si il a un score plus elever set le noveau score
                }
                break;
            case "histoire":
                if (user.getCulture_1_score_histoire()<getIntent().getIntExtra("score_key",0)) {
                    user.setCulture_1_score_histoire(getIntent().getIntExtra("score_key", 0));  // si il a un score plus elever set le noveau score
                }
                break;
        }
        updateUser();
    }

    @Override
    public void onBackPressed() {
        Intent culture_module1_home_Intent = new Intent(this, culture_module1_home.class);
        startActivity(culture_module1_home_Intent);
    }

    public void culture_module1_replay(View view) {
        Intent culture_module1_home_Intent = new Intent(this, culture_module1_home.class);
        startActivity(culture_module1_home_Intent);
    }

    public void culture_module1_others_exercices(View view) {
        Intent mainActivityIntent = new Intent(this, com.example.projet.MainActivity.class);
        startActivity(mainActivityIntent);
    }

    private void updateUser() {


        // Instruction pour update le Current user
        class UpdateUser extends AsyncTask<Void, Void, User> {

            @Override
            protected User doInBackground(Void... voids) {
                mDb.getAppDatabase()
                        .userDao()
                        .updateUser(user);
                return user;
            }

        }

        // IMPORTANT bien penser à executer la demande asynchrone
        // Création d'un objet de type UpdateUser et execution de la demande asynchrone
        UpdateUser gu = new UpdateUser();
        gu.execute();
    }
}
