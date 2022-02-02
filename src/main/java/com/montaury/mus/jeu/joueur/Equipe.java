package com.montaury.mus.jeu.joueur;

import com.montaury.mus.console.InterfaceJoueurHumain;
import com.montaury.mus.jeu.carte.Carte;
import java.util.List;
import com.montaury.mus.jeu.joueur.Joueur;


public class Equipe {

    /*LES ATTRIBUTS*/
    private String nomEquipe;//nom de l'equipe en string
    private int identifiant;//identifiant de l'equipe etant egal a 1 ou 2
    private Joueur joueur1;//joueur numero 1 de l'equipe
    private Joueur joueur2;//joueur numero 2 de l'equipe

    /*CONSTRUCTEUR*/

    //constructeur demandant le joueur 1, le joueur 2, son nom et son identifiant qui vaut 1 ou 2
    public Equipe(Joueur j1, Joueur j2, String nom,int id)
    {
        joueur1=j1;
        joueur2=j2;
        nomEquipe=nom;
        identifiant=id;
    }

    /*METHODES*/

    //retourne le joueur 1 ou 2 celon le numero passé en paramétre
    //affiche une erreur si le numero n'est ni 1 ni 2
    public Joueur getJoueur(int numero) {
        if(numero==1)
        {
            return joueur1;
        }
        else if(numero==2)
        {
            return joueur2;
        }
        else
        {
            System.out.print("Erreur, joueur introuvable");
            return new Joueur("vide", new InterfaceJoueurHumain());
        }
    }
}
