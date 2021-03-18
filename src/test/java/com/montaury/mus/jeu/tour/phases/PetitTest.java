package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.joueur.Equipe;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.joueur.Opposants;
import org.junit.jupiter.api.Test;

import static com.montaury.mus.jeu.joueur.Fixtures.main;
import static com.montaury.mus.jeu.joueur.Fixtures.unJoueurAvec;
import static org.assertj.core.api.Assertions.assertThat;

class PetitTest {
  @Test
  void devrait_faire_gagner_le_joueur_esku_s_il_a_la_plus_petite_carte() {
    Joueur joueurEsku = unJoueurAvec(main(Carte.DEUX_PIECE, Carte.CINQ_PIECE, Carte.SEPT_BATON, Carte.AS_COUPE));
    Joueur joueurZaku = unJoueurAvec(main(Carte.DEUX_BATON, Carte.TROIS_PIECE, Carte.CAVALIER_BATON, Carte.SIX_PIECE));
    Joueur ordi1 = unJoueurAvec(main(Carte.DEUX_PIECE, Carte.CINQ_PIECE, Carte.SEPT_BATON, Carte.SIX_COUPE));
    Joueur ordi2 = unJoueurAvec(main(Carte.DEUX_BATON, Carte.TROIS_PIECE, Carte.CAVALIER_BATON, Carte.SIX_PIECE));

    Equipe equipe1 = new Equipe(joueurEsku,ordi1,"e1");
    Equipe equipe2 = new Equipe(joueurZaku,ordi2,"e2");

    Joueur vainqueur = new Petit().meilleurParmi(new Opposants(equipe1,equipe2));

    assertThat(vainqueur).isEqualTo(joueurEsku);
  }

  @Test
  void devrait_faire_gagner_le_joueur_zaku_s_il_a_la_plus_petite_carte() {
    Joueur joueurEsku = unJoueurAvec(main(Carte.DEUX_PIECE, Carte.CINQ_PIECE, Carte.SEPT_BATON, Carte.SIX_COUPE));
    Joueur joueurZaku = unJoueurAvec(main(Carte.DEUX_BATON, Carte.DEUX_PIECE, Carte.DEUX_BATON, Carte.AS_BATON));
    Joueur ordi1 = unJoueurAvec(main(Carte.DEUX_PIECE, Carte.CINQ_PIECE, Carte.SEPT_BATON, Carte.SIX_COUPE));
    Joueur ordi2 = unJoueurAvec(main(Carte.DEUX_BATON, Carte.TROIS_PIECE, Carte.CAVALIER_BATON, Carte.SIX_PIECE));

    Equipe equipe1 = new Equipe(joueurEsku,ordi1,"e1");
    Equipe equipe2 = new Equipe(joueurZaku,ordi2,"e2");

    Joueur vainqueur = new Petit().meilleurParmi(new Opposants(equipe1,equipe2));

    assertThat(vainqueur).isEqualTo(joueurZaku);
  }

  @Test
  void devrait_faire_gagner_le_joueur_qui_a_la_seconde_plus_petite_carte_si_la_premiere_est_egale() {
    Joueur joueurEsku = unJoueurAvec(main(Carte.ROI_BATON, Carte.ROI_PIECE, Carte.ROI_BATON, Carte.DEUX_BATON));
    Joueur joueurZaku = unJoueurAvec(main(Carte.ROI_BATON, Carte.ROI_PIECE, Carte.TROIS_BATON, Carte.DEUX_COUPE));
    Joueur ordi1 = unJoueurAvec(main(Carte.ROI_BATON, Carte.ROI_PIECE, Carte.ROI_BATON, Carte.ROI_COUPE));
    Joueur ordi2 = unJoueurAvec(main(Carte.ROI_BATON, Carte.ROI_PIECE, Carte.ROI_BATON, Carte.ROI_COUPE));

    Equipe equipe1 = new Equipe(joueurEsku,ordi1,"e1");
    Equipe equipe2 = new Equipe(joueurZaku,ordi2,"e2");

    Joueur vainqueur = new Petit().meilleurParmi(new Opposants(equipe1,equipe2));

    assertThat(vainqueur).isEqualTo(joueurZaku);
  }

  @Test
  void devrait_faire_gagner_le_joueur_esku_si_les_deux_mains_sont_egales() {
    Joueur joueurEsku = unJoueurAvec(main(Carte.TROIS_BATON, Carte.CINQ_PIECE, Carte.SEPT_BATON, Carte.AS_COUPE));
    Joueur joueurZaku = unJoueurAvec(main(Carte.TROIS_BATON, Carte.CINQ_PIECE, Carte.SEPT_BATON, Carte.AS_COUPE));
    Joueur ordi1 = unJoueurAvec(main(Carte.TROIS_BATON, Carte.CINQ_PIECE, Carte.SEPT_BATON, Carte.AS_COUPE));
    Joueur ordi2 = unJoueurAvec(main(Carte.TROIS_BATON, Carte.CINQ_PIECE, Carte.SEPT_BATON, Carte.AS_COUPE));

    Equipe equipe1 = new Equipe(joueurEsku,ordi1,"e1");
    Equipe equipe2 = new Equipe(joueurZaku,ordi2,"e2");

    Joueur vainqueur = new Petit().meilleurParmi(new Opposants(equipe1,equipe2));

    assertThat(vainqueur).isEqualTo(joueurEsku);
  }
}