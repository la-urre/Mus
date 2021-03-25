package com.montaury.mus;

import com.montaury.mus.jeu.Partie;
import com.montaury.mus.jeu.equipe.Equipe;
import com.montaury.mus.jeu.joueur.AffichageConsoleEvenementsDeJeu;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.equipe.Opposants;
import java.util.Scanner;

public class Mus {
  public static void main(String[] args) {
    System.out.print("Entrez votre nom: ");
    String nomJoueur = new Scanner(System.in).next();
    Joueur humain = Joueur.humain(nomJoueur);

    Partie partie = new Partie(new AffichageConsoleEvenementsDeJeu(humain));
    Partie.Resultat resultat = partie.jouer(new Opposants(  new Equipe( humain, Joueur.ordinateur("allié") ),
                                                            new Equipe(Joueur.ordinateur("enemie 1"),Joueur.ordinateur("enemie 2"))));

    System.out.println("Le vainqueur de la partie est " + resultat.vainqueur().nom());
  }
}
