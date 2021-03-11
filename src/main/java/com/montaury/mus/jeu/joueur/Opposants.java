package com.montaury.mus.jeu.joueur;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

public class Opposants {
  private Joueur joueurEsku;
  private Joueur joueurZaku;
  private LinkedList<Joueur> listeJoueursJeu;
  private ArrayList<Equipe> equipes;

  public Opposants(Equipe equipeEsku, Equipe equipeZaku) {

    equipes = new ArrayList<>();

    equipes.add(equipeEsku);
    equipes.add(equipeZaku);

    listeJoueursJeu = new LinkedList<>();

    this.joueurEsku = equipeEsku.getJoueurs().get(0);
    this.joueurZaku = equipeZaku.getJoueurs().get(1);


    for (int i=0 ; i < equipeEsku.getJoueurs().size() ; i++ )
    {
      listeJoueursJeu.add(equipeEsku.getJoueurs().get(i));
      listeJoueursJeu.add(equipeZaku.getJoueurs().get(i));
    }

  }

  public void tourner() {
    Joueur echange = listeJoueursJeu.removeFirst();
    listeJoueursJeu.addLast(echange);

    defineRoles();
    /*joueurEsku = listeJoueursJeu.get((listeJoueursJeu.indexOf(this.joueurEsku)+1)%4);
    joueurZaku = listeJoueursJeu.get((listeJoueursJeu.indexOf(this.joueurZaku)+1)%4);
  */
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
    return this.listeJoueursJeu;
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
      int indexSuivant = (opposants.listeJoueursJeu.indexOf(suivant)+1)%4;
      suivant = opposants.listeJoueursJeu.get(indexSuivant);
      return next;
    }
  }

  public List<Equipe> getEquipes(){
    return equipes;
  }

  public void defineRoles()
  {
    joueurEsku = listeJoueursJeu.getFirst();
    joueurZaku = listeJoueursJeu.getLast();
  }
}
