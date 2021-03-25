package com.montaury.mus.jeu.tour.phases;

import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.carte.Defausse;
import com.montaury.mus.jeu.equipe.Equipe;
import com.montaury.mus.jeu.joueur.AffichageConsoleEvenementsDeJeu;
import com.montaury.mus.jeu.joueur.InterfaceJoueur;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.equipe.Opposants;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.montaury.mus.jeu.carte.Fixtures.paquetEntierCroissant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MusTest {

  @BeforeEach
  void setUp() {
    defausse = new Defausse();
    mus = new Mus(paquetEntierCroissant(), defausse, new AffichageConsoleEvenementsDeJeu(joueur1));
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
  }

  @Test
  void devrait_distribuer_quatre_cartes_a_chaque_joueur() {
    when(interfaceJoueur1.veutAllerMus()).thenReturn(false);

    mus.jouer(opposants);

    assertThat(joueur1.main().cartes()).containsExactly(Carte.AS_BATON, Carte.AS_COUPE, Carte.AS_EPEE, Carte.AS_PIECE);
    assertThat(joueur2.main().cartes()).containsExactly(Carte.TROIS_BATON, Carte.TROIS_COUPE, Carte.TROIS_EPEE, Carte.TROIS_PIECE);
    assertThat(joueur3.main().cartes()).containsExactly(Carte.DEUX_BATON, Carte.DEUX_COUPE, Carte.DEUX_EPEE, Carte.DEUX_PIECE);
    assertThat(joueur4.main().cartes()).containsExactly(Carte.QUATRE_BATON, Carte.QUATRE_COUPE, Carte.QUATRE_EPEE, Carte.QUATRE_PIECE);
  }

  @Test
  void devrait_se_terminer_si_le_joueur_1_veut_sortir() {
    when(interfaceJoueur1.veutAllerMus()).thenReturn(false);

    mus.jouer(opposants);

    verify(interfaceJoueur3, times(0)).veutAllerMus();
  }
  /* l equipié du joueur etant aleatire pour le choix de mus pas de test car forcement true si bot plus intelligent change
  @Test
  void devrait_se_terminer_si_le_joueur_2_veut_sortir() {
    when(interfaceJoueur1.veutAllerMus()).thenReturn(true);
    when(interfaceJoueur3.veutAllerMus()).thenReturn(true);
    when(interfaceJoueur2.veutAllerMus()).thenReturn(false);

    mus.jouer(opposants);

    verify(interfaceJoueur4, times(0)).veutAllerMus();
  }*/

  @Test
  void devrait_se_terminer_si_le_joueur_3_veut_sortir() {
    when(interfaceJoueur1.veutAllerMus()).thenReturn(true);
    when(interfaceJoueur3.veutAllerMus()).thenReturn(false);
    mus.jouer(opposants);

    verify(interfaceJoueur2, times(0)).veutAllerMus();
  }

  @Test
  void devrait_se_terminer_si_le_joueur_4_veut_sortir() {
    when(interfaceJoueur1.veutAllerMus()).thenReturn(true);
    when(interfaceJoueur2.veutAllerMus()).thenReturn(true);
    when(interfaceJoueur3.veutAllerMus()).thenReturn(true);
    when(interfaceJoueur4.veutAllerMus()).thenReturn(false);
    mus.jouer(opposants);

    verify(interfaceJoueur1, times(0)).cartesAJeter();
  }

  @Test
  void devrait_demander_les_cartes_a_jeter_aux_joueurs_s_ils_vont_mus() {
    when(interfaceJoueur1.veutAllerMus()).thenReturn(true,false);
    when(interfaceJoueur2.veutAllerMus()).thenReturn(true);
    when(interfaceJoueur3.veutAllerMus()).thenReturn(true);
    when(interfaceJoueur4.veutAllerMus()).thenReturn(true);
    mus.jouer(opposants);

    verify(interfaceJoueur1, times(1)).cartesAJeter();
    verify(interfaceJoueur2, times(1)).cartesAJeter();
    verify(interfaceJoueur3, times(1)).cartesAJeter();
    verify(interfaceJoueur4, times(1)).cartesAJeter();
  }

  @Test
  void devrait_defausser_les_cartes_a_jeter_si_les_joueurs_vont_mus() {
    when(interfaceJoueur1.veutAllerMus()).thenReturn(true, false);
    when(interfaceJoueur1.cartesAJeter()).thenReturn(List.of(Carte.AS_COUPE));
    when(interfaceJoueur2.veutAllerMus()).thenReturn(true);
    when(interfaceJoueur2.cartesAJeter()).thenReturn(List.of(Carte.DEUX_COUPE));
    when(interfaceJoueur3.veutAllerMus()).thenReturn(true);
    when(interfaceJoueur3.cartesAJeter()).thenReturn(List.of(Carte.TROIS_COUPE));
    when(interfaceJoueur4.veutAllerMus()).thenReturn(true);
    when(interfaceJoueur4.cartesAJeter()).thenReturn(List.of(Carte.QUATRE_COUPE));

    mus.jouer(opposants);

    assertThat(defausse.reprendreCartes()).containsExactly(Carte.AS_COUPE, Carte.TROIS_COUPE, Carte.DEUX_COUPE,Carte.QUATRE_COUPE);
  }

  @Test
  void devrait_distribuer_des_cartes_pour_remplacer_les_cartes_jetees_si_les_joueurs_vont_mus() {
    when(interfaceJoueur1.veutAllerMus()).thenReturn(true, false);
    when(interfaceJoueur1.cartesAJeter()).thenReturn(List.of(Carte.AS_COUPE));
    when(interfaceJoueur2.veutAllerMus()).thenReturn(true);
    when(interfaceJoueur2.cartesAJeter()).thenReturn(List.of(Carte.TROIS_COUPE));
    when(interfaceJoueur3.veutAllerMus()).thenReturn(true);
    when(interfaceJoueur3.cartesAJeter()).thenReturn(List.of(Carte.DEUX_COUPE));
    when(interfaceJoueur4.veutAllerMus()).thenReturn(true);
    when(interfaceJoueur4.cartesAJeter()).thenReturn(List.of(Carte.QUATRE_COUPE));

    mus.jouer(opposants);

    assertThat(joueur1.main().cartes()).containsExactly(Carte.AS_BATON, Carte.AS_EPEE, Carte.AS_PIECE , Carte.CINQ_BATON);
    assertThat(joueur2.main().cartes()).containsExactly(Carte.TROIS_BATON, Carte.TROIS_EPEE, Carte.TROIS_PIECE , Carte.CINQ_EPEE);
    assertThat(joueur3.main().cartes()).containsExactly( Carte.DEUX_BATON, Carte.DEUX_EPEE, Carte.DEUX_PIECE , Carte.CINQ_COUPE);
    assertThat(joueur4.main().cartes()).containsExactly(Carte.QUATRE_BATON, Carte.QUATRE_EPEE, Carte.QUATRE_PIECE , Carte.CINQ_PIECE);
  }

  private Mus mus;
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
  private Defausse defausse;
}