package com.montaury.mus.jeu.joueur;


import java.util.ArrayList;
import java.util.List;
import com.montaury.mus.jeu.joueur.Joueur;

public class Equipe  {

    private final List<Joueur> joueurs;

    public Equipe (InterfaceJoueur interfaceJoueur, Joueur joueurHumain, boolean partieAQuatre ) {

        joueurs = new ArrayList<>();

        this.joueurs.add(joueurHumain);

        if (partieAQuatre) {
            Joueur joueurOrdi = Joueur.ordinateur();
            this.joueurs.add(joueurOrdi);
        }
    }
}
