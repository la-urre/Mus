package com.montaury.mus.jeu;

import com.montaury.mus.jeu.joueur.AffichageEvenementsDeJeu;
import com.montaury.mus.jeu.joueur.Equipe;
import com.montaury.mus.jeu.joueur.Opposants;
import com.montaury.mus.jeu.tour.Tour;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Manche {
  private final AffichageEvenementsDeJeu affichage;

  public Manche(AffichageEvenementsDeJeu affichage) {
    this.affichage = affichage;
  }

  public Resultat jouer(Opposants opposants) {
    affichage.nouvelleManche();
    Score score = new Score(opposants);
    do {
      new Tour(affichage).jouer(opposants, score);
      affichage.tourTermine(opposants, score);
      opposants.tourner();
    } while (score.vainqueur().isEmpty());
    return new Resultat(score.vainqueur().get(), score.pointsVaincu().get());
  }

  public static class Score {
    private static final int POINTS_POUR_TERMINER_MANCHE = 40;

    private final Map<Equipe, Integer> scoreParEquipe = new HashMap<>();

    public Score(Opposants opposants) {
      for(Equipe equipe : opposants.equipes()) scoreParEquipe.put(equipe,0);
    }

    public Map<Equipe, Integer> scoreParEquipe() {
      return scoreParEquipe;
    }

    public void scorer(Equipe equipe, int points) {
      if (vainqueur().isEmpty()) {
        scoreParEquipe.put(equipe, Math.min(scoreParEquipe.get(equipe) + points, POINTS_POUR_TERMINER_MANCHE));
      }
    }

    public void remporterManche(Equipe Equipe) {
      scoreParEquipe.put(Equipe, POINTS_POUR_TERMINER_MANCHE);
    }

    public Optional<Equipe> vainqueur() {
      return scoreParEquipe.keySet().stream().filter(Equipe -> scoreParEquipe.get(Equipe) == POINTS_POUR_TERMINER_MANCHE).findAny();
    }

    public Optional<Integer> pointsVaincu() {
      return vainqueur().isEmpty() ?
        Optional.empty() :
        scoreParEquipe.values().stream().filter(points -> points < POINTS_POUR_TERMINER_MANCHE).findAny();
    }
  }

  public static class Resultat {
    private final Equipe vainqueur;
    private final int pointsVaincu;

    public Resultat(Equipe Equipe, int pointsVaincu) {
      vainqueur = Equipe;
      this.pointsVaincu = pointsVaincu;
    }

    public Equipe vainqueur() {
      return vainqueur;
    }

    public int pointsVaincu() {
      return pointsVaincu;
    }
  }
}
