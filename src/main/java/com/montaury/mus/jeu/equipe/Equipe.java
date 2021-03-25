package com.montaury.mus.jeu.equipe;

import com.montaury.mus.jeu.Manche;
import com.montaury.mus.jeu.joueur.Joueur;

public class Equipe {
    //attribut
   public Joueur joueur1;
   public Joueur joueur2;

    //constructeur
    public Equipe(Joueur j1, Joueur j2)
    {
        joueur1 = j1;
        joueur2 = j2;
    }
    //methodes

    public Joueur getJoueur1() {
        return joueur1;
    }

    public Joueur getJoueur2() {
        return joueur2;
    }


}
