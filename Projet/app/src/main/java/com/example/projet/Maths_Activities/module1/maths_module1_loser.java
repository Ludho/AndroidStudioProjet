package com.example.projet.Maths_Activities.module1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.projet.R;


public class maths_module1_loser extends Activity {
    public static final String ERROR_KEY = "error_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maths_module1_loser_activity);
        // initalise nberror et la vue associer
        TextView nberror = findViewById(R.id.maths_module1_nb_error);
        nberror.setText("Nombre d'erreurs : "+ getIntent().getIntExtra("error_key",0));
    }

    public void maths_module1_Other_table(View view) {
        // boutton autre table retour au homme du module
        Intent activity5Intent = new Intent(this, maths_module1_home.class);
        startActivity(activity5Intent);
        finish();
    }

    public void maths_module1_Correct(View view) {
        // correction retour a la vue de l'exercice
        finish();
    }

    public void maths_module1_others_exercices(View view) {
        finish();
        Intent mainActivityIntent = new Intent(this, com.example.projet.MainActivity.class);
        startActivity(mainActivityIntent);
    }

    @Override
    public void onBackPressed() {
        Intent activity5Intent = new Intent(this, maths_module1_home.class);
        startActivity(activity5Intent);
        finish();
    }
}
