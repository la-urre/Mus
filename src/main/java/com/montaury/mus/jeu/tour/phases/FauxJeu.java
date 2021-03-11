package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.joueur.Opposants;

import static com.montaury.mus.jeu.tour.phases.Jeu.aLeJeu;

public class FauxJeu extends Phase {
  public FauxJeu() {
    super("Faux Jeu");
  }

  @Override
  protected boolean peutParticiper(Joueur joueur) {
    return !aLeJeu(joueur);
  }

  @Override
  protected Joueur meilleurParmi(Opposants opposants) {
    Joueur meilleur = opposants.joueurEsku();
    for (Joueur j:opposants.dansLOrdre()) {
      if (j.main().pointsPourJeu() >= meilleur.main().pointsPourJeu()) meilleur = j;
    }
    if (opposants.joueurEsku().main().pointsPourJeu() >= meilleur.main().pointsPourJeu())meilleur = opposants.joueurEsku();
    return meilleur;
  }

  @Override
  public int pointsBonus(Joueur vainqueur) {
    return 1;
  }
}
