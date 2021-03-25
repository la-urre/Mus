package com.montaury.mus.jeu;

import com.montaury.mus.jeu.equipe.Equipe;
import com.montaury.mus.jeu.joueur.AffichageEvenementsDeJeu;
import com.montaury.mus.jeu.joueur.InterfaceJoueur;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.equipe.Opposants;
import com.montaury.mus.jeu.tour.phases.dialogue.Gehiago;
import com.montaury.mus.jeu.tour.phases.dialogue.Hordago;
import com.montaury.mus.jeu.tour.phases.dialogue.Imido;
import com.montaury.mus.jeu.tour.phases.dialogue.Kanta;
import com.montaury.mus.jeu.tour.phases.dialogue.Paso;
import com.montaury.mus.jeu.tour.phases.dialogue.Tira;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MancheTest {

  private Manche manche;

  @BeforeEach
  void setUp() {
    interfaceJoueur1 = mock(InterfaceJoueur.class);
    interfaceJoueur2 = mock(InterfaceJoueur.class);
    interfaceJoueur3 = mock(InterfaceJoueur.class);
    interfaceJoueur4 = mock(InterfaceJoueur.class);

    joueur1 = new Joueur("J1", interfaceJoueur1);
    joueur2 = new Joueur("J2", interfaceJoueur2);
    joueur3 = new Joueur("J3", interfaceJoueur3);
    joueur4 = new Joueur("J4", interfaceJoueur4);

    equipe1 = new Equipe(joueur1,joueur2);
    equipe2 = new Equipe(joueur3,joueur4);

    opposants = new Opposants(equipe1, equipe2);
    manche = new Manche(mock(AffichageEvenementsDeJeu.class));
  }

  @Test
  void devrait_terminer_la_manche_si_hordago_au_grand() {
    when(interfaceJoueur1.faireChoixParmi(any())).thenReturn(new Hordago());
    when(interfaceJoueur2.faireChoixParmi(any())).thenReturn(new Kanta());
    when(interfaceJoueur3.faireChoixParmi(any())).thenReturn(new Kanta());
    when(interfaceJoueur4.faireChoixParmi(any())).thenReturn(new Kanta());

    Manche.Resultat resultat = manche.jouer(opposants);

    assertThat(resultat.vainqueur()).isNotNull();
    assertThat(resultat.pointsVaincu()).isZero();
  }

  @Test
  void devrait_terminer_la_manche_si_un_joueur_a_40_points() {
    when(interfaceJoueur1.faireChoixParmi(any())).thenReturn(new Imido(), new Gehiago(2));
    when(interfaceJoueur2.faireChoixParmi(any())).thenReturn(new Imido(), new Gehiago(2));
    when(interfaceJoueur3.faireChoixParmi(any())).thenReturn(new Imido(), new Gehiago(2));
    when(interfaceJoueur4.faireChoixParmi(any())).thenReturn(new Gehiago(40), new Tira());

    Manche.Resultat resultat = manche.jouer(opposants);

    assertThat(resultat.vainqueur()).isEqualTo(joueur2);
    assertThat(resultat.pointsVaincu()).isZero();
  }

  @Test
  void devrait_changer_l_ordre_des_opposants_a_la_fin_du_tour() {

    when(interfaceJoueur1.faireChoixParmi(any())).thenReturn(new Hordago());
    when(interfaceJoueur2.faireChoixParmi(any())).thenReturn(new Kanta());
    when(interfaceJoueur3.faireChoixParmi(any())).thenReturn(new Kanta());
    when(interfaceJoueur4.faireChoixParmi(any())).thenReturn(new Kanta());

    manche.jouer(opposants);

    assertThat(opposants.dansLOrdre()).containsExactly(joueur4,joueur1,joueur3,joueur2);
  }

  private InterfaceJoueur interfaceJoueur1;
  private InterfaceJoueur interfaceJoueur2;
  private InterfaceJoueur interfaceJoueur3;
  private InterfaceJoueur interfaceJoueur4;
  private Joueur joueur1;
  private Joueur joueur2;
  private Joueur joueur3;
  private Joueur joueur4;
  private Equipe equipe1;
  private Equipe equipe2;
  private Opposants opposants;

}