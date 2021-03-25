package com.montaury.mus.jeu.equipe;

import com.montaury.mus.jeu.joueur.Joueur;

import java.util.ArrayList;
import java.util.Iterator;

import java.util.LinkedList;
import java.util.List;

public class Opposants {
  private Equipe equipe1;
  private Equipe equipe2;
  private LinkedList<Joueur> joueursDansLordre;


  public Opposants(Equipe equipe1, Equipe equipe2) {
    this.equipe1 = equipe1;
    this.equipe2 = equipe2;
    joueursDansLordre = new LinkedList<Joueur>();
    joueursDansLordre.add(joueurEsku());
    joueursDansLordre.add(joueur1equipe2());
    joueursDansLordre.add(joueur2equipe1());
    joueursDansLordre.add(joueurZaku());
  }
  // permet de changer celui qui distribue et celui qui parle en premier
  public void tourner() {
    Joueur jouerTempon = joueursDansLordre.removeLast();
    joueursDansLordre.addFirst(jouerTempon);
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
    return joueursDansLordre;
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
