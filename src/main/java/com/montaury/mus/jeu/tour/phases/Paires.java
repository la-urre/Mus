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

    com.montaury.mus.jeu.carte.paires.Paires meilleurPaireEquie1;
    com.montaury.mus.jeu.carte.paires.Paires meilleurPaireEquie2;
    opposants.joueur1().main().getPaires();

    Joueur meilleurJoueurEquipe1 = opposants.joueur1();
    Joueur meilleurJoueurEquipe2 = opposants.joueur4();

    if(peutParticiper(opposants.joueur1()) && peutParticiper(opposants.joueur2()))
    {
      //recup le meilleur
      if (opposants.joueur1().main().getPaires().estMeilleureOuEgaleA(opposants.joueur2().main().getPaires())){
        meilleurPaireEquie1=opposants.joueur1().main().getPaires();
      }else {
        meilleurPaireEquie1=opposants.joueur2().main().getPaires();
        meilleurJoueurEquipe1=opposants.joueur2();
      }
    }
    else if(peutParticiper(opposants.joueur1()))
    {
      //recup paire esku
      meilleurPaireEquie1 =opposants.joueur1().main().getPaires() ;
    }
    else
    {
      //recup paire j2e1
      meilleurPaireEquie1 =opposants.joueur2().main().getPaires() ;
      meilleurJoueurEquipe1=opposants.joueur2();
    }

    if(peutParticiper(opposants.joueur4()) && peutParticiper(opposants.joueur3()))
    {
      //recup le meilleur
      if (opposants.joueur4().main().getPaires().estMeilleureOuEgaleA(opposants.joueur2().main().getPaires())){
        meilleurPaireEquie2 =opposants.joueur4().main().getPaires();
      }else {
        meilleurPaireEquie2=opposants.joueur3().main().getPaires();
        meilleurJoueurEquipe2=opposants.joueur3();
      }
    }
    else if(peutParticiper(opposants.joueur4()))
    {
      //recup paire zaku
      meilleurPaireEquie2 =opposants.joueur4().main().getPaires() ;
    }
    else
    {
      //recup paire j1e2
      meilleurPaireEquie2 =opposants.joueur3().main().getPaires() ;
      meilleurJoueurEquipe2=opposants.joueur3();
    }

    return meilleurPaireEquie1.estMeilleureOuEgaleA(meilleurPaireEquie2) ? meilleurJoueurEquipe1 : meilleurJoueurEquipe2;
  }

  @Override
  public int pointsBonus(Joueur vainqueur) {
    return vainqueur.main().getPaires().pointsBonus();
  }
}
