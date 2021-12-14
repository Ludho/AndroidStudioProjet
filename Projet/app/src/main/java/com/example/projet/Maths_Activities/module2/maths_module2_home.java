package com.example.projet.Maths_Activities.module2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.ToggleButton;
import com.example.projet.MainActivity;
import com.example.projet.R;


public class maths_module2_home extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.projet.R.layout.maths_module2_home);

        // initialise le number picker
        // demande au user si il veut des addition/soustraction ou les deux
        NumberPicker NumberPicker = (NumberPicker) findViewById(R.id.maths_module2_choix_operand);
        String[] operand = {"-","+","±"};
        NumberPicker.setMaxValue(0);
        NumberPicker.setMaxValue(2);
        NumberPicker.setValue(1);
        NumberPicker.setDisplayedValues(operand);
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent MainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(MainActivityIntent);
    }

    public void onCheckboxClicked(View view) {
        // OPERAND 1
        CheckBox c1 = findViewById(R.id.maths_module2_int1); // Nombre a 3 chiffres
        CheckBox c2 = findViewById(R.id.maths_module2_int2);  // Nombre a 2 chiffres
        CheckBox c3 = findViewById(R.id.maths_module2_int3);    // defauly Nombre a 1 chiffres

        // OPERAND 2
        CheckBox c4 = findViewById(R.id.maths_module2_int4);    // Nombre a 3 chiffres
        CheckBox c5 = findViewById(R.id.maths_module2_int5);    // Nombre a 2 chiffres
        CheckBox c6 = findViewById(R.id.maths_module2_int6);    // defauly Nombre a 1 chiffres

        switch(view.getId()) { // gere les Onclick des Check box
            case R.id.maths_module2_int1:
                if (c1.isChecked()){            // si nombre à 3 chiffre, alors aussi à 2 chiffres
                    c2.setChecked(true);
                    c3.setChecked(true);
                }
                break;
            case R.id.maths_module2_int2:
                if (c2.isChecked()){
                    c3.setChecked(true);
                }
                if (c1.isChecked()){            // si nombre à 2 chiffre, alors aussi à 1 chiffres
                    c2.setChecked(true);
                    c3.setChecked(true);
                }
                break;
            case R.id.maths_module2_int3:
                c3.setChecked(true);
                break;
            case R.id.maths_module2_int4:       // si nombre à 3 chiffre, alors aussi à 2 chiffres
                if (c4.isChecked()){
                    c5.setChecked(true);
                    c6.setChecked(true);
                }
                break;
            case R.id.maths_module2_int5:
                if (c5.isChecked()){
                    c6.setChecked(true);
                }
                if (c4.isChecked()){           // si nombre à 2 chiffre, alors aussi à 1 chiffres
                    c5.setChecked(true);
                    c6.setChecked(true);
                }
                break;
            case R.id.maths_module2_int6:
                c6.setChecked(true);
                break;

        }
    }

    public void maths_module2_valider_commencer(View view) {
        CheckBox c1 = findViewById(R.id.maths_module2_int1);
        CheckBox c2 = findViewById(R.id.maths_module2_int2);
        CheckBox c4 = findViewById(R.id.maths_module2_int4);
        CheckBox c5 = findViewById(R.id.maths_module2_int5);
        NumberPicker picker = findViewById(R.id.maths_module2_choix_operand);
        ToggleButton timer = findViewById(R.id.maths_module2_timer);

        // envoie dans les intent les checkbox et debut de l'activité
        if (timer.isChecked()){
            Intent calculTimerActivityIntent = new Intent(this, maths_module2_calcul_timer.class);
            calculTimerActivityIntent.putExtra(maths_module2_calcul_timer.OPERAND_KEY,picker.getValue());
            calculTimerActivityIntent.putExtra(maths_module2_calcul_timer.NUM1_KEY,c1.isChecked());
            calculTimerActivityIntent.putExtra(maths_module2_calcul_timer.NUM2_KEY,c2.isChecked());
            calculTimerActivityIntent.putExtra(maths_module2_calcul_timer.NUM4_KEY,c4.isChecked());
            calculTimerActivityIntent.putExtra(maths_module2_calcul_timer.NUM5_KEY,c5.isChecked());
            calculTimerActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(calculTimerActivityIntent);
            finish();
        }else{
            Intent calculActivityIntent = new Intent(this, maths_module2_calcul.class);
            calculActivityIntent.putExtra(maths_module2_calcul.OPERAND_KEY,picker.getValue());
            calculActivityIntent.putExtra(maths_module2_calcul.NUM1_KEY,c1.isChecked());
            calculActivityIntent.putExtra(maths_module2_calcul.NUM2_KEY,c2.isChecked());
            calculActivityIntent.putExtra(maths_module2_calcul.NUM4_KEY,c4.isChecked());
            calculActivityIntent.putExtra(maths_module2_calcul.NUM5_KEY,c5.isChecked());
            calculActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(calculActivityIntent);
            finish();
        }
    }

    public void maths_module2_others_exercices(View view) {
        finish();
        Intent MainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(MainActivityIntent);
    }
}
