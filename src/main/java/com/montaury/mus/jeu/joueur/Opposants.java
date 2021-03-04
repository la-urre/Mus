package com.montaury.mus.jeu.joueur;

import java.util.Iterator;
import java.util.List;

public class Opposants {
 private Equipe equipeHumain ;
 private Equipe equipeOrdinateur;
 private Joueur joueurEsku;
 private Joueur joueurDeux;
 private Joueur joueurTrois;
 private Joueur joueurZaku;

  public Opposants(Joueur joueurHumain,Joueur joueurOrdinateurEquipeHumain, Joueur joueurOrdinateurEquipeOrdinateur, Joueur joueurOrdinateur2EquipeOrdinateur) {
  this.equipeHumain= new Equipe (joueurHumain,joueurOrdinateurEquipeHumain,"Humain");
    this.equipeOrdinateur = new Equipe (joueurOrdinateurEquipeOrdinateur,joueurOrdinateur2EquipeOrdinateur,"Ordinateur");
    this.joueurEsku = equipeHumain.joueurA();
    this.joueurDeux = equipeOrdinateur.joueurA();
    this.joueurTrois = equipeHumain.joueurB();
    this.joueurZaku = equipeOrdinateur.joueurB();
  }

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

  public Equipe equipeHumain(){return equipeHumain;}

  public Equipe equipeOrdinateur(){return equipeOrdinateur;}


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
