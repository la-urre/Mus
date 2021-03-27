package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.carte.ValeurCarte;
import com.montaury.mus.jeu.joueur.Main;

import java.util.List;

import static com.montaury.mus.jeu.carte.ValeurCarte.Comparaison.PLUS_GRANDE;
import static com.montaury.mus.jeu.carte.ValeurCarte.Comparaison.PLUS_PETITE;

public class Petit extends Phase {
  public Petit() {
    super("Petit");
  }

  @Override
  protected boolean mainEskuEstMeilleure(Main mainJoueurEsku, Main mainJoueurZaku) {
    List<Carte> cartesJoueurEsku = mainJoueurEsku.cartesDuPlusGrandAuPlusPetit();
    List<Carte> cartesJoueurZaku = mainJoueurZaku.cartesDuPlusGrandAuPlusPetit();

    for (int i = Main.TAILLE - 1; i >= 0; i--) {
      ValeurCarte.Comparaison compare = cartesJoueurEsku.get(i).comparerAvec(cartesJoueurZaku.get(i));
      if (compare == PLUS_PETITE) {
        return true;
      }
      if (compare == PLUS_GRANDE) {
        return false;
      }
    }
    return true;
  }
}
