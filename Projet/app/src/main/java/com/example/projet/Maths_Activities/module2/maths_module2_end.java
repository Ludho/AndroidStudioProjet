package com.example.projet.Maths_Activities.module2;

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

public class maths_module2_end extends Activity {
    public static final String CORRECT_KEY = "correct_key";
    public static final String NBCALCUL_KEY = "nbcalcul_key";
    private User user;
    private DatabaseClient mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maths_module2_end);

        TextView nberror = findViewById(R.id.maths_module2_nb_error_timer);
        int calculcorr = getIntent().getIntExtra("correct_key",0);      // get le nombre de réponse correct
        int nbcalcul = getIntent().getIntExtra("nbcalcul_key",0);       // get le nombre de calcul fait

        nberror.setText("Vous avez répondu juste à "+ calculcorr +" calculs sur " + nbcalcul );     // set txt par rapport au reponse et caulcul faite

        // initialise bd + current user
        mDb = DatabaseClient.getInstance(getApplicationContext());
        user = ((App) getApplication()).getUser();

        if (user.getMaths_2_score()<getIntent().getIntExtra("correct_key",0)){ // si il a fait un nouveau score
            user.setMaths_2_score(getIntent().getIntExtra("correct_key",0));       // initalise ce score
        }


        updateUser();
    }

    @Override
    public void onBackPressed() {
        Intent maths_module_2_home_Intent = new Intent(this, maths_module2_home.class);
        startActivity(maths_module_2_home_Intent);
        finish();
    }

    public void maths_module2_Correct(View view) {
        finish();
    } // implémenter ????????????????????????????????????????? le moi du future refuse

    public void maths_module2_others_exercices(View view) {
        Intent mainActivityIntent = new Intent(this, com.example.projet.MainActivity.class);
        startActivity(mainActivityIntent);
        finish();
    }

    public void maths_module2_replay(View view) {
        Intent maths_module_2_home_Intent = new Intent(this, maths_module2_home.class);
        startActivity(maths_module_2_home_Intent);
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
