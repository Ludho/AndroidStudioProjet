package com.example.projet.Diverse_Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

import com.example.projet.MainActivity;
import com.example.projet.R;


public class memory_home extends Activity {
    Intent startIntentMemory;
    ToggleButton memo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory_home);

        startIntentMemory = new Intent(this, memory_game.class);
        memo = findViewById(R.id.diver_memo);
    }



    public void others_exercices(View view) {
        Intent mainActivityIntent = new Intent(this, com.example.projet.MainActivity.class);
        startActivity(mainActivityIntent);
        finish();
    }



    public void startDifficil(View view) {  // set Onclick Button envoie diffuclté et memo vers activité du jeu
        if (memo.isChecked()){
            startIntentMemory.putExtra(memory_game.MEMO_KEY,true);
        }
        startIntentMemory.putExtra(memory_game.DIFFICULTY_KEY,"difficil");
        startIntentMemory.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(startIntentMemory);
    }

    public void startNormal(View view) {    // set Onclick Button envoie diffuclté et memo vers activité du jeu
        if (memo.isChecked()){
            startIntentMemory.putExtra(memory_game.MEMO_KEY,true);
        }
        startIntentMemory.putExtra(memory_game.DIFFICULTY_KEY,"normal");
        startIntentMemory.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(startIntentMemory);
    }

    public void startFacil(View view) { // set Onclick Button envoie diffuclté et memo vers activité du jeu
        if (memo.isChecked()){
            startIntentMemory.putExtra(memory_game.MEMO_KEY,true);
        }
        startIntentMemory.putExtra(memory_game.DIFFICULTY_KEY,"facil");
        startIntentMemory.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(startIntentMemory);
    }

    @Override
    public void onBackPressed() {
        Intent MainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(MainActivityIntent);
        finish();
    }
}
