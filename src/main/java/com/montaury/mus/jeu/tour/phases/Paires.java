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
    opposants.joueurEsku().main().getPaires();

    Joueur meilleurJoueurEquipe1 = opposants.joueurEsku();
    Joueur meilleurJoueurEquipe2 = opposants.joueurZaku();

    if(peutParticiper(opposants.joueurEsku()) && peutParticiper(opposants.joueur2equipe1()))
    {
      //recup le meilleur
      if (opposants.joueurEsku().main().getPaires().estMeilleureOuEgaleA(opposants.joueur2equipe1().main().getPaires())){
        meilleurPaireEquie1=opposants.joueurEsku().main().getPaires();
      }else {
        meilleurPaireEquie1=opposants.joueur2equipe1().main().getPaires();
        meilleurJoueurEquipe1=opposants.joueur2equipe1();
      }
    }
    else if(peutParticiper(opposants.joueurEsku()))
    {
      //recup paire esku
      meilleurPaireEquie1 =opposants.joueurEsku().main().getPaires() ;
    }
    else
    {
      //recup paire j2e1
      meilleurPaireEquie1 =opposants.joueur2equipe1().main().getPaires() ;
      meilleurJoueurEquipe1=opposants.joueur2equipe1();
    }

    if(peutParticiper(opposants.joueurZaku()) && peutParticiper(opposants.joueur1equipe2()))
    {
      //recup le meilleur
      if (opposants.joueurZaku().main().getPaires().estMeilleureOuEgaleA(opposants.joueur2equipe1().main().getPaires())){
        meilleurPaireEquie2 =opposants.joueurZaku().main().getPaires();
      }else {
        meilleurPaireEquie2=opposants.joueur1equipe2().main().getPaires();
        meilleurJoueurEquipe2=opposants.joueur1equipe2();
      }
    }
    else if(peutParticiper(opposants.joueurZaku()))
    {
      //recup paire zaku
      meilleurPaireEquie2 =opposants.joueurZaku().main().getPaires() ;
    }
    else
    {
      //recup paire j1e2
      meilleurPaireEquie2 =opposants.joueur1equipe2().main().getPaires() ;
      meilleurJoueurEquipe2=opposants.joueur1equipe2();
    }

    return meilleurPaireEquie1.estMeilleureOuEgaleA(meilleurPaireEquie2) ? meilleurJoueurEquipe1 : meilleurJoueurEquipe2;
  }

  @Override
  public int pointsBonus(Joueur vainqueur) {
    return vainqueur.main().getPaires().pointsBonus();
  }
}
