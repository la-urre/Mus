package com.montaury.mus.jeu.joueur;

import java.util.Iterator;
import java.util.List;

public class Opposants {
 private Equipe equipeHumain ;
 private Equipe equipeOrdinateur;

  public Opposants(Joueur joueurHumain,Joueur joueurOrdinateurEquipeHumain, Joueur joueurOrdinateurEquipeOrdinateur, Joueur joueurOrdinateur2EquipeOrdinateur) {
  this.equipeHumain= new Equipe (joueurHumain,joueurOrdinateurEquipeHumain);
    this.equipeOrdinateur = new Equipe (joueurOrdinateurEquipeOrdinateur,joueurOrdinateur2EquipeOrdinateur);
  }


  private Joueur joueurEsku = equipeHumain.joueurA();
  private Joueur joueurDeux = equipeOrdinateur.joueurA();
  private Joueur joueurTrois =equipeHumain.joueurB();
  private Joueur joueurZaku = equipeOrdinateur.joueurB();

  public void tourner() {
    Joueur tmp = joueurEsku;
    joueurEsku = joueurDeux;
    joueurDeux = joueurTrois ;
    joueurTrois = joueurZaku ;
    joueurZaku = tmp;
  }


  //Getter
  public Joueur joueurEsku() {
    return joueurEsku;
  }

  public Joueur joueurDeux() {
    return joueurDeux;
  }

  public Joueur joueurTrois() {
    return joueurTrois;
  }

  public Joueur joueurZaku() {
    return joueurZaku;
  }


  public Iterator<Joueur> itererDansLOrdre() {
    return new IteratorInfini(this);
  }

  public List<Joueur> dansLOrdre() {
    return List.of(joueurEsku, joueurDeux,joueurTrois,joueurZaku);
  }

  private static class IteratorInfini implements Iterator<Joueur> {
    private final Opposants opposants;
    private Joueur suivant;

    public IteratorInfini(Opposants opposants) {
      this.opposants = opposants;
      suivant = opposants.joueurEsku;
    }

    @Override
    public boolean hasNext() {
      return true;
    }

    @Override
    public Joueur next() {
      Joueur next = suivant;
      if (opposants.equipeOrdinateur.joueurA().equals(suivant)) {
        suivant = opposants.joueurDeux;
      } else if (opposants.joueurDeux.equals(suivant)) {
        suivant = opposants.joueurTrois;
      } else if (opposants.joueurTrois.equals(suivant)) {
        suivant = opposants.joueurZaku;
      } else if (opposants.joueurZaku.equals(suivant)) {
        suivant = opposants.joueurEsku;
      }

      return next;
    }
  }
}
