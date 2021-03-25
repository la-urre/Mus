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
    int pointsJoueurEsku = opposants.joueur1().main().pointsPourJeu();
    int pointsJoueurAllie = opposants.joueur3().main().pointsPourJeu();
    int pointsJoueurEnemie = opposants.joueur2().main().pointsPourJeu();
    int pointsJoueurZaku = opposants.joueur4().main().pointsPourJeu();

    int pointsMeilleurJoueurEquipe1 = opposants.joueur1().main().pointsPourJeu();
    Joueur meilleurJoueurEquipe1 = opposants.joueur1();
    int pointsMeilleurJoueurEquipe2 = opposants.joueur4().main().pointsPourJeu();
    Joueur meilleurJoueurEquipe2 = opposants.joueur1();

    if (pointsJoueurAllie > pointsJoueurEsku){
      pointsMeilleurJoueurEquipe1=pointsJoueurAllie;
      meilleurJoueurEquipe1 = opposants.joueur2();
    }
    if (pointsJoueurEnemie > pointsJoueurZaku){
      pointsMeilleurJoueurEquipe2=pointsJoueurEnemie;
      meilleurJoueurEquipe2 = opposants.joueur3();
    }


    return pointsMeilleurJoueurEquipe1 >= pointsMeilleurJoueurEquipe2 ? meilleurJoueurEquipe1 : meilleurJoueurEquipe2;
  }

  @Override
  public int pointsBonus(Joueur vainqueur) {
    return 1;
  }
}
