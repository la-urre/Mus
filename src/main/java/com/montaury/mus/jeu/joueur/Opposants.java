package com.montaury.mus.jeu.joueur;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

public class Opposants {
  private Joueur joueurEsku;
  private Joueur joueurZaku;
  private ArrayList<Joueur> listeJoueursJeu;

  public Opposants(Equipe equipeEsku, Equipe equipeZaku) {

    listeJoueursJeu = new ArrayList<>();

    this.joueurEsku = equipeEsku.getJoueurs().get(0);


    for (int i=0 ; i < equipeEsku.getJoueurs().size() ; i++ )
    {
      listeJoueursJeu.add(equipeEsku.getJoueurs().get(i));
      listeJoueursJeu.add(equipeZaku.getJoueurs().get(i));
    }

  }

  public void tourner() {
    Joueur tmp = joueurEsku;
    joueurEsku = joueurZaku;
    joueurZaku = tmp;
  }

  public Joueur joueurEsku() {
    return joueurEsku;
  }

  public Joueur joueurZaku() {
    return joueurZaku;
  }

  public Iterator<Joueur> itererDansLOrdre() {
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
