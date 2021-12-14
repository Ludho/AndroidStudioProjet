package com.example.projet;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import com.example.projet.model.DatabaseClient;
import com.example.projet.model.User;


public class register extends Activity {

    private String profile="profile1";
    private DatabaseClient mDb;
    EditText editNom;
    EditText editPrenom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);


        // initialisation de la bd
        mDb = DatabaseClient.getInstance(getApplicationContext());


        // Initialise la photo de profile
        ImageView profile_picture = findViewById(R.id.register_profile);
        profile_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {    // appelle le dialog pour changer le profile
                profile_pick_dialog profile_pick_dialog = new profile_pick_dialog(register.this);
                profile_pick_dialog.ProfileDialog(register.this);
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void login(View view) {
        finish();
    }

    public void create(View view) {
        editNom = findViewById(R.id.editTextNomRegister);
        editPrenom = findViewById(R.id.editTextPrenomRegister);
        if (!TextUtils.isEmpty(editNom.getText()) &&  !TextUtils.isEmpty(editPrenom.getText())){        // Get les selections txt
            addUser();      // ajout d'un nouveau user dans la bd
            Intent moveToLogin = new Intent(this, login.class);
            startActivity(moveToLogin);
            finish();
        }
        else{
            Toast.makeText(this,"Veuillez entrer un nom et un prénom",Toast.LENGTH_SHORT).show();   // si il en manque envoyer un message
        }

    }

    public void setProfile(String profile) {
        // set le profile select apres avoir choisis
        this.profile=profile;
        int resId = getResources().getIdentifier(profile, "drawable", getPackageName());
        ImageView img = findViewById(R.id.register_profile);
        img.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),resId));
    }

    private void addUser() {

        class AddUser extends AsyncTask<Void, Void, User> {

            @Override
            protected User doInBackground(Void... voids) {
                String nom = editNom.getText().toString().trim();
                String prenom = editPrenom.getText().toString().trim();
                User user = new User(nom,prenom);
                user.setPicture(profile);

                // adding to database
                mDb.getAppDatabase()
                        .userDao()
                        .insertUser(user);

                return user;
            }

        }

        //////////////////////////
        // IMPORTANT bien penser à executer la demande asynchrone
        // Création d'un objet de type GetTasks et execution de la demande asynchrone
        AddUser au = new AddUser();
        au.execute();
    }

}