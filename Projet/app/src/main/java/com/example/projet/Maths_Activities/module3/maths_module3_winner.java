package com.example.projet.Maths_Activities.module3;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.projet.MainActivity;
import com.example.projet.R;
import com.example.projet.data.UserDAO;
import com.example.projet.model.App;
import com.example.projet.model.DatabaseClient;
import com.example.projet.model.User;

public class maths_module3_winner extends Activity {

    private User user;
    private UserDAO userDao;
    private DatabaseClient mDb;

    public static final String DIFFICULTY_KEY = "difficulty_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maths_module3_win);

        mDb = DatabaseClient.getInstance(getApplicationContext());
        userDao = mDb.getAppDatabase().userDao();
        user = ((App) getApplication()).getUser();
        if (getIntent().getStringExtra("difficulty_key").equals("facile")){
            user.setMaths_3_normal(true);
        }else if(getIntent().getStringExtra("difficulty_key").equals("normal")){
            user.setMaths_3_hard(true);
        }
        updateUser();
    }

    public void maths_module3_replay(View view) {
        finish();
        Intent maths_module_3_home_Intent = new Intent(this, maths_module3_home.class);
        startActivity(maths_module_3_home_Intent);
    }

    public void maths_module3_others_exercices(View view) {
        Intent MainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(MainActivityIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent mathsmodule3home = new Intent(this, maths_module3_home.class);
        startActivity(mathsmodule3home);
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
        // Création d'un objet de type GetTasks et execution de la demande asynchrone
        UpdateUser gu = new UpdateUser();
        gu.execute();
    }
}
