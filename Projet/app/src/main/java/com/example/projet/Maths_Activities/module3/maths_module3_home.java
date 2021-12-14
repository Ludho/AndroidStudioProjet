package com.example.projet.Maths_Activities.module3;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.core.content.ContextCompat;

import com.example.projet.MainActivity;
import com.example.projet.Maths_Activities.module2.maths_module2_calcul;
import com.example.projet.Maths_Activities.module2.maths_module2_calcul_timer;
import com.example.projet.R;
import com.example.projet.model.App;
import com.example.projet.model.User;

public class maths_module3_home extends Activity {

    private String difficulte = "facile";
    private Button facile;
    private Button normale;
    private Button difficile;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(com.example.projet.R.layout.maths_module3_home);
        init();
        updateColor();
    }

    private void updateColor() {
        String actifButton = "#ff8b43";
        String defaultButton = "#ff7b00";

        // update couleur des boutton par rapport a celui selectionné
        switch (difficulte){
            case "facile":
                facile.setBackgroundColor(Color.parseColor(actifButton));
                normale.setBackgroundColor(Color.parseColor(defaultButton));
                difficile.setBackgroundColor(Color.parseColor(defaultButton));
                break;
            case "normal":
                facile.setBackgroundColor(Color.parseColor(defaultButton));
                normale.setBackgroundColor(Color.parseColor(actifButton));
                difficile.setBackgroundColor(Color.parseColor(defaultButton));
                break;
            case "difficile":
                facile.setBackgroundColor(Color.parseColor(defaultButton));
                normale.setBackgroundColor(Color.parseColor(defaultButton));
                difficile.setBackgroundColor(Color.parseColor(actifButton));
                break;
        }
    }

    private void init() {
        user = ((App) getApplication()).getUser();
        facile = findViewById(R.id.maths_module3_facile); // boutton facile
        normale = findViewById(R.id.maths_module3_normale); // boutton normal
        difficile = findViewById(R.id.maths_module3_difficile); // boutton difficile


        // si le user a debloqué le niveau normal
        if (user.isMaths_3_normal()){

            // cache Image verrou
            ImageView lock = findViewById(R.id.maths_module3_lock_normal);
            lock.setVisibility(View.GONE);

            // ajoute on click pour pouvoir selectionner le niveau
            normale.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    maths_module3_home.this.difficulte="normal";
                    updateColor();
                }
            });
        }else{
            // sinon prevenir que le niveau n'est toujours pas debloqué
            normale.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(maths_module3_home.this,"Débloque cette difficulté en complétant la difficulté facile",Toast.LENGTH_SHORT).show();
                }
            });
        }


        // si le user a debloqué le niveau hard
        if (user.isMaths_3_hard()){

            // cache Image verrou
            ImageView lock = findViewById(R.id.maths_module3_lock_hard);
            lock.setVisibility(View.GONE);

            // ajoute on click pour pouvoir selectionner le niveau
            difficile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    maths_module3_home.this.difficulte="difficile";
                    updateColor();
                }
            });

        }else{
            // sinon prevenir que le niveau n'est toujours pas debloqué
            difficile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(maths_module3_home.this,"Débloque cette difficulté en complétant la difficulté normal",Toast.LENGTH_SHORT).show();
                }
            });
        }


        // par default le niveau facile est accessible
        facile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maths_module3_home.this.difficulte="facile";
                updateColor();
            }
        });

    }



    public void others_exercices(View view) {
        finish();
        Intent MainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(MainActivityIntent);
    }


    public void maths_module3_valider_commencer(View view) {
        ToggleButton timer = findViewById(R.id.maths_module3_timer);        // si le toggle timer est press commence l'ctivité avec le timer
        if (timer.isChecked()){
            Intent calculTimerActivityIntent = new Intent(this, maths_module3_calcul_timer.class);
            calculTimerActivityIntent.putExtra(maths_module3_calcul_timer.DIFFICULTY_KEY,difficulte);
            calculTimerActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(calculTimerActivityIntent);
            finish();
        }else{
            Intent calculActivityIntent = new Intent(this, maths_module3_calcul.class);
            calculActivityIntent.putExtra(maths_module3_calcul.DIFFICULTY_KEY,difficulte);
            calculActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(calculActivityIntent);
            finish();
        }
    }



    @Override
    public void onBackPressed() {
        Intent MainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(MainActivityIntent);
        finish();
    }

}
