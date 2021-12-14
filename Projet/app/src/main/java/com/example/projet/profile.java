package com.example.projet;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.projet.data.UserDAO;
import com.example.projet.model.App;
import com.example.projet.model.DatabaseClient;
import com.example.projet.model.User;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class profile extends Fragment {
    private User user;
    private UserDAO userDao;
    private ImageView profile;
    private DatabaseClient mDb;
    public profile() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate le  layout pour ce fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // get current users et bd
        user = ((App) getActivity().getApplication()).getUser();
        mDb = DatabaseClient.getInstance(getActivity().getApplicationContext());

        // Initialise la photo de profile par rapport au user actuel
        profile = view.findViewById(R.id.profile_picture);
        profile.setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(),getResources().getIdentifier(user.getPicture(), "drawable", getActivity().getPackageName())));
        profile.setOnClickListener(new View.OnClickListener() { // si il click sur la photo
            @Override
            public void onClick(View view) {                    // dialog pour changer la photo

                profile_pick_dialog profile_pick_dialog = new profile_pick_dialog(profile.this.getActivity());
                profile_pick_dialog.ProfileDialog(profile.this);
            }
        });

        // image tropher pour aller dans la page score de l'utilisateur
        ImageView trophy = view.findViewById(R.id.trophy);
        trophy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent succesActivity = new Intent(getContext(), score.class); // aller dans la page score de l'utilisateur
                startActivity(succesActivity);
            }
        });

        return view;
    }



    public void setProfile(String profile) {
        // update user avec nouvelle photo selectionné
        user.setPicture(profile);
        setProfileUser();
        int resId = getResources().getIdentifier(profile, "drawable", getActivity().getPackageName());
        this.profile.setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(),resId));
    }


    private void setProfileUser() {

        class SetProfileUser extends AsyncTask<Void, Void, User> {

            @Override
            protected User doInBackground(Void... voids) {
                mDb.getAppDatabase()
                        .userDao()
                        .updateUser(user);
                return user;
            }

        }

        //////////////////////////
        // IMPORTANT bien penser à executer la demande asynchrone
        // Création d'un objet de type GetTasks et execution de la demande asynchrone
        SetProfileUser gu = new SetProfileUser();
        gu.execute();
    }

}