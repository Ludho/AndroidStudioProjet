package com.example.projet;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;

public class delete_account_dialog {
    private Context mContext;
    private Dialog deleteDialog;

    private MainActivity mainActivity;


    delete_account_dialog(Context mContext) {
        this.mContext = mContext;
    }

    void DeleteDialog(MainActivity mainActivity){

        this.mainActivity = mainActivity;
        deleteDialog = new Dialog(mContext);
        deleteDialog.setContentView(R.layout.delete_account_dialog);

        final Button btnOui = (Button) deleteDialog.findViewById(R.id.bt_oui_delete);
        final Button btnNon = (Button) deleteDialog.findViewById(R.id.bt_non_delete);

        btnOui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog.dismiss(); // fermer le dialog
                mainActivity.delete();  // appelle la fonction delete de l'activité pour supprimer le compte
            }
        });

        btnNon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog.dismiss();
            }   // fermer le dialog
        });

        deleteDialog.show();
        deleteDialog.setCancelable(false);
        deleteDialog.setCanceledOnTouchOutside(false);

        deleteDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }
}
