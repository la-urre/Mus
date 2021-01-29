package com.montaury.mus;

import com.montaury.mus.jeu.Partie;
import com.montaury.mus.jeu.joueur.AffichageConsoleEvenementsDeJeu;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.joueur.Opposants;
import java.util.Scanner;

public class Mus {
  public static void main(String[] args) {
    //Affichage de la demande du nom de joueur pour l'utilisateur
    System.out.print("Entrez votre nom: ");

    //Reception du nom de joueur
    String nomJoueur = new Scanner(System.in).next();

    //creation du joueur de type humain
    Joueur humain = Joueur.humain(nomJoueur);

    //creation d'une nouvelle partie
    Partie partie = new Partie(new AffichageConsoleEvenementsDeJeu(humain));

    Partie.Resultat resultat = partie.jouer(new Opposants(humain, Joueur.ordinateur()));

    System.out.println("Le vainqueur de la partie est " + resultat.vainqueur().nom());
  }
}
