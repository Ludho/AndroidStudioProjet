package com.example.projet.Maths_Activities.module2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.projet.R;


public class maths_module2_winner extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maths_module2_win);
    }

    public void maths_module2_replay(View view) {
        finish();
        Intent maths_module_2_home_Intent = new Intent(this, maths_module2_home.class);
        startActivity(maths_module_2_home_Intent);
    }

    public void maths_module2_others_exercices(View view) {
        Intent mainActivityIntent = new Intent(this, com.example.projet.MainActivity.class);
        startActivity(mainActivityIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent mathsmodule2home = new Intent(this, maths_module2_home.class);
        startActivity(mathsmodule2home);
        finish();
    }
}
