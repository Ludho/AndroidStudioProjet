package com.example.projet.Culture_Activities.module1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.projet.R;

public class culture_module1_home extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.culture_module1_home);

    }


    // Methode OnClick boutton -> Itent l'activité quiz avec le theme
    public void BouttonHistoire(View view) {
        Intent quizzActivityIntent = new Intent(this, culture_module1_quizz.class);
        quizzActivityIntent.putExtra(culture_module1_quizz.THEME_KEY,"histoire");
        quizzActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(quizzActivityIntent);
    }



    // Methode OnClick boutton -> Itent l'activité quiz avec le theme
    public void BouttonFrancais(View view) {
        Intent quizzActivityIntent = new Intent(this, culture_module1_quizz.class);
        quizzActivityIntent.putExtra(culture_module1_quizz.THEME_KEY,"francais");
        quizzActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(quizzActivityIntent);
    }



    // Methode OnClick boutton -> Itent l'activité quiz avec le theme
    public void BouttonGeographie(View view) {
        Intent quizzActivityIntent = new Intent(this, culture_module1_quizz.class);
        quizzActivityIntent.putExtra(culture_module1_quizz.THEME_KEY,"geographie");
        quizzActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(quizzActivityIntent);
    }



    public void culture_module1_others_exercices(View view) {
        // retour a l'activité Main
        Intent MainActivity = new Intent(this, com.example.projet.MainActivity.class);
        startActivity(MainActivity);
    }

    @Override
    public void onBackPressed() {
        // retour a l'activité Main
        Intent MainActivity = new Intent(this, com.example.projet.MainActivity.class);
        startActivity(MainActivity);
    }
}
