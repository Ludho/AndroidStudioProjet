package com.example.projet;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.projet.model.App;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link exercices#newInstance} factory method to
 * create an instance of this fragment.
 */
public class exercices extends Fragment {
    private Button btnMaths1;   // button table de multiplication
    private Button btnMaths2;   // button calcul Addition/soustraction
    private Button btnCulture1; // button quizz
    private Button btnMaths3;   // button Multiplication/division
    private Button btnDivers1;  // button jeu memo
    String matiere;     // matiere actuelle selectionné
    public exercices() {
        // Required empty public constructor
    }

    public static exercices newInstance() {
        exercices fragment = new exercices();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        matiere = ((App) getActivity().getApplication()).getMatiere();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exercices, container, false);
        init(view);  // initialise les bouttons
        update(view, matiere);
        return view;
    }

    public void init(View view){
        TextView txt = view.findViewById(R.id.matiere_choisis);
        txt.setText(matiere);
        btnMaths1 = (Button) view.findViewById(R.id.maths_module1); //set OnClick pour Start l'activité de l'exercice
        btnMaths1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent tableActivityIntent = new Intent(getActivity(), com.example.projet.Maths_Activities.module1.maths_module1_home.class);
                tableActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(tableActivityIntent);
            }
        });
        btnMaths2 = (Button) view.findViewById(R.id.maths_module2); //set OnClick pour Start l'activité de l'exercice
        btnMaths2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent culculActivityIntent = new Intent(getActivity(), com.example.projet.Maths_Activities.module2.maths_module2_home.class);
                culculActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(culculActivityIntent);
            }
        });
        btnCulture1 = (Button) view.findViewById(R.id.culture_module1); //set OnClick pour Start l'activité de l'exercice
        btnCulture1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent culculActivityIntent = new Intent(getActivity(), com.example.projet.Culture_Activities.module1.culture_module1_home.class);
                culculActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(culculActivityIntent);
            }
        });
        btnMaths3 = (Button) view.findViewById(R.id.maths_module3); //set OnClick pour Start l'activité de l'exercice
        btnMaths3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent multiActivityIntent = new Intent(getActivity(), com.example.projet.Maths_Activities.module3.maths_module3_home.class);
                multiActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(multiActivityIntent);
            }
        });
        btnDivers1 = (Button) view.findViewById(R.id.Divers_1);     //set OnClick pour Start l'activité de l'exercice
        btnDivers1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent memoActivityIntent = new Intent(getActivity(), com.example.projet.Diverse_Activities.memory_home.class);
                memoActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(memoActivityIntent);
            }
        });
        update(view, "Maths");
    }

    public void update(View view,String matiere){  // update les boutton en fonction de la matiere selectionné
        btnMaths1.setVisibility(View.GONE);
        btnMaths2.setVisibility(View.GONE);
        btnCulture1.setVisibility(View.GONE);
        btnMaths3.setVisibility(View.GONE);
        btnDivers1.setVisibility(View.GONE);
        switch (matiere){
            case "Maths":
                btnMaths1.setVisibility(View.VISIBLE);
                btnMaths2.setVisibility(View.VISIBLE);
                btnMaths3.setVisibility(View.VISIBLE);
                break;
            case "Culture Général":
                btnCulture1.setVisibility(View.VISIBLE);
                break;
            case "Divers":
                btnDivers1.setVisibility(View.VISIBLE);
                break;
        }
    }

}