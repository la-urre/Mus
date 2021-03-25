package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.equipe.Equipe;
import com.montaury.mus.jeu.equipe.Opposants;
import com.montaury.mus.jeu.joueur.Joueur;
import org.junit.jupiter.api.Test;

import static com.montaury.mus.jeu.joueur.Fixtures.main;
import static com.montaury.mus.jeu.joueur.Fixtures.unJoueurAvec;
import static org.assertj.core.api.Assertions.assertThat;

class PairesTest {
  @Test
  void ne_doit_pas_se_derouler_si_personne_n_a_de_paires() {
    Opposants opposants = new Opposants(
      new Equipe( unJoueurAvec(main(Carte.AS_BATON, Carte.QUATRE_PIECE, Carte.VALET_BATON, Carte.SIX_COUPE)),
                  unJoueurAvec(main(Carte.VALET_PIECE, Carte.SIX_PIECE, Carte.QUATRE_BATON, Carte.AS_PIECE))),
      new Equipe( unJoueurAvec(main(Carte.DEUX_PIECE, Carte.ROI_BATON, Carte.VALET_EPEE, Carte.AS_COUPE)),
                  unJoueurAvec(main(Carte.DEUX_EPEE, Carte.TROIS_PIECE, Carte.CINQ_BATON, Carte.AS_EPEE)))
    );

    boolean peutSeDerouler = new Paires().peutSeDerouler(opposants);

    assertThat(peutSeDerouler).isFalse();
  }

  @Test
  void ne_doit_pas_se_derouler_si_un_des_joueurs_n_a_pas_de_paires() {
    Opposants opposants = new Opposants(
      new Equipe( unJoueurAvec(main(Carte.AS_BATON, Carte.QUATRE_PIECE, Carte.VALET_BATON, Carte.SIX_COUPE)),
                  unJoueurAvec(main(Carte.VALET_PIECE, Carte.SIX_PIECE, Carte.QUATRE_BATON, Carte.AS_PIECE))),
      new Equipe( unJoueurAvec(main(Carte.DEUX_PIECE, Carte.ROI_BATON, Carte.VALET_EPEE, Carte.AS_COUPE)),
                  unJoueurAvec(main(Carte.DEUX_EPEE, Carte.TROIS_PIECE, Carte.CINQ_BATON, Carte.AS_EPEE)))
    );

    boolean peutSeDerouler = new Paires().peutSeDerouler(opposants);

    assertThat(peutSeDerouler).isFalse();
  }

  @Test
  void devrait_se_derouler_si_un_joueur_de_chaque_equipe_ont_un_ou_des_paires() {
    Opposants opposants = new Opposants(
      new Equipe( unJoueurAvec(main(Carte.DEUX_COUPE, Carte.QUATRE_PIECE, Carte.VALET_BATON, Carte.SIX_COUPE)),
                  unJoueurAvec(main(Carte.AS_BATON, Carte.SIX_PIECE, Carte.QUATRE_BATON, Carte.AS_PIECE))),
      new Equipe( unJoueurAvec(main(Carte.DEUX_PIECE, Carte.DEUX_EPEE, Carte.VALET_EPEE, Carte.AS_COUPE)),
                  unJoueurAvec(main(Carte.ROI_BATON, Carte.TROIS_PIECE, Carte.CINQ_BATON, Carte.AS_EPEE)))
    );

    boolean peutSeDerouler = new Paires().peutSeDerouler(opposants);

    assertThat(peutSeDerouler).isTrue();
  }

  @Test
  void devrait_faire_gagner_le_joueur_ayant_la_meilleure_paire() {
    Joueur j2=unJoueurAvec(main(Carte.AS_BATON, Carte.SIX_PIECE, Carte.QUATRE_BATON, Carte.AS_PIECE));
    Opposants opposants = new Opposants(
      new Equipe( unJoueurAvec(main(Carte.DEUX_COUPE, Carte.QUATRE_PIECE, Carte.VALET_BATON, Carte.SIX_COUPE)),
                  j2),
      new Equipe( unJoueurAvec(main(Carte.DEUX_PIECE, Carte.DEUX_EPEE, Carte.VALET_EPEE, Carte.AS_COUPE)),
                  unJoueurAvec(main(Carte.ROI_BATON, Carte.TROIS_PIECE, Carte.CINQ_BATON, Carte.AS_EPEE)))
    );

    Joueur vainqueur = new Paires().meilleurParmi(opposants);

    assertThat(vainqueur).isEqualTo(j2);
  }

  @Test
  void devrait_donner_un_bonus_de_1_si_le_joueur_a_une_paire_simple() {
    Joueur joueur = unJoueurAvec(main(Carte.VALET_PIECE, Carte.SIX_PIECE, Carte.SIX_EPEE, Carte.AS_PIECE));

    int bonus = new Paires().pointsBonus(joueur);

    assertThat(bonus).isEqualTo(1);
  }

  @Test
  void devrait_donner_un_bonus_de_2_si_le_joueur_a_des_meds() {
    Joueur joueur = unJoueurAvec(main(Carte.VALET_PIECE, Carte.SIX_PIECE, Carte.SIX_EPEE, Carte.SIX_COUPE));

    int bonus = new Paires().pointsBonus(joueur);

    assertThat(bonus).isEqualTo(2);
  }

  @Test
  void devrait_donner_un_bonus_de_3_si_le_joueur_a_des_doubles() {
    Joueur joueur = unJoueurAvec(main(Carte.VALET_PIECE, Carte.VALET_BATON, Carte.VALET_COUPE, Carte.VALET_EPEE));

    int bonus = new Paires().pointsBonus(joueur);

    assertThat(bonus).isEqualTo(3);
  }

}