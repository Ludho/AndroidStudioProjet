package com.example.projet.Maths_Activities.module1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.projet.R;
import java.util.ArrayList;
import java.util.List;
import com.example.projet.Maths_Activities.Calcul;
import com.example.projet.Maths_Activities.TableDeMultiplication;

public class maths_module1_table extends Activity {

    // table de multiplication
    private TableDeMultiplication tableDeMultiplication;
    private List<EditText> reponses = new ArrayList<EditText>();
    public static final String TABLE_KEY = "table_key";
    public int table;

    // réponse avant correction
    private ArrayList<ImageView> correction = new ArrayList<ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maths_module1_table);

        // get la table
        table = getIntent().getIntExtra("table_key", 0);

        // init la table avec la table selectionner
        TextView tableTxt = findViewById(R.id.module1_math_table);
        tableTxt.setText("Table de multiplication de " + table); // set le texte de la table actuelle
        initTable();
    }


    // init la table avec la table selectionner
    public void initTable(){
        // creation de la table par rapport à celle selectionné
        tableDeMultiplication = new TableDeMultiplication(table);

        // Initialisation du layout dans lequelle on va ajouter les calculs
        LinearLayout linear = findViewById(R.id.table_vue);
        //  pour toutes les calcul on crée le layout avec la template calcul
        for (Calcul multiplication : tableDeMultiplication.getMultiplications()){

            LinearLayout linearTMP = (LinearLayout) getLayoutInflater().inflate(R.layout.maths_module1_template_calcul, null);


            // set l'expression du calcul
            TextView calcul = (TextView) linearTMP.findViewById(R.id.template_calcul);
            calcul.setText(multiplication.getOperande1()+ " x "+ multiplication.getOperande2()+ " = ");
            EditText resusltat = (EditText) linearTMP.findViewById(R.id.template_resultat);


            // creation d'un array des reponses que l'ont va tapé
            this.reponses.add(resusltat);


            // creation d'un array d'image que l'ont va afficher quand on demande une correction
            ImageView img = linearTMP.findViewById(R.id.image_template_maths);
            this.correction.add(img);


            // ajoute le layout calcul crée
            linear.addView(linearTMP);
            }

        }

    @Override
    public void onBackPressed() {
        Intent activity5Intent = new Intent(this, maths_module1_home.class);
        startActivity(activity5Intent);
        finish();
    }



    public void maths_module1_valider_reponses(View view) {

        int nberror = 0;
        int i=1; // undice du calcul

        // Calcul le nombre d'erreur fait
        for (EditText reponse : reponses){
            if(!TextUtils.isEmpty(reponse.getText())){  // si l'entrer à été rempli

                // si l'entrer tapé correpond pas a la réponse du calcul erreur ++
                if (Integer.parseInt(String.valueOf(reponse.getText()))!= i*getIntent().getIntExtra("table_key", 0)){
                    nberror++;
                    // affiche indication d'erreur
                    correction.get(i-1).setVisibility(View.VISIBLE);
                    correction.get(i-1).setImageDrawable(getResources().getDrawable( R.drawable.wrong32 ));
                }

                // si reponse est juste
                else{
                    // affiche indication de réussite
                    correction.get(i-1).setVisibility(View.VISIBLE);
                    correction.get(i-1).setImageDrawable(getResources().getDrawable( R.drawable.right32 ));
                }

            }
            // si rien n'a été rempli
            else{
                nberror++;
                // affiche indication d'erreur
                correction.get(i-1).setVisibility(View.VISIBLE);
                correction.get(i-1).setImageDrawable(getResources().getDrawable( R.drawable.wrong32 ));
            }
            i++;
        }


        // si y'a des erreur Intent -> loser Activity
        if (nberror>0){
            Intent loserIntent = new Intent(this, com.example.projet.Maths_Activities.module1.maths_module1_loser.class);
            loserIntent.putExtra(com.example.projet.Maths_Activities.module1.maths_module1_loser.ERROR_KEY, nberror);
            startActivity(loserIntent);
        }
        else{ // sinon Winner activity
            Intent winnerIntent = new Intent(this, com.example.projet.Maths_Activities.module1.maths_module1_winner.class);
            winnerIntent.putExtra(com.example.projet.Maths_Activities.module1.maths_module1_winner.TABLE_KEY, getIntent().getIntExtra("table_key", 0));
            startActivity(winnerIntent);
        }
    }
}
