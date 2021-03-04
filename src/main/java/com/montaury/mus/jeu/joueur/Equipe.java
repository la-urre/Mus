package com.montaury.mus.jeu.joueur;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.montaury.mus.jeu.joueur.Joueur;

public class Equipe  {

    private final List<Joueur> joueurs;
    private String nom;
    private boolean peutParler = true;

    public Equipe (Joueur joueur, String nomEquipe, boolean partieAQuatre) {

        this.nom = nomEquipe;
        joueurs = new LinkedList<>();

        this.joueurs.add(joueur);
        joueur.setEquipe(this);

        if (partieAQuatre) {
            Joueur joueurOrdi = Joueur.ordinateur();
            this.joueurs.add(joueurOrdi);
            joueurOrdi.setEquipe(this);
        }
    }

    public List<Joueur> getJoueurs() { return this.joueurs; }

    public String getNom() { return this.nom; }

    public boolean getPeutParler() { return this.peutParler; }

    public void setPeutParler(boolean permission) { this.peutParler = permission; }
}
