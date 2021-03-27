package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.joueur.Main;
import com.montaury.mus.jeu.joueur.Opposants;

import static com.montaury.mus.jeu.tour.phases.Jeu.aLeJeu;

public class FauxJeu extends Phase {
  public FauxJeu() {
    super("Faux Jeu");
  }

  @Override
  protected boolean peutParticiper(Main main) {
    return !aLeJeu(main);
  }

  @Override
  protected Joueur meilleurParmi(Opposants opposants) {
    int pointsJoueurEsku = opposants.joueurEsku().main().pointsPourJeu();
    int pointsJoueurZaku = opposants.joueurZaku().main().pointsPourJeu();
    return pointsJoueurEsku >= pointsJoueurZaku ? opposants.joueurEsku() : opposants.joueurZaku();
  }

  @Override
  public int pointsBonus(Joueur vainqueur) {
    return 1;
  }
}
