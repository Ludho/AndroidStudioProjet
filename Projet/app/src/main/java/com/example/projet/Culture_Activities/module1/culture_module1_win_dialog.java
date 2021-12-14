package com.example.projet.Culture_Activities.module1;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.projet.R;

public class culture_module1_win_dialog {

    private Context mContext;
    private Dialog correctDialog;

    private culture_module1_quizz mquizActivity;

    public culture_module1_win_dialog(Context mContext) {
        this.mContext = mContext;
    }

    public void correctDialog(int score,culture_module1_quizz quizActivity){
        // Initialisation de l'activité dans laquelle elle va apparaitre
        mquizActivity = quizActivity;

        // Initialisation d'une nouveau dialog
        correctDialog = new Dialog(mContext);
        correctDialog.setContentView(R.layout.culture_module1_win_dialog);

        Score(score);  //  calling method -> set text avec le score recu

        // Initialisation du boutton Ok du dialog + Ajout Onlick listener du boutton  du dialog
        final Button btcorrectDialog = (Button) correctDialog.findViewById(R.id.bt_Score_Dialog);
        btcorrectDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                correctDialog.dismiss();
                mquizActivity.questionSuivante();
            }
        });

        correctDialog.show();
        correctDialog.setCancelable(false);
        correctDialog.setCanceledOnTouchOutside(false);
        correctDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private void Score(int score) {
        TextView textScore = (TextView) correctDialog.findViewById(R.id.textView_Score);
        textScore.setText("Score: " + String.valueOf(score));
    }
}
