package com.example.projet;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;

public class profile_pick_dialog {
    private Context mContext;
    private Dialog profile_dialog;
    private profile profile;
    private register register;

    profile_pick_dialog(Context mContext) {
        this.mContext = mContext;
    }


    void ProfileDialog(profile profile){ // dialog pour le profile
        this.profile = profile;
        profile_dialog = new Dialog(mContext);
        profile_dialog.setContentView(R.layout.profile_picture_dialog);

        final ImageView profile1 = profile_dialog.findViewById(R.id.profile1);  // get toutes les photos de profile de l'appli
        final ImageView profile2 = profile_dialog.findViewById(R.id.profile2);
        final ImageView profile3 = profile_dialog.findViewById(R.id.profile3);
        final ImageView profile4 = profile_dialog.findViewById(R.id.profile4);
        final ImageView profile5 = profile_dialog.findViewById(R.id.profile5);
        final ImageView profile6 = profile_dialog.findViewById(R.id.profile6);
        final ImageView profile7 = profile_dialog.findViewById(R.id.profile7);
        final ImageView profile8 = profile_dialog.findViewById(R.id.profile8);


        profile1.setOnClickListener(new View.OnClickListener() { // implemente tout les Onclick pour la selection des image
            @Override
            public void onClick(View v) {
                profile_dialog.dismiss();
                profile.setProfile("profile1");
            }
        });
        profile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_dialog.dismiss();
                profile.setProfile("profile2");
            }
        });
        profile3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_dialog.dismiss();
                profile.setProfile("profile3");
            }
        });
        profile4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_dialog.dismiss();
                profile.setProfile("profile4");
            }
        });
        profile5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_dialog.dismiss();
                profile.setProfile("profile5");
            }
        });
        profile6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_dialog.dismiss();
                profile.setProfile("profile6");
            }
        });
        profile7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_dialog.dismiss();
                profile.setProfile("profile7");
            }
        });
        profile8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_dialog.dismiss();
                profile.setProfile("profile8");
            }
        });

        profile_dialog.show();
        profile_dialog.setCancelable(false);
        profile_dialog.setCanceledOnTouchOutside(true);

        profile_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }






    void ProfileDialog(register register){ // dialog pour la page d'enregistrement
        this.register = register;
        profile_dialog = new Dialog(mContext);
        profile_dialog.setContentView(R.layout.profile_picture_dialog);

        final ImageView profile1 = profile_dialog.findViewById(R.id.profile1);
        final ImageView profile2 = profile_dialog.findViewById(R.id.profile2);
        final ImageView profile3 = profile_dialog.findViewById(R.id.profile3);
        final ImageView profile4 = profile_dialog.findViewById(R.id.profile4);
        final ImageView profile5 = profile_dialog.findViewById(R.id.profile5);
        final ImageView profile6 = profile_dialog.findViewById(R.id.profile6);
        final ImageView profile7 = profile_dialog.findViewById(R.id.profile7);
        final ImageView profile8 = profile_dialog.findViewById(R.id.profile8);


        profile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_dialog.dismiss();
                register.setProfile("profile1");
            }
        });
        profile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_dialog.dismiss();
                register.setProfile("profile2");
            }
        });
        profile3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_dialog.dismiss();
                register.setProfile("profile3");
            }
        });
        profile4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_dialog.dismiss();
                register.setProfile("profile4");
            }
        });
        profile5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_dialog.dismiss();
                register.setProfile("profile5");
            }
        });
        profile6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_dialog.dismiss();
                register.setProfile("profile6");
            }
        });
        profile7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_dialog.dismiss();
                register.setProfile("profile7");
            }
        });
        profile8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_dialog.dismiss();
                register.setProfile("profile8");
            }
        });

        profile_dialog.show();
        profile_dialog.setCancelable(false);
        profile_dialog.setCanceledOnTouchOutside(true);

        profile_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }
}
