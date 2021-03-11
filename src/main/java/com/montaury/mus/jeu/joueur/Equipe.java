package com.montaury.mus.jeu.joueur;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.montaury.mus.jeu.joueur.Joueur;

public class Equipe  {

    private final List<Joueur> joueurs;
    private String nom;
    private boolean peutParler = true;
    private int score = 0;

    // Crée une équipe à 1 ou 2 joueurs (selon partieAQuatre
    public Equipe (Joueur joueur, String nomEquipe, boolean partieAQuatre, String nomEquipier) {

        this.nom = nomEquipe;
        joueurs = new ArrayList<>();

        this.joueurs.add(joueur);
        joueur.setEquipe(this);

        if (partieAQuatre) {
            Joueur joueurOrdi = Joueur.ordinateur(nomEquipier);
            this.joueurs.add(joueurOrdi);
            joueurOrdi.setEquipe(this);
        }
    }

    public String nom() {
        return this.nom;
    }

    public List<Joueur> getJoueurs() { return this.joueurs; }

    public String getNom() { return this.nom; }

    public boolean getPeutParler() { return this.peutParler; }

    public void setPeutParler(boolean permission) { this.peutParler = permission; }

    public int getScore() { return this.score; }

    public void setScore(int points) { this.score = points; }

    public void addPoints(int points) { this.score += points; }
}
