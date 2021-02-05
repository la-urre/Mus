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
    com.montaury.mus.jeu.carte.paires.Paires pairesJoueurEsku = opposants.joueurEsku().main().getPaires();
    com.montaury.mus.jeu.carte.paires.Paires pairesJoueurDeux = opposants.joueurDeux().main().getPaires();
    com.montaury.mus.jeu.carte.paires.Paires pairesJoueurTrois = opposants.joueurTrois().main().getPaires();
    com.montaury.mus.jeu.carte.paires.Paires pairesJoueurZaku = opposants.joueurZaku().main().getPaires();

    com.montaury.mus.jeu.carte.paires.Paires[] tabPaire ={pairesJoueurDeux,pairesJoueurTrois,pairesJoueurZaku} ;
    com.montaury.mus.jeu.carte.paires.Paires meilleurPaire= opposants.joueurEsku().main().getPaires();

    for(int i = 0 ; i < 2 ; i++) {
      if (!meilleurPaire.estMeilleureOuEgaleA(tabPaire[i])) {
        meilleurPaire = tabPaire[i];
      }
    }




    return pairesJoueurEsku.estMeilleureOuEgaleA(pairesJoueurZaku) ? opposants.joueurEsku() : opposants.joueurZaku();
  }

  @Override
  public int pointsBonus(Joueur vainqueur) {
    return vainqueur.main().getPaires().pointsBonus();
  }
}
