package com.example.projet.Culture_Activities.module1;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.projet.R;

public class culture_module1_loose_dialog {
    private Context mContext;
    private Dialog wrongAnswerDialog;

    private culture_module1_quizz mquizActivity;


    culture_module1_loose_dialog(Context mContext) {
        this.mContext = mContext;
    }

    void WrongDialog(String correctAnswer,culture_module1_quizz quizActivity){
        // Initialisation de l'activité dans laquelle elle va apparaitre
        mquizActivity = quizActivity;

        // Initialisation d'une nouveau dialog
        wrongAnswerDialog = new Dialog(mContext);
        wrongAnswerDialog.setContentView(R.layout.culture_module1_loose_dialog);

        // Initialisation du TextView du dialog + ajout de la reponse correct
        TextView textView = (TextView) wrongAnswerDialog.findViewById(R.id.textView_Correct_Answer);
        textView.setText("réponse : " + correctAnswer);

        // Initialisation du boutton Ok du dialog + Ajout Onlick listener du boutton  du dialog
        final Button btwrongAnswerDialog = (Button) wrongAnswerDialog.findViewById(R.id.bt_wrongDialog);
        btwrongAnswerDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                wrongAnswerDialog.dismiss();
                mquizActivity.questionSuivante();
            }
        });

        wrongAnswerDialog.show();
        wrongAnswerDialog.setCancelable(false);
        wrongAnswerDialog.setCanceledOnTouchOutside(false);

        wrongAnswerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }
}
