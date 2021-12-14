package com.example.projet.Maths_Activities.module2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.projet.R;

public class maths_module2_loser extends Activity {
    public static final String ERROR_KEY = "error_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maths_module_2_loser);

        // set le nombre d'érreur faite
        TextView nberror = findViewById(R.id.maths_module2_nb_error);
        nberror.setText("Nombre d'erreurs : "+ getIntent().getIntExtra("error_key",0));
    }

    public void maths_module2_Correct(View view) {
        finish();
        // retour correction
    }

    public void maths_module2_change(View view) {
        finish();
        Intent maths_module_2_home_Intent = new Intent(this, maths_module2_home.class);
        startActivity(maths_module_2_home_Intent);
    }

    public void maths_module2_others_exercices(View view) {
        finish();
        Intent mainActivityIntent = new Intent(this, com.example.projet.MainActivity.class);
        startActivity(mainActivityIntent);
    }

    @Override
    public void onBackPressed() {
        Intent mathsmodule2home = new Intent(this, maths_module2_home.class);
        startActivity(mathsmodule2home);
        finish();
    }
}
