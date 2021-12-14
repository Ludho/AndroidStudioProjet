package com.example.projet.Maths_Activities.module3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.projet.MainActivity;
import com.example.projet.R;

public class maths_module3_loser extends Activity {
    public static final String ERROR_KEY = "error_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maths_module_3_loser);
        TextView nberror = findViewById(R.id.maths_module3_nb_error);
        nberror.setText("Nombre d'erreurs : "+ getIntent().getIntExtra("error_key",0));
    }

    public void maths_module3_Correct(View view) {
        finish();
    }

    public void maths_module3_change(View view) {
        finish();
        Intent maths_module_3_home_Intent = new Intent(this, maths_module3_home.class);
        startActivity(maths_module_3_home_Intent);
    }
    @Override
    public void onBackPressed() {
        Intent mathsmodule3home = new Intent(this, maths_module3_home.class);
        startActivity(mathsmodule3home);
        finish();
    }

    public void maths_module3_others_exercices(View view) {
        Intent MainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(MainActivityIntent);
        finish();
    }
}
