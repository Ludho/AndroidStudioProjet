package com.example.projet;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.projet.model.App;
import com.example.projet.model.DatabaseClient;
import com.example.projet.model.User;

import java.util.List;

public class login extends AppCompatActivity{

    private DatabaseClient mDb;
    List<User> allUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialisation de la base de donnée
        mDb = DatabaseClient.getInstance(getApplicationContext());

        // get Tout les users de l'appareil
        getUsers();
        setContentView(R.layout.login_activity);

    }

    public void setUsersVue(){

        // creation des profile user
        LinearLayout linear = findViewById(R.id.users_vue);
        for (User user : allUsers){         // pour tout les user dans la bd crée une linearLayout
            RelativeLayout linearTMP = (RelativeLayout) getLayoutInflater().inflate(R.layout.user_template_layout, null);
            // set image ici
            ImageView og = linearTMP.findViewById(R.id.user_image_template);
            og.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),getResources().getIdentifier(user.getPicture(), "drawable",getPackageName())));   //set l'image profile du user present dans la bd
            TextView user_name = linearTMP.findViewById(R.id.template_user_name);
            user_name.setText(user.getNom() + " " + user.getPrenom());      // set le nom du user

            linearTMP.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Intent mainActivityIntent = new Intent(v.getContext(), MainActivity.class);
                    ((App) getApplication()).setUser(user);     // OnClick set Current User, le profile cliqué
                    startActivity(mainActivityIntent);          // commencé l'activité
                    finish();
                }
            });
            linear.addView(linearTMP);
        }
    }

    public void register(View view){
        // aller dans la vue pour enregistrer un compte
        Intent registerActivityIntent = new Intent(this, com.example.projet.register.class);
        registerActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(registerActivityIntent);
    }


    private void getUsers() {

        class GetUsers extends AsyncTask<Void, Void, List<User>> {

            @Override
            protected List<User> doInBackground(Void... voids) {
                List<User> usersList = mDb.getAppDatabase()
                        .userDao()
                        .getAllusers();     // return tout les user de la bd
                return usersList;
            }

            @Override
            protected void onPostExecute(List<User> users) {
                super.onPostExecute(users);

                // Mettre à jour l'adapter avec la liste de taches
                allUsers = users;
                setUsersVue();
            }
        }

        //////////////////////////
        // IMPORTANT bien penser à executer la demande asynchrone
        // Création d'un objet de type GetTasks et execution de la demande asynchrone
        GetUsers gu = new GetUsers();
        gu.execute();
    }

}