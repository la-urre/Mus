package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.joueur.Opposants;

public class Paires extends Phase {
  public Paires() {
    super("Paires");
  }

  @Override
  protected boolean peutParticiper(Joueur joueur) {
    return joueur.main().aDesPaires();
  }

  @Override
  protected Joueur meilleurParmi(Opposants opposants) {
    Joueur meilleur = opposants.joueurEsku();
    for (Joueur j:opposants.dansLOrdre()) {
      if (j.main().getPaires().estMeilleureOuEgaleA(meilleur.main().getPaires())) meilleur = j;
    }
    if (opposants.joueurEsku().main().getPaires().estMeilleureOuEgaleA(meilleur.main().getPaires())) meilleur = opposants.joueurEsku();
    return meilleur;
  }

  @Override
  public int pointsBonus(Joueur vainqueur) {
    return vainqueur.main().getPaires().pointsBonus();
  }
}
