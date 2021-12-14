package com.example.projet.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Entity
public class Question implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String question;
    private String reponse1;
    private String reponse2;
    private String reponse3;
    private String bonneReponse;
    private String theme;


    public Question(String question, String reponse1, String reponse2, String reponse3, String bonneReponse,String theme){
        this.question=question;
        this.reponse1=reponse1;
        this.reponse2=reponse2;
        this.reponse3=reponse3;
        this.bonneReponse=bonneReponse;
        this.theme=theme;
    }

    public String getTheme() {
        return theme;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public String getReponse1() {
        return reponse1;
    }

    public String getReponse2() {
        return reponse2;
    }

    public String getReponse3() {
        return reponse3;
    }

    public String getBonneReponse() {
        return bonneReponse;
    }
}