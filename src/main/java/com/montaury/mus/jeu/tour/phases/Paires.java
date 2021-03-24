package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.equipe.Opposants;

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
    com.montaury.mus.jeu.carte.paires.Paires pairesJoueurEsku = opposants.joueurEsku().main().getPaires();
    com.montaury.mus.jeu.carte.paires.Paires pairesJoueurAllie = opposants.joueur2equipe1().main().getPaires();
    com.montaury.mus.jeu.carte.paires.Paires pairesJoueurEnemie = opposants.joueur1equipe2().main().getPaires();
    com.montaury.mus.jeu.carte.paires.Paires pairesJoueurZaku = opposants.joueurZaku().main().getPaires();

    com.montaury.mus.jeu.carte.paires.Paires pairesMeilleurEquipe1 = opposants.joueurEsku().main().getPaires();
    com.montaury.mus.jeu.carte.paires.Paires pairesMeilleurEquipe2 = opposants.joueurZaku().main().getPaires();
    Joueur meilleurJoueurEquipe1 =opposants.joueurEsku();
    Joueur meilleurJoueurEquipe2=opposants.joueurZaku();

    if (pairesJoueurEsku.estMeilleureOuEgaleA(pairesJoueurAllie)){
      pairesMeilleurEquipe1=pairesJoueurEsku;
    }else {
      pairesMeilleurEquipe1=pairesJoueurAllie;
      meilleurJoueurEquipe1=opposants.joueur2equipe1();
    }

    if (pairesJoueurZaku.estMeilleureOuEgaleA(pairesJoueurEnemie)){
      pairesMeilleurEquipe2=pairesJoueurZaku;
    }else {
      pairesMeilleurEquipe2=pairesJoueurEnemie;
      meilleurJoueurEquipe2=opposants.joueur1equipe2();
    }
    return pairesMeilleurEquipe1.estMeilleureOuEgaleA(pairesMeilleurEquipe2) ? meilleurJoueurEquipe1 : meilleurJoueurEquipe2;
  }

  @Override
  public int pointsBonus(Joueur vainqueur) {
    return vainqueur.main().getPaires().pointsBonus();
  }
}
