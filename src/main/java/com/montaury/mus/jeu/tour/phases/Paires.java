package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.joueur.Main;

public class Paires extends Phase {
  public Paires() {
    super("Paires");
  }

  @Override
  protected boolean peutParticiper(Main main) {
    return main.aDesPaires();
  }

  @Override
  protected boolean mainEskuEstMeilleure(Main mainJoueurEsku, Main mainJoueurZaku) {
    com.montaury.mus.jeu.carte.paires.Paires pairesJoueurEsku = mainJoueurEsku.getPaires();
    com.montaury.mus.jeu.carte.paires.Paires pairesJoueurZaku = mainJoueurZaku.getPaires();
    return pairesJoueurEsku.estMeilleureOuEgaleA(pairesJoueurZaku);
  }

  @Override
  public int pointsBonus(Joueur vainqueur) {
    return vainqueur.main().getPaires().pointsBonus();
  }
}
