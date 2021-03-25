package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.equipe.Equipe;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.equipe.Opposants;
import org.junit.jupiter.api.Test;

import static com.montaury.mus.jeu.joueur.Fixtures.main;
import static com.montaury.mus.jeu.joueur.Fixtures.unJoueurAvec;
import static org.assertj.core.api.Assertions.assertThat;

class FauxJeuTest {
  @Test
  void ne_doit_pas_se_derouler_si_un_seul_des_joueurs_a_le_jeu() {
    Opposants opposants = new Opposants(
            new Equipe( unJoueurAvec(main(Carte.AS_BATON, Carte.QUATRE_PIECE, Carte.VALET_BATON, Carte.SIX_COUPE)),
                    unJoueurAvec(main(Carte.AS_COUPE, Carte.DEUX_COUPE, Carte.TROIS_COUPE, Carte.QUATRE_COUPE))),
            new Equipe( unJoueurAvec(main(Carte.AS_EPEE, Carte.DEUX_EPEE, Carte.TROIS_EPEE, Carte.QUATRE_EPEE)),
                    unJoueurAvec(main(Carte.VALET_PIECE, Carte.ROI_COUPE, Carte.CAVALIER_PIECE, Carte.AS_EPEE)))
    );

    boolean peutSeDerouler = new FauxJeu().peutSeDerouler(opposants);

    assertThat(peutSeDerouler).isFalse();
  }

  @Test
  void doit_se_derouler_si_personne_n_a_le_jeu() {
    Opposants opposants = new Opposants(
            new Equipe( unJoueurAvec(main(Carte.AS_BATON, Carte.QUATRE_PIECE, Carte.VALET_BATON, Carte.SIX_COUPE)),
                    unJoueurAvec(main(Carte.AS_COUPE, Carte.DEUX_COUPE, Carte.TROIS_COUPE, Carte.QUATRE_COUPE))),
            new Equipe( unJoueurAvec(main(Carte.AS_EPEE, Carte.DEUX_EPEE, Carte.TROIS_EPEE, Carte.QUATRE_EPEE)),
                    unJoueurAvec(main(Carte.AS_PIECE, Carte.DEUX_PIECE, Carte.TROIS_PIECE, Carte.QUATRE_BATON)))
    );

    boolean peutSeDerouler = new FauxJeu().peutSeDerouler(opposants);

    assertThat(peutSeDerouler).isTrue();
  }

  @Test
  void devrait_faire_gagner_le_joueur_ayant_le_plus_grand_nombre_de_points() {
    Joueur joueur1 = unJoueurAvec(main(Carte.VALET_PIECE, Carte.SIX_PIECE, Carte.QUATRE_BATON, Carte.ROI_COUPE));
    Opposants opposants = new Opposants(
            new Equipe( joueur1,
                    unJoueurAvec(main(Carte.AS_COUPE, Carte.DEUX_COUPE, Carte.TROIS_COUPE, Carte.QUATRE_COUPE))),
            new Equipe( unJoueurAvec(main(Carte.AS_EPEE, Carte.DEUX_EPEE, Carte.TROIS_EPEE, Carte.QUATRE_EPEE)),
                    unJoueurAvec(main(Carte.AS_PIECE, Carte.DEUX_PIECE, Carte.TROIS_PIECE, Carte.QUATRE_BATON)))
    );

    Joueur vainqueur = new FauxJeu().meilleurParmi(opposants);

    assertThat(vainqueur).isEqualTo(joueur1);
  }

  @Test
  void devrait_faire_gagner_le_joueur_esku_en_cas_d_egalite() {
    Joueur joueur1 = unJoueurAvec(main(Carte.AS_BATON, Carte.QUATRE_PIECE, Carte.VALET_BATON, Carte.SIX_COUPE));
    Opposants opposants = new Opposants(
            new Equipe( joueur1,
                    unJoueurAvec(main(Carte.AS_PIECE, Carte.QUATRE_BATON, Carte.VALET_COUPE, Carte.SIX_BATON))),
            new Equipe( unJoueurAvec(main(Carte.AS_EPEE, Carte.QUATRE_EPEE, Carte.VALET_EPEE, Carte.SIX_EPEE)),
                    unJoueurAvec(main(Carte.AS_COUPE, Carte.QUATRE_COUPE, Carte.VALET_PIECE, Carte.SIX_PIECE)))
    );

    Joueur vainqueur = new FauxJeu().meilleurParmi(opposants);

    assertThat(vainqueur).isEqualTo(joueur1);
  }

  @Test
  void doit_faire_gagner_un_bonus_de_1() {
    Joueur joueurEsku = unJoueurAvec(main(Carte.AS_BATON, Carte.QUATRE_PIECE, Carte.VALET_BATON, Carte.SIX_COUPE));

    int bonus = new FauxJeu().pointsBonus(joueurEsku);

    assertThat(bonus).isEqualTo(1);
  }

}
