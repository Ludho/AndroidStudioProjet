package com.example.projet;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.projet.model.App;
import com.example.projet.model.DatabaseClient;
import com.example.projet.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity{

    protected String matiere = "Maths"; //default
    protected User user;
    private DatabaseClient mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialisé la menu du bas
        BottomNavigationView navView = findViewById(R.id.bottomNav_view);


        //pass les Id a toute les destination
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_matiere, R.id.navigation_exercices, R.id.navigation_profile)
                .build();

        //Initialise Le controlleur de la navigation
        NavController navController = Navigation.findNavController(this, R.id.navHostFragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        // Initialisation de la bd
        mDb = DatabaseClient.getInstance(getApplicationContext());
        user = ((App) getApplication()).getUser();

        setNav(); // set les navigation personnalisé
    }

    public void setNav(){

        // Initialise la nav
        BottomNavigationView navView = findViewById(R.id.bottomNav_view);
        NavController navController = Navigation.findNavController(this, R.id.navHostFragment);

        navView.findViewById(R.id.navigation_exercices).setOnClickListener(new View.OnClickListener() { // si on click sur exercice,
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.navigation_exercices);                                      //  aller sur exercice
            }
        });
        navView.findViewById(R.id.navigation_matiere).setOnClickListener(new View.OnClickListener() {   // si on click sur matiere,
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.navigation_matiere);                                        // aller sur matiere
            }
        });
        navView.findViewById(R.id.navigation_profile).setOnClickListener(new View.OnClickListener() {   // si on clock sur profile
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.navigation_profile);                                        // aller sur profile
                if (user!=null){
                    getSupportActionBar().setTitle("Profile de " + user.getNom()+ " " + user.getPrenom()); // afficher le nom prenom du current user
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
    }

    public void delete() {
        deleteUser(user);   // call delete User qui supprime de la db le current user
        Intent loginActivityIntent = new Intent(this, com.example.projet.login.class); // retour page login
        startActivity(loginActivityIntent);
        finish();
    }

    public void disconnect(View view) {
        Intent loginActivityIntent = new Intent(this, com.example.projet.login.class);  // deconnection retour page login
        startActivity(loginActivityIntent);
        finish();
    }

    public void deleteDialog(View view){
        delete_account_dialog delete_account_dialog = new delete_account_dialog(this);
        delete_account_dialog.DeleteDialog(this);
    }

    private void deleteUser(User usertodelete) {

        class DeleteUser extends AsyncTask<Void, Void, User> {

            @Override
            protected User doInBackground(Void... voids) {      //supprime de la db le current user
                        mDb.getAppDatabase()
                        .userDao()
                        .deleteUser(usertodelete);
                return usertodelete;
            }
        }

        //////////////////////////
        // IMPORTANT bien penser à executer la demande asynchrone
        // Création d'un objet de type GetTasks et execution de la demande asynchrone
        DeleteUser ac = new DeleteUser();
        ac.execute();
    }

}
