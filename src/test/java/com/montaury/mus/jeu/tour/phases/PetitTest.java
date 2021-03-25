package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.equipe.Equipe;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.equipe.Opposants;
import org.junit.jupiter.api.Test;

import static com.montaury.mus.jeu.joueur.Fixtures.main;
import static com.montaury.mus.jeu.joueur.Fixtures.unJoueurAvec;
import static org.assertj.core.api.Assertions.assertThat;

class PetitTest {
  @Test
  void devrait_faire_gagner_le_joueur_1_s_il_a_la_plus_petite_carte() {
    Joueur joueur1 = unJoueurAvec(main(Carte.DEUX_PIECE, Carte.CINQ_PIECE, Carte.SEPT_BATON, Carte.AS_COUPE));
    Joueur joueur2 = unJoueurAvec(main(Carte.DEUX_BATON, Carte.DEUX_BATON, Carte.CAVALIER_BATON, Carte.SIX_BATON));
    Joueur joueur3 = unJoueurAvec(main(Carte.TROIS_EPEE, Carte.CINQ_EPEE, Carte.SEPT_EPEE, Carte.VALET_EPEE));
    Joueur joueur4 = unJoueurAvec(main(Carte.DEUX_PIECE, Carte.TROIS_PIECE, Carte.CAVALIER_PIECE, Carte.SIX_PIECE));

    Equipe equipe1 = new Equipe(joueur1,joueur2);
    Equipe equipe2 = new Equipe(joueur3,joueur4);

    Joueur vainqueur = new Petit().meilleurParmi(new Opposants(equipe1, equipe2));

    assertThat(vainqueur).isEqualTo(joueur1);
  }

  @Test
  void devrait_faire_gagner_le_joueur_2_s_il_a_la_plus_petite_carte() {
    Joueur joueur1 = unJoueurAvec(main(Carte.DEUX_BATON, Carte.DEUX_BATON, Carte.CAVALIER_BATON, Carte.SIX_BATON));
    Joueur joueur2 = unJoueurAvec(main(Carte.DEUX_PIECE, Carte.CINQ_PIECE, Carte.SEPT_BATON, Carte.AS_COUPE));
    Joueur joueur3 = unJoueurAvec(main(Carte.TROIS_EPEE, Carte.CINQ_EPEE, Carte.SEPT_EPEE, Carte.VALET_EPEE));
    Joueur joueur4 = unJoueurAvec(main(Carte.DEUX_PIECE, Carte.TROIS_PIECE, Carte.CAVALIER_PIECE, Carte.SIX_PIECE));

    Equipe equipe1 = new Equipe(joueur1,joueur2);
    Equipe equipe2 = new Equipe(joueur3,joueur4);

    Joueur vainqueur = new Petit().meilleurParmi(new Opposants(equipe1, equipe2));

    assertThat(vainqueur).isEqualTo(joueur2);
  }

  @Test
  void devrait_faire_gagner_le_joueur_3_s_il_a_la_plus_petite_carte() {
    Joueur joueur1 = unJoueurAvec(main(Carte.TROIS_EPEE, Carte.CINQ_EPEE, Carte.SEPT_EPEE, Carte.VALET_EPEE));
    Joueur joueur2 = unJoueurAvec(main(Carte.DEUX_BATON, Carte.DEUX_BATON, Carte.CAVALIER_BATON, Carte.SIX_BATON));
    Joueur joueur3 = unJoueurAvec(main(Carte.DEUX_EPEE, Carte.CINQ_PIECE, Carte.SEPT_BATON, Carte.AS_COUPE));
    Joueur joueur4 = unJoueurAvec(main(Carte.DEUX_PIECE, Carte.TROIS_PIECE, Carte.CAVALIER_PIECE, Carte.SIX_PIECE));

    Equipe equipe1 = new Equipe(joueur1,joueur2);
    Equipe equipe2 = new Equipe(joueur3,joueur4);

    Joueur vainqueur = new Petit().meilleurParmi(new Opposants(equipe1, equipe2));

    assertThat(vainqueur).isEqualTo(joueur3);
  }

  @Test
  void devrait_faire_gagner_le_joueur_4_s_il_a_la_plus_petite_carte() {
    Joueur joueur1 = unJoueurAvec(main(Carte.DEUX_PIECE, Carte.CINQ_PIECE, Carte.SEPT_BATON, Carte.ROI_PIECE));
    Joueur joueur2 = unJoueurAvec(main(Carte.DEUX_BATON, Carte.ROI_BATON, Carte.CAVALIER_BATON, Carte.SIX_BATON));
    Joueur joueur3 = unJoueurAvec(main(Carte.DEUX_EPEE, Carte.TROIS_PIECE, Carte.CAVALIER_PIECE, Carte.SIX_PIECE));
    Joueur joueur4 = unJoueurAvec(main(Carte.DEUX_COUPE, Carte.CINQ_EPEE, Carte.SEPT_PIECE, Carte.AS_COUPE));

    Equipe equipe1 = new Equipe(joueur1,joueur2);
    Equipe equipe2 = new Equipe(joueur3,joueur4);

    Joueur vainqueur = new Petit().meilleurParmi(new Opposants(equipe1, equipe2));

    assertThat(vainqueur).isEqualTo(joueur4);
  }

  @Test
  void devrait_faire_gagner_le_joueur_qui_a_la_seconde_plus_petite_carte_si_la_premiere_est_egale() {

    Joueur joueur1 = unJoueurAvec(main(Carte.AS_PIECE, Carte.CINQ_PIECE, Carte.SEPT_BATON, Carte.TROIS_BATON));
    Joueur joueur2 = unJoueurAvec(main(Carte.AS_BATON, Carte.CAVALIER_EPEE, Carte.CAVALIER_BATON, Carte.SIX_BATON));
    Joueur joueur3 = unJoueurAvec(main(Carte.AS_PIECE, Carte.TROIS_PIECE, Carte.CAVALIER_PIECE, Carte.SIX_PIECE));
    Joueur joueur4 = unJoueurAvec(main(Carte.AS_PIECE, Carte.CINQ_PIECE, Carte.SEPT_BATON, Carte.DEUX_COUPE));

    Equipe equipe1 = new Equipe(joueur1,joueur2);
    Equipe equipe2 = new Equipe(joueur3,joueur4);

    Joueur vainqueur = new Petit().meilleurParmi(new Opposants(equipe1, equipe2));

    assertThat(vainqueur).isEqualTo(joueur4);
  }

  @Test
  void devrait_faire_gagner_le_joueur_esku_si_les_deux_mains_sont_egales() {
    Joueur joueur1 = unJoueurAvec(main(Carte.AS_PIECE, Carte.CINQ_PIECE, Carte.CAVALIER_EPEE, Carte.SIX_COUPE));
    Joueur joueur2 = unJoueurAvec(main(Carte.AS_BATON, Carte.CINQ_EPEE, Carte.CAVALIER_BATON, Carte.SIX_BATON));
    Joueur joueur3 = unJoueurAvec(main(Carte.AS_PIECE, Carte.CINQ_BATON, Carte.CAVALIER_PIECE, Carte.SIX_PIECE));
    Joueur joueur4 = unJoueurAvec(main(Carte.AS_PIECE, Carte.CINQ_COUPE, Carte.CAVALIER_COUPE, Carte.SIX_EPEE));

    Equipe equipe1 = new Equipe(joueur1,joueur2);
    Equipe equipe2 = new Equipe(joueur3,joueur4);

    Joueur vainqueur = new Petit().meilleurParmi(new Opposants(equipe1, equipe2));

    assertThat(vainqueur).isEqualTo(joueur1);
  }
}