package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.equipe.Opposants;

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
    int pointsJoueurEsku = opposants.joueurEsku().main().pointsPourJeu();
    int pointsJoueurAllie = opposants.joueur1equipe2().main().pointsPourJeu();
    int pointsJoueurEnemie = opposants.joueur2equipe1().main().pointsPourJeu();
    int pointsJoueurZaku = opposants.joueurZaku().main().pointsPourJeu();

    int pointsMeilleurJoueurEquipe1 = opposants.joueurEsku().main().pointsPourJeu();
    Joueur meilleurJoueurEquipe1 = opposants.joueurEsku();
    int pointsMeilleurJoueurEquipe2 = opposants.joueurZaku().main().pointsPourJeu();
    Joueur meilleurJoueurEquipe2 = opposants.joueurEsku();

    if (pointsJoueurAllie > pointsJoueurEsku){
      pointsMeilleurJoueurEquipe1=pointsJoueurAllie;
      meilleurJoueurEquipe1 = opposants.joueur2equipe1();
    }
    if (pointsJoueurEnemie > pointsJoueurZaku){
      pointsMeilleurJoueurEquipe2=pointsJoueurEnemie;
      meilleurJoueurEquipe2 = opposants.joueur1equipe2();
    }


    return pointsMeilleurJoueurEquipe1 >= pointsMeilleurJoueurEquipe2 ? meilleurJoueurEquipe1 : meilleurJoueurEquipe2;
  }

  @Override
  public int pointsBonus(Joueur vainqueur) {
    return 1;
  }
}
