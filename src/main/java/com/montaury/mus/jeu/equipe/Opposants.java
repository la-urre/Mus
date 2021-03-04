package com.montaury.mus.jeu.equipe;

import com.montaury.mus.jeu.equipe.Equipe;
import com.montaury.mus.jeu.joueur.Joueur;

import java.util.Iterator;

import java.util.List;

public class Opposants {
  private Joueur joueurEsku;
  private Joueur joueurZaku;
  

  public Opposants(Equipe joueurEsku, Equipe joueurZaku) {
    this.joueurEsku = joueurEsku.joueur1;
    this.joueurZaku = joueurZaku.joueur2;
  }
  // permet de changer celui qui distribue et celui qui parle en premier
  public void tourner() {
    Joueur Joueurtamp = joueurZaku;
    joueurZaku = joueurEsku;
    joueurEsku = Joueurtamp;
  }

  public Joueur joueurEsku() {
    return joueurEsku;
  }

  public Joueur joueurZaku() {
    return joueurZaku;
  }

  public Iterator<Joueur> itererDansLOrdre() { //permet de passer d'un Joueur a l autre pour avoir ses données
    return new IteratorInfini(this);
  }

  public List<Joueur> dansLOrdre() {
    return List.of(joueurEsku, joueurZaku);
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
      suivant = suivant == opposants.joueurEsku ? opposants.joueurZaku : opposants.joueurEsku;
      return next;
    }
  }
}
