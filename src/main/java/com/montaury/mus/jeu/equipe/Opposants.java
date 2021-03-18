package com.montaury.mus.jeu.equipe;

import com.montaury.mus.jeu.joueur.Joueur;

import java.util.Iterator;

import java.util.List;

public class Opposants {
  private Equipe equipe1;
  private Equipe equipe2;
  

  public Opposants(Equipe equipe1, Equipe equipe2) {
    this.equipe1 = equipe1;
    this.equipe2 = equipe2;
  }
  // permet de changer celui qui distribue et celui qui parle en premier
  public void tourner() {
    Equipe equipeTemp = equipe2;
    equipe2 = equipe1;
    equipe1 = equipeTemp;
  }

  public Joueur joueurEsku() {
    return equipe1.joueur1;
  }
  public Joueur joueur2equipe1() {
    return equipe1.joueur2;
  }
  public Joueur joueur1equipe2() {
    return equipe2.joueur1;
  }
  public Joueur joueurZaku() {
    return equipe2.joueur2;
  }

  public Iterator<Joueur> itererDansLOrdre() { //permet de passer d'un Joueur a l autre pour avoir ses données
    return new IteratorInfini(this);
  }

  public List<Joueur> dansLOrdre() {
    return List.of(equipe1.joueur1, equipe2.joueur1, equipe1.joueur2, equipe2.joueur2);
  }

  private static class IteratorInfini implements Iterator<Joueur> {
    private final Opposants opposants;
    private Joueur suivant;

    public IteratorInfini(Opposants opposants) {
      this.opposants = opposants;
      suivant = opposants.equipe1.joueur1;
    }

    @Override
    public boolean hasNext() {
      return true;
    }

    @Override
    public Joueur next() {
      Joueur next = suivant;
      if (opposants.equipe1.joueur1 == next)
      {
        suivant = opposants.equipe2.joueur1;
      }

      else if (opposants.equipe2.joueur1 == next)
      {
        suivant = opposants.equipe1.joueur2;
      }

      else if (opposants.equipe1.joueur2 == next)
      {
        suivant = opposants.equipe2.joueur2;
      }

      else if (opposants.equipe2.joueur2 == next)
      {
        suivant = opposants.equipe1.joueur1;
      }

      // suivant = suivant == opposants.joueurEsku.joueur1 ? opposants.joueurZaku.joueur1 : opposants.joueurEsku.joueur1;
      return next;
    }
  }
}
