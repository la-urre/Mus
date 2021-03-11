package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.carte.ValeurCarte;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.joueur.Main;
import com.montaury.mus.jeu.joueur.Opposants;
import java.util.List;

import static com.montaury.mus.jeu.carte.ValeurCarte.Comparaison.PLUS_GRANDE;
import static com.montaury.mus.jeu.carte.ValeurCarte.Comparaison.PLUS_PETITE;

public class Petit extends Phase {
  public Petit() {
    super("Petit");
  }

  @Override
  protected Joueur meilleurParmi(Opposants opposants) {
    Joueur meilleur = opposants.joueurEsku();
    for (Joueur j:opposants.dansLOrdre()) {
      for (int i = Main.TAILLE - 1; i >= 0; i--) {
        ValeurCarte.Comparaison compare = meilleur.main().cartesDuPlusGrandAuPlusPetit().get(i).comparerAvec(j.main().cartesDuPlusGrandAuPlusPetit().get(i));
        if (compare == PLUS_PETITE) {
          meilleur = j;
          break;
        }
        if (compare == PLUS_GRANDE) {
          continue;
        }
      }
    }
    for (int i = Main.TAILLE - 1; i >= 0; i--) {
      ValeurCarte.Comparaison compare = opposants.joueurEsku().main().cartesDuPlusGrandAuPlusPetit().get(i).comparerAvec(meilleur.main().cartesDuPlusGrandAuPlusPetit().get(i));
      if (compare == PLUS_PETITE) {
        return opposants.joueurEsku();
      }
      if (compare == PLUS_GRANDE) {
        return meilleur;
      }
    }

    return meilleur;

  }
}
