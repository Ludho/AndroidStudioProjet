package com.example.projet.Maths_Activities.module3;

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

public class maths_module3_end extends Activity {
    public static final String CORRECT_KEY = "correct_key";
    public static final String NBCALCUL_KEY = "nbcalcul_key";
    public static final String DIFFICULTY_KEY = "difficulty_key";
    private User user;
    private DatabaseClient mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maths_module3_end);

        TextView nberror = findViewById(R.id.maths_module3_nb_error_timer);

        int nbcalcul = getIntent().getIntExtra("nbcalcul_key",0);   // get Intent nombre de calcul fait
        int calculcorr = getIntent().getIntExtra("correct_key",0);  // get Intent nombre de calcul correct
        String difficulte = getIntent().getStringExtra("difficulty_key");

        nberror.setText("Vous avez répondu juste à "+ calculcorr +" calculs sur " +  nbcalcul); // set text avec le nombre de calcul correct et de calcul fait

        // initialisation bd + current user
        mDb = DatabaseClient.getInstance(getApplicationContext());
        user = ((App) getApplication()).getUser();


        if (difficulte.equals("facile")){   // set le score du user par rapport à la difficulté
            if(user.getMaths_3_score_facile()<calculcorr){
                user.setMaths_3_score_facile(calculcorr);   // si c'est un nouveau record set nouveau score
            }
        }else if (difficulte.equals("normal")){
            if(user.getMaths_3_score_normale()<calculcorr){     // si c'est un nouveau record set nouveau score
                user.setMaths_3_score_normale(calculcorr);
            }
        }else{
            if(user.getMaths_3_score_difficile()<calculcorr) {  // si c'est un nouveau record set nouveau score
                user.setMaths_3_score_difficile(calculcorr);
            }
        }
        updateUser();
    }

    @Override
    public void onBackPressed() {
        Intent maths_module_3_home_Intent = new Intent(this, maths_module3_home.class);
        startActivity(maths_module_3_home_Intent);
        finish();
    }

    public void maths_module3_Correct(View view) {
        finish();
    } // implémenter ????????????????????????????????????????? le moi du future refuse

    public void maths_module3_others_exercices(View view) {
        Intent mainActivityIntent = new Intent(this, com.example.projet.MainActivity.class);
        startActivity(mainActivityIntent);
        finish();
    }

    public void maths_module3_replay(View view) {
        Intent maths_module_2_home_Intent = new Intent(this, maths_module3_home.class);
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
