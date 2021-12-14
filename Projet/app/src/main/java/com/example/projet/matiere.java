package com.example.projet;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.projet.model.App;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class matiere extends Fragment {

    String matiere;
    Button mathsButton;
    Button frenchButton;
    Button gKnowledgeButton;

    public matiere() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matiere, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    public void init(View view){
        matiere = ((App) getActivity().getApplication()).getMatiere();          // get matiere de l'application
        final NavController navController = Navigation.findNavController(view);
        mathsButton = view.findViewById(R.id.school_subject_maths);
        frenchButton = view.findViewById(R.id.school_subject_divers);
        gKnowledgeButton = view.findViewById(R.id.school_subject_G_knowledges);

        // initialise les couleurs des bouttons actif ou non
        updateColor();

        // initialise les clicks
        mathsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((App) getActivity().getApplication()).setMatiere("Maths");
                navController.navigate(R.id.navigation_exercices);
                updateColor();
            }
        });
        frenchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((App) getActivity().getApplication()).setMatiere("Divers");
                navController.navigate(R.id.navigation_exercices);
                updateColor();
            }
        });
        gKnowledgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((App) getActivity().getApplication()).setMatiere("Culture Général");
                navController.navigate(R.id.navigation_exercices);
                updateColor();
            }
        });
    }

    private void updateColor() {
        matiere = ((App) getActivity().getApplication()).getMatiere();  // change les couleurs des boutton en
        String actifButton = "#ff8b43";                                 // fonction de si elle est selectionné ou non
        String defaultButton = "#ff7b00";
        switch (matiere){
            case "Maths":
                mathsButton.setBackgroundColor(Color.parseColor(actifButton));
                frenchButton.setBackgroundColor(Color.parseColor(defaultButton));
                gKnowledgeButton.setBackgroundColor(Color.parseColor(defaultButton));
                break;
            case "Divers":
                mathsButton.setBackgroundColor(Color.parseColor(defaultButton));
                frenchButton.setBackgroundColor(Color.parseColor(actifButton));
                gKnowledgeButton.setBackgroundColor(Color.parseColor(defaultButton));
                break;
            case "Culture Général":
                mathsButton.setBackgroundColor(Color.parseColor(defaultButton));
                frenchButton.setBackgroundColor(Color.parseColor(defaultButton));
                gKnowledgeButton.setBackgroundColor(Color.parseColor(actifButton));
                break;
        }
    }

}