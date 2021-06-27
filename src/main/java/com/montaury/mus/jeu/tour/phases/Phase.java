package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.Manche;
import com.montaury.mus.jeu.joueur.AffichageEvenementsDeJeu;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.joueur.Main;
import com.montaury.mus.jeu.Opposants;
import com.montaury.mus.jeu.tour.phases.dialogue.Dialogue;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.montaury.mus.jeu.tour.phases.dialogue.choix.TypeChoix.KANTA;
import static com.montaury.mus.jeu.tour.phases.dialogue.choix.TypeChoix.PASO;
import static com.montaury.mus.jeu.tour.phases.dialogue.choix.TypeChoix.TIRA;

public abstract class Phase {
  private final String nom;

  protected Phase(String nom) {
    this.nom = nom;
  }

  public String nom() {
    return nom;
  }

  public final Resultat jouer(AffichageEvenementsDeJeu affichage, Opposants opposants, Manche.Score score) {
    affichage.nouvellePhase(this);
    var participants = participantsParmi(opposants);
    if (participants.aucun()) {
      return Phase.Resultat.nonJouable();
    }
    if (participants.estUnique()) {
      Joueur premier = participants.premier();
      return Phase.Resultat.termine(premier, pointsBonus(premier));
    }
    var resultat = new Dialogue(affichage).derouler(participants);
    return conclure(resultat, score, participants);
  }

  private Resultat conclure(Dialogue.Recapitulatif recapitulatifDialogue, Manche.Score score, Participants participants) {
    if (recapitulatifDialogue.terminePar(TIRA)) {
      var joueurEmportantLaMise = recapitulatifDialogue.dernierJoueurAyantMise();
      score.scorer(joueurEmportantLaMise, recapitulatifDialogue.pointsEngages());
      return Phase.Resultat.termine(joueurEmportantLaMise, pointsBonus(joueurEmportantLaMise));
    }
    var vainqueur = meilleurParmi(participants);
    if (recapitulatifDialogue.terminePar(KANTA)) {
      score.remporterManche(vainqueur);
      return Phase.Resultat.termine(vainqueur, 0);
    }
    var bonus = pointsBonus(vainqueur);
    return Phase.Resultat.suspendu(vainqueur, recapitulatifDialogue.terminePar(PASO) && bonus != 0 ? 0 : recapitulatifDialogue.pointsEngages(), bonus);
  }

  public Participants participantsParmi(Opposants opposants) {
    return new Participants(opposants.dansLOrdre().stream()
      .filter(joueur -> peutParticiper(joueur.main()))
      .collect(Collectors.toList()));
  }

  protected boolean peutParticiper(Main main) {
    return true;
  }

  private Joueur meilleurParmi(Participants participants) {
    Joueur meilleur = null;
    for (Joueur joueur : participants.dansLOrdre()) {
      meilleur = meilleur == null ? joueur : meilleurEntre(meilleur, joueur);
    }
    return meilleur;
  }

  private Joueur meilleurEntre(Joueur joueurPlaceAvant, Joueur joueurPlaceApres) {
    return mainEskuEstMeilleure(joueurPlaceAvant.main(), joueurPlaceApres.main()) ? joueurPlaceAvant : joueurPlaceApres;
  }

  protected abstract boolean mainEskuEstMeilleure(Main mainJoueurEsku, Main mainJoueurZaku);

  protected int pointsBonus(Joueur vainqueur) {
    return 0;
  }

  public static class Resultat {
    public static Resultat nonJouable() {
      return new Resultat(null, 0, 0);
    }

    public static Resultat termine(Joueur vainqueur, int bonusDeFin) {
      return new Resultat(vainqueur, 0, bonusDeFin);
    }

    public static Resultat suspendu(Joueur vainqueur, int pointsEnSuspens, int bonusDeFin) {
      return new Resultat(vainqueur, pointsEnSuspens, bonusDeFin);
    }

    private final Joueur vainqueur;
    public final int pointsEnSuspens;
    public final int bonus;

    private Resultat(Joueur vainqueur, int pointsEnSuspens, int bonus) {
      this.pointsEnSuspens = pointsEnSuspens;
      this.vainqueur = vainqueur;
      this.bonus = bonus;
    }

    public Optional<Joueur> vainqueur() {
      return Optional.ofNullable(vainqueur);
    }

    public int points() {
      return pointsEnSuspens + bonus;
    }
  }
}
