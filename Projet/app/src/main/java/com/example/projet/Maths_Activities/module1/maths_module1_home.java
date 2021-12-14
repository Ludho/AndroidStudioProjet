package com.example.projet.Maths_Activities.module1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

import com.example.projet.MainActivity;

public class maths_module1_home extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(com.example.projet.R.layout.maths_module1_home);

        // Initalise le number picker de 9 à 1 pour la selection des tables
        NumberPicker picker1 = findViewById(com.example.projet.R.id.maths_module1_choix_table);
        picker1.setMaxValue(9);
        picker1.setMinValue(1);
    }


    // valider renvoie le picker pris (table) et commence l'exercice
    public void maths_module1_valider_table(View view) {
        NumberPicker picker1 = findViewById(com.example.projet.R.id.maths_module1_choix_table);
        Intent tableActivityIntent = new Intent(this, maths_module1_table.class);
        tableActivityIntent.putExtra(maths_module1_table.TABLE_KEY, picker1.getValue());
        tableActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(tableActivityIntent);
    }


    public void maths_module1_others_exercices(View view) {
        Intent MainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(MainActivityIntent);
        finish();
    }


    @Override
    public void onBackPressed() {
        Intent MainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(MainActivityIntent);
        finish();
    }
}
