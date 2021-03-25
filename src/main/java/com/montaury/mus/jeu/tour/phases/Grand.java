package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.carte.ValeurCarte;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.joueur.Main;
import com.montaury.mus.jeu.equipe.Opposants;
import java.util.List;

import static com.montaury.mus.jeu.carte.ValeurCarte.Comparaison.*;

public class Grand extends Phase {
  public Grand() {
    super("Grand");
  }

  @Override
  protected Joueur meilleurParmi(Opposants opposants) {
    Joueur joueurEsku = opposants.joueur1();
    Joueur joueur3 = opposants.joueur2();
    Joueur joueur2 = opposants.joueur3();
    Joueur joueurZaku = opposants.joueur4();
    Joueur joueurEquipe1Choisi = null;
    Joueur joueurEquipe2Choisi = null;
    Joueur joueurFinal=joueurEsku;


    List<Carte> cartesJoueurEsku = joueurEsku.main().cartesDuPlusGrandAuPlusPetit();
    List<Carte> cartesjoueur2 = joueur2.main().cartesDuPlusGrandAuPlusPetit();
    List<Carte> cartesjoueur3 = joueur3.main().cartesDuPlusGrandAuPlusPetit();
    List<Carte> cartesJoueurZaku = joueurZaku.main().cartesDuPlusGrandAuPlusPetit();

    List<Carte> cartesjoueurPlusGrandEquipe1 = null;
    List<Carte> cartesjoueurPlusGrandEquipe2 = null;

    for (int i = 0; i < Main.TAILLE; i++) {
      ValeurCarte.Comparaison compareEquipe1 = cartesJoueurEsku.get(i).comparerAvec(cartesjoueur3.get(i));
      if (compareEquipe1 == PLUS_GRANDE) {
        cartesjoueurPlusGrandEquipe1 = cartesJoueurEsku;
        joueurEquipe1Choisi = joueurEsku;
        break;
      } else if (compareEquipe1 == PLUS_PETITE) {
        cartesjoueurPlusGrandEquipe1 = cartesjoueur3;
        joueurEquipe1Choisi = joueur3;
        break;
      }
    }

    for (int i = 0; i < Main.TAILLE; i++) {
      ValeurCarte.Comparaison compareEquipe2 = cartesjoueur2.get(i).comparerAvec(cartesJoueurZaku.get(i));
      if (compareEquipe2 == PLUS_GRANDE) {
        cartesjoueurPlusGrandEquipe2 = cartesjoueur2;
        joueurEquipe2Choisi = joueur2;
        break;
      } else if (compareEquipe2 == PLUS_PETITE) {
        cartesjoueurPlusGrandEquipe2 = cartesJoueurZaku;
        joueurEquipe2Choisi = joueurZaku;
        break;
      }
    }
    for (int i = 0; i < Main.TAILLE; i++) {
      ValeurCarte.Comparaison compareEntreEquipe = cartesjoueurPlusGrandEquipe1.get(i).comparerAvec(cartesjoueurPlusGrandEquipe2.get(i));
      if (compareEntreEquipe == PLUS_GRANDE ) {
        joueurFinal= joueurEquipe1Choisi;
        break;
      } else if (compareEntreEquipe == PLUS_PETITE) {
        joueurFinal= joueurEquipe2Choisi;
        break;

      }
    }
    return joueurFinal;
  }
}
