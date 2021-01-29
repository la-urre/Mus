package com.montaury.mus.jeu.joueur;

import com.montaury.mus.jeu.carte.Carte;
import java.util.List;

public class Joueur {
  //constructeur d'un joueur humain prend en parametre le nom de joueur choisi par l'utilisateur
  //retourne un objet Joueur

  public static Joueur humain(String nom) {
    return new Joueur(nom, new InterfaceJoueurHumain());
  }

  //constructeur d'un joueur ordinateur
  //retourne un objet Joueur
  public static Joueur ordinateur() {
    return new Joueur("Ordinateur", new InterfaceJoueurOrdinateur());
  }

  private final String nom;
  public final InterfaceJoueur interfaceJoueur;
  private final Main main = Main.vide();

  //Constructeur d'un joueur prend en parametre le nom du joueur et son interface qui defini son statuts soit ordinateur soit Humain

  public Joueur(String nom, InterfaceJoueur interfaceJoueur) {
    this.nom = nom;
    this.interfaceJoueur = interfaceJoueur;
  }
//Getter
  public String nom() {
    return nom;
  }

  public Main main() {
    return main;
  }

  public void donnerCartes(List<Carte> cartes) {
    main.ajouter(cartes);
    interfaceJoueur.nouvelleMain(main);
  }
}
