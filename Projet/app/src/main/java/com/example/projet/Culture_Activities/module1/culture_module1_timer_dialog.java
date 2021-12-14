package com.example.projet.Culture_Activities.module1;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.projet.R;

public class culture_module1_timer_dialog {
    private Context mContext;
    private Dialog timerDialog;
    private culture_module1_quizz mquizActivity;

    culture_module1_timer_dialog(Context mContext) {
        this.mContext = mContext;
    }

    public void timerDialog(String correctAnswer, culture_module1_quizz quizActivity){
        // Initialisation de l'activité dans laquelle elle va apparaitre
        mquizActivity = quizActivity;

        // Initialisation d'une nouveau dialog
        timerDialog = new Dialog(mContext);
        timerDialog.setContentView(R.layout.culture_module1_loosetime_dialog);

        // Initialisation du TextView du dialog + ajout de la reponse correct
        TextView textView = (TextView) timerDialog.findViewById(R.id.textView_Correct_Answer);
        textView.setText("réponse : " + correctAnswer);

        // Initialisation du boutton Ok du dialog + Ajout Onlick listener du boutton  du dialog
        final Button bttimerDialog = (Button) timerDialog.findViewById(R.id.bt_timerDialog);
        bttimerDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timerDialog.dismiss();
                mquizActivity.questionSuivante();

            }
        });

        timerDialog.show();
        timerDialog.setCancelable(false);
        timerDialog.setCanceledOnTouchOutside(false);
        timerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
