package com.example.projet.Maths_Activities.module1;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.projet.model.App;
import com.example.projet.model.DatabaseClient;
import com.example.projet.model.User;

public class maths_module1_winner extends Activity {

    public static final String TABLE_KEY = "table_key";
    private User user;
    private DatabaseClient mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.projet.R.layout.maths_module1_winner_activity);

        // initalise bd + current user
        mDb = DatabaseClient.getInstance(getApplicationContext());
        user = ((App) getApplication()).getUser();

        // get le user score pour se module qui est un String 00010000, si l'indice i = 0 la table i n'est pas fait avec succes
        String score = user.getMaths_1_score();
        char[] scoreChars = score.toCharArray();
        scoreChars[getIntent().getIntExtra("table_key", 0)-1] = '1';  // table accompli on change le String score en initialisant avec 1
        score = String.valueOf(scoreChars);
        user.setMaths_1_score(score);
        // update user
        updateUser();
    }

    @Override
    public void onBackPressed() {
        Intent activity5Intent = new Intent(this, maths_module1_home.class);
        startActivity(activity5Intent);
        finish();
    }

    public void maths_module1_others_table(View view) {
        Intent maths_module_1_home_Intent = new Intent(this, maths_module1_home.class);
        startActivity(maths_module_1_home_Intent);
    }

    public void maths_module1_others_exercices(View view) {
        Intent mainActivityIntent = new Intent(this, com.example.projet.MainActivity.class);
        startActivity(mainActivityIntent);
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
