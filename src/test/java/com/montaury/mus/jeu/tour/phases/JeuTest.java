package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.equipe.Equipe;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.equipe.Opposants;
import org.junit.jupiter.api.Test;

import static com.montaury.mus.jeu.joueur.Fixtures.main;
import static com.montaury.mus.jeu.joueur.Fixtures.unJoueurAvec;
import static org.assertj.core.api.Assertions.assertThat;

class JeuTest {
  @Test
  void ne_doit_pas_se_derouler_si_personne_n_a_le_jeu() {
    Opposants opposants = new Opposants(
            new Equipe( unJoueurAvec(main(Carte.AS_BATON, Carte.QUATRE_PIECE, Carte.VALET_BATON, Carte.SIX_COUPE)),
                    unJoueurAvec(main(Carte.AS_COUPE, Carte.DEUX_COUPE, Carte.TROIS_COUPE, Carte.QUATRE_COUPE))),
            new Equipe( unJoueurAvec(main(Carte.AS_EPEE, Carte.DEUX_EPEE, Carte.TROIS_EPEE, Carte.QUATRE_EPEE)),
                    unJoueurAvec(main(Carte.AS_PIECE, Carte.DEUX_PIECE, Carte.TROIS_PIECE, Carte.QUATRE_BATON)))
    );

    boolean peutSeDerouler = new Jeu().peutSeDerouler(opposants);

    assertThat(peutSeDerouler).isFalse();
  }

  @Test
  void ne_doit_pas_se_derouler_si_un_des_joueurs_n_a_pas_le_jeu() {
    Opposants opposants = new Opposants(
            new Equipe( unJoueurAvec(main(Carte.AS_BATON, Carte.QUATRE_PIECE, Carte.VALET_BATON, Carte.SIX_COUPE)),
                    unJoueurAvec(main(Carte.VALET_PIECE, Carte.ROI_EPEE, Carte.CAVALIER_COUPE, Carte.AS_PIECE))),
            new Equipe( unJoueurAvec(main(Carte.VALET_PIECE, Carte.ROI_BATON, Carte.CAVALIER_EPEE, Carte.AS_COUPE)),
                    unJoueurAvec(main(Carte.VALET_PIECE, Carte.ROI_COUPE, Carte.CAVALIER_PIECE, Carte.AS_EPEE)))
    );

    boolean peutSeDerouler = new Jeu().peutSeDerouler(opposants);

    assertThat(peutSeDerouler).isFalse();
  }

  @Test
  void devrait_se_derouler_si_tous_les_joueurs_ont_le_jeu() {
    Opposants opposants = new Opposants(
            new Equipe( unJoueurAvec(main(Carte.VALET_EPEE, Carte.ROI_COUPE, Carte.VALET_BATON, Carte.SIX_COUPE)),
                    unJoueurAvec(main(Carte.VALET_PIECE, Carte.ROI_EPEE, Carte.CAVALIER_COUPE, Carte.AS_PIECE))),
            new Equipe( unJoueurAvec(main(Carte.VALET_BATON, Carte.ROI_BATON, Carte.CAVALIER_EPEE, Carte.AS_COUPE)),
                    unJoueurAvec(main(Carte.VALET_COUPE, Carte.ROI_COUPE, Carte.CAVALIER_PIECE, Carte.AS_EPEE)))
    );

    boolean peutSeDerouler = new Jeu().peutSeDerouler(opposants);

    assertThat(peutSeDerouler).isTrue();
  }

  @Test
  void devrait_faire_gagner_le_joueur_ayant_31_par_rapport_a_32() {
    Joueur joueur1 = unJoueurAvec(main(Carte.VALET_COUPE, Carte.ROI_COUPE, Carte.CAVALIER_PIECE, Carte.AS_EPEE));
    Joueur joueur2 = unJoueurAvec(main(Carte.VALET_COUPE, Carte.ROI_COUPE, Carte.CAVALIER_PIECE, Carte.DEUX_PIECE));
    Joueur joueur3 = unJoueurAvec(main(Carte.VALET_COUPE, Carte.ROI_COUPE, Carte.CAVALIER_PIECE, Carte.DEUX_EPEE));
    Joueur joueur4 = unJoueurAvec(main(Carte.VALET_COUPE, Carte.ROI_COUPE, Carte.CAVALIER_PIECE, Carte.DEUX_BATON));
    Equipe equipe1 = new Equipe(joueur1,joueur2);
    Equipe equipe2 = new Equipe(joueur3,joueur4);

    Joueur vainqueur = new Petit().meilleurParmi(new Opposants(equipe1, equipe2));
    assertThat(vainqueur).isEqualTo(joueur1);
  }

  @Test
  void devrait_faire_gagner_le_joueur_ayant_40_par_rapport_a_37() {
    Joueur joueur1 = unJoueurAvec(main(Carte.VALET_COUPE, Carte.ROI_COUPE, Carte.CAVALIER_PIECE, Carte.ROI_EPEE));
    Joueur joueur2 = unJoueurAvec(main(Carte.VALET_COUPE, Carte.ROI_COUPE, Carte.CAVALIER_PIECE, Carte.SEPT_PIECE));
    Joueur joueur3 = unJoueurAvec(main(Carte.VALET_COUPE, Carte.ROI_COUPE, Carte.CAVALIER_PIECE, Carte.SEPT_EPEE));
    Joueur joueur4 = unJoueurAvec(main(Carte.VALET_COUPE, Carte.ROI_COUPE, Carte.CAVALIER_PIECE, Carte.SEPT_BATON));
    Equipe equipe1 = new Equipe(joueur1,joueur2);
    Equipe equipe2 = new Equipe(joueur3,joueur4);

    Joueur vainqueur = new Petit().meilleurParmi(new Opposants(equipe1, equipe2));
    assertThat(vainqueur).isEqualTo(joueur1);
  }

  @Test
  void devrait_faire_gagner_le_joueur_ayant_36_par_rapport_a_33() {
    Joueur joueur1 = unJoueurAvec(main(Carte.VALET_COUPE, Carte.ROI_COUPE, Carte.CAVALIER_PIECE, Carte.SIX_BATON));
    Joueur joueur2 = unJoueurAvec(main(Carte.VALET_COUPE, Carte.ROI_COUPE, Carte.CAVALIER_PIECE, Carte.TROIS_PIECE));
    Joueur joueur3 = unJoueurAvec(main(Carte.VALET_COUPE, Carte.ROI_COUPE, Carte.CAVALIER_PIECE, Carte.TROIS_EPEE));
    Joueur joueur4 = unJoueurAvec(main(Carte.VALET_COUPE, Carte.ROI_COUPE, Carte.CAVALIER_PIECE, Carte.TROIS_BATON));
    Equipe equipe1 = new Equipe(joueur1,joueur2);
    Equipe equipe2 = new Equipe(joueur3,joueur4);

    Joueur vainqueur = new Petit().meilleurParmi(new Opposants(equipe1, equipe2));
    assertThat(vainqueur).isEqualTo(joueur1);
  }

  @Test
  void devrait_faire_gagner_le_joueur_esku_en_cas_d_egalite() {
    Joueur joueur1 = unJoueurAvec(main(Carte.VALET_COUPE, Carte.ROI_COUPE, Carte.CAVALIER_PIECE, Carte.TROIS_COUPE));
    Joueur joueur2 = unJoueurAvec(main(Carte.VALET_COUPE, Carte.ROI_COUPE, Carte.CAVALIER_PIECE, Carte.TROIS_PIECE));
    Joueur joueur3 = unJoueurAvec(main(Carte.VALET_COUPE, Carte.ROI_COUPE, Carte.CAVALIER_PIECE, Carte.TROIS_EPEE));
    Joueur joueur4 = unJoueurAvec(main(Carte.VALET_COUPE, Carte.ROI_COUPE, Carte.CAVALIER_PIECE, Carte.TROIS_BATON));
    Equipe equipe1 = new Equipe(joueur1,joueur2);
    Equipe equipe2 = new Equipe(joueur3,joueur4);

    Joueur vainqueur = new Petit().meilleurParmi(new Opposants(equipe1, equipe2));
    assertThat(vainqueur).isEqualTo(joueur1);
  }

  @Test
  void devrait_accorder_un_bonus_de_3_pour_31() {
    Joueur joueur1 = unJoueurAvec(main(Carte.VALET_EPEE, Carte.AS_BATON, Carte.VALET_BATON, Carte.VALET_COUPE));

    int pointsBonus = new Jeu().pointsBonus(joueur1);

    assertThat(pointsBonus).isEqualTo(3);
  }

  @Test
  void devrait_accorder_un_bonus_de_2_pour_32() {
    Joueur joueur1 = unJoueurAvec(main(Carte.VALET_EPEE, Carte.DEUX_BATON, Carte.VALET_BATON, Carte.VALET_COUPE));

    int pointsBonus = new Jeu().pointsBonus(joueur1);

    assertThat(pointsBonus).isEqualTo(2);
  }
}