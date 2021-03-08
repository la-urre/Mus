package com.montaury.mus.jeu;

import com.montaury.mus.jeu.joueur.Joueur;

public class Equipe {
    private String nomEquipe;
    private Joueur joueurUn;

    public static Equipe equipeHumain(String nomEquipe, String nomJoueur)
    {
        return new Equipe(nomEquipe, Joueur.humain(nomJoueur));
    }

    public static Equipe equipeOdinateurs()
    {
        return new Equipe("Equipe ordinateurs", Joueur.ordinateur());
    }

    public Equipe(String nomE, Joueur joueur1)
    {
        this.nomEquipe = nomE;
        this.joueurUn = joueur1;
    }

    public Joueur getJoueurUn() {
        return joueurUn;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }
}
