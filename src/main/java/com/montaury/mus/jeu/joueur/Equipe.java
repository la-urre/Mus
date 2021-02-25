package com.montaury.mus.jeu.joueur;

public class Equipe {
    private Joueur joueurA ;
    private Joueur joueurB;

    public Equipe(Joueur joueurA,Joueur joueurB) {
        this.joueurA = joueurA;
        this.joueurB = joueurB;
    }

    public Joueur joueurA() {
        return joueurA;
    }

    public Joueur joueurB() {
        return joueurB;
    }



}
