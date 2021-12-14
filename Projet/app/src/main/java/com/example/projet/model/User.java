package com.example.projet.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class User implements Serializable {



    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String prenom;
    private String nom;
    private String picture;

    // score
    private String maths_1_score = "000000000";
    private int maths_2_score = 0;
    private boolean maths_3_normal = false;
    private boolean maths_3_hard = false;
    private int maths_3_score_facile = 0;
    private int maths_3_score_normale = 0;
    private int maths_3_score_difficile = 0;
    private int culture_1_score_histoire = 0;
    private int culture_1_score_geographie = 0;
    private int culture_1_score_francais = 0;
    private int diver_score_facil=-1;
    private int diver_score_normal=-1;
    private int diver_score_difficil=-1;


    public User(String nom, String prenom){
        this.nom=nom;
        this.prenom=prenom;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }



    public int getMaths_2_score() {
        return maths_2_score;
    }
    public void setMaths_2_score(int maths_2_score) {
        this.maths_2_score = maths_2_score;
    }

    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getCulture_1_score_histoire() {
        return culture_1_score_histoire;
    }

    public void setCulture_1_score_histoire(int culture_1_score_histoire) {
        this.culture_1_score_histoire = culture_1_score_histoire;
    }

    public int getCulture_1_score_geographie() {
        return culture_1_score_geographie;
    }

    public int getCulture_1_score_francais() {
        return culture_1_score_francais;
    }

    public void setCulture_1_score_francais(int culture_1_score_francais) {
        this.culture_1_score_francais = culture_1_score_francais;
    }

    public void setCulture_1_score_geographie(int culture_1_score_geographie) {
        this.culture_1_score_geographie = culture_1_score_geographie;
    }


    public String getMaths_1_score() {
        return maths_1_score;
    }

    public void setMaths_1_score(String maths_1_score) {
        this.maths_1_score = maths_1_score;
    }

    public boolean isMaths_3_normal() {
        return maths_3_normal;
    }

    public void setMaths_3_normal(boolean maths_3_normal) {
        this.maths_3_normal = maths_3_normal;
    }

    public boolean isMaths_3_hard() {
        return maths_3_hard;
    }

    public void setMaths_3_hard(boolean maths_3_hard) {
        this.maths_3_hard = maths_3_hard;
    }

    public int getMaths_3_score_facile() {
        return maths_3_score_facile;
    }

    public void setMaths_3_score_facile(int maths_3_score_facile) {
        this.maths_3_score_facile = maths_3_score_facile;
    }

    public int getMaths_3_score_normale() {
        return maths_3_score_normale;
    }

    public void setMaths_3_score_normale(int maths_3_score_normale) {
        this.maths_3_score_normale = maths_3_score_normale;
    }

    public int getMaths_3_score_difficile() {
        return maths_3_score_difficile;
    }

    public void setMaths_3_score_difficile(int maths_3_score_difficile) {
        this.maths_3_score_difficile = maths_3_score_difficile;
    }

    public int getDiver_score_facil() {
        return diver_score_facil;
    }

    public void setDiver_score_facil(int diver_score_facil) {
        this.diver_score_facil = diver_score_facil;
    }

    public int getDiver_score_normal() {
        return diver_score_normal;
    }

    public void setDiver_score_normal(int diver_score_normal) {
        this.diver_score_normal = diver_score_normal;
    }

    public int getDiver_score_difficil() {
        return diver_score_difficil;
    }

    public void setDiver_score_difficil(int diver_score_difficil) {
        this.diver_score_difficil = diver_score_difficil;
    }
}
