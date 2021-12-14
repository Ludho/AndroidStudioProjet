package com.example.projet.model;

import android.app.Application;

public class App extends Application {
    private User user;
    private String matiere = "Maths";

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
