package com.montaury.mus.jeu.tour;

import com.montaury.mus.jeu.Manche;
import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.carte.Defausse;
import com.montaury.mus.jeu.equipe.Equipe;
import com.montaury.mus.jeu.joueur.AffichageEvenementsDeJeu;
import com.montaury.mus.jeu.joueur.InterfaceJoueur;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.equipe.Opposants;
import com.montaury.mus.jeu.tour.phases.dialogue.Gehiago;
import com.montaury.mus.jeu.tour.phases.dialogue.Hordago;
import com.montaury.mus.jeu.tour.phases.dialogue.Idoki;
import com.montaury.mus.jeu.tour.phases.dialogue.Imido;
import com.montaury.mus.jeu.tour.phases.dialogue.Kanta;
import com.montaury.mus.jeu.tour.phases.dialogue.Paso;
import com.montaury.mus.jeu.tour.phases.dialogue.Tira;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.montaury.mus.jeu.carte.Fixtures.paquetAvec;
import static com.montaury.mus.jeu.carte.Fixtures.paquetEntierCroissant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TourTest {

  @BeforeEach
  void setUp() {
    interfaceJoueurEsku = mock(InterfaceJoueur.class);
    interfacejoueurAmi = mock(InterfaceJoueur.class);
    interfacejoueurEnemie = mock(InterfaceJoueur.class);
    interfaceJoueurZaku = mock(InterfaceJoueur.class);
    joueurEsku = new Joueur("J1", interfaceJoueurEsku);
    joueurAmi = new Joueur("J2", interfaceJoueurZaku);
    joueurEnemie = new Joueur("J3", interfaceJoueurZaku);
    joueurZaku = new Joueur("J4", interfaceJoueurZaku);
    equipe1= new Equipe(joueurEsku,joueurAmi);
    equipe2=new Equipe( joueurEnemie,joueurZaku);
    opposants = new Opposants(equipe1,equipe2);
    score = new Manche.Score(opposants);
    evenementsDeJeu = mock(AffichageEvenementsDeJeu.class);
    tour = new Tour(evenementsDeJeu, paquetEntierCroissant(), new Defausse());
  }

  @Test
  void devrait_donner_tous_les_points_au_joueur_1_si_tous_les_joueurs_font_tira() {
    when(interfaceJoueurEsku.faireChoixParmi(any())).thenReturn(new Imido());
    when(interfaceJoueurZaku.faireChoixParmi(any())).thenReturn(new Tira());
    when(interfacejoueurAmi.faireChoixParmi(any())).thenReturn(new Tira());
    when(interfacejoueurEnemie.faireChoixParmi(any())).thenReturn(new Tira());

    tour.jouer(opposants, score);

    assertThat(score.vainqueur()).isEmpty();
    assertThat(score.scoreParJoueur()).containsEntry(joueurEsku, 8);
    assertThat(score.scoreParJoueur()).containsEntry(joueurZaku, 0);
    assertThat(score.scoreParJoueur()).containsEntry(joueurAmi, 0);
    assertThat(score.scoreParJoueur()).containsEntry(joueurEnemie, 0);
  }

  @Test
  void devrait_repartir_les_points_si_tout_est_paso() {
    when(interfaceJoueurEsku.faireChoixParmi(any())).thenReturn(new Paso());
    when(interfaceJoueurZaku.faireChoixParmi(any())).thenReturn(new Paso());
    when(interfacejoueurAmi.faireChoixParmi(any())).thenReturn(new Paso());
    when(interfacejoueurEnemie.faireChoixParmi(any())).thenReturn(new Paso());
    tour.jouer(opposants, score);

    assertThat(score.vainqueur()).isEmpty();
    assertThat(score.scoreParJoueur()).containsEntry(joueurEsku, 1);
    assertThat(score.scoreParJoueur()).containsEntry(joueurAmi, 5);
    assertThat(score.scoreParJoueur()).containsEntry(joueurEnemie, 5);
    assertThat(score.scoreParJoueur()).containsEntry(joueurZaku, 5);
  }

  @Test
  void devrait_faire_gagner_le_joueur_zaku_si_hordago_au_grand() {
    when(interfaceJoueurEsku.faireChoixParmi(any())).thenReturn(new Hordago());
    when(interfaceJoueurZaku.faireChoixParmi(any())).thenReturn(new Kanta());
    when(interfacejoueurAmi.faireChoixParmi(any())).thenReturn(new Kanta());
    when(interfacejoueurEnemie.faireChoixParmi(any())).thenReturn(new Kanta());
    tour.jouer(opposants, score);

    assertThat(score.vainqueur()).contains(joueurZaku);
    assertThat(score.scoreParJoueur()).containsEntry(joueurEsku, 0);
    assertThat(score.scoreParJoueur()).containsEntry(joueurZaku, 40);
    assertThat(score.scoreParJoueur()).containsEntry(joueurAmi, 0);
    assertThat(score.scoreParJoueur()).containsEntry(joueurEnemie, 0);
  }

  @Test
  void devrait_partager_les_points_si_tout_est_idoki() {
    when(interfaceJoueurEsku.faireChoixParmi(any())).thenReturn(new Imido());
    when(interfaceJoueurZaku.faireChoixParmi(any())).thenReturn(new Idoki());
    when(interfacejoueurAmi.faireChoixParmi(any())).thenReturn(new Idoki());
    when(interfacejoueurEnemie.faireChoixParmi(any())).thenReturn(new Idoki());
    //24point en tous

    tour.jouer(opposants, score);

    assertThat(score.vainqueur()).isEmpty();
    assertThat(score.scoreParJoueur()).containsEntry(joueurEsku, 3);
    assertThat(score.scoreParJoueur()).containsEntry(joueurZaku, 10);
    assertThat(score.scoreParJoueur()).containsEntry(joueurAmi, 10);
    assertThat(score.scoreParJoueur()).containsEntry(joueurEnemie, 10);
  }

  @Test
  void devrait_partager_les_points_si_tout_est_gehiago_puis_idoki() {
    when(interfaceJoueurEsku.faireChoixParmi(any())).thenReturn(new Imido(), new Idoki(), new Imido(), new Idoki(), new Imido(), new Idoki(), new Imido(), new Idoki());
    when(interfaceJoueurZaku.faireChoixParmi(any())).thenReturn(new Gehiago(2));
    when(interfacejoueurAmi.faireChoixParmi(any())).thenReturn(new Gehiago(2));
    when(interfacejoueurEnemie.faireChoixParmi(any())).thenReturn(new Gehiago(2));


    tour.jouer(opposants, score);

    assertThat(score.vainqueur()).isEmpty();
    assertThat(score.scoreParJoueur()).containsEntry(joueurEsku, 4);
    assertThat(score.scoreParJoueur()).containsEntry(joueurZaku, 16);
    assertThat(score.scoreParJoueur()).containsEntry(joueurAmi, 4);
    assertThat(score.scoreParJoueur()).containsEntry(joueurEnemie, 16);
  }

  @Test
  void devrait_privilegier_le_joueur_esku_si_les_mains_sont_identiques() {
    when(interfaceJoueurEsku.faireChoixParmi(any())).thenReturn(new Imido());
    when(interfaceJoueurZaku.faireChoixParmi(any())).thenReturn(new Idoki());
    when(interfacejoueurAmi.faireChoixParmi(any())).thenReturn(new Idoki());
    when(interfacejoueurEnemie.faireChoixParmi(any())).thenReturn(new Idoki());

    Tour tour = new Tour(evenementsDeJeu, paquetAvec  ( Carte.AS_BATON, Carte.DEUX_BATON, Carte.TROIS_BATON, Carte.QUATRE_BATON,
                                                        Carte.AS_COUPE, Carte.DEUX_COUPE, Carte.TROIS_COUPE, Carte.QUATRE_COUPE,
                                                        Carte.AS_EPEE, Carte.DEUX_EPEE, Carte.TROIS_EPEE, Carte.QUATRE_EPEE,
                                                        Carte.AS_PIECE, Carte.DEUX_PIECE, Carte.TROIS_PIECE, Carte.QUATRE_PIECE
                                                       ), new Defausse()
                        );

    tour.jouer(opposants, score);

    assertThat(score.vainqueur()).isEmpty();
    assertThat(score.scoreParJoueur()).containsEntry(joueurEsku, 17);
    assertThat(score.scoreParJoueur()).containsEntry(joueurZaku, 0);
    assertThat(score.scoreParJoueur()).containsEntry(joueurAmi, 0);
    assertThat(score.scoreParJoueur()).containsEntry(joueurEnemie, 0);
  }

  private InterfaceJoueur interfaceJoueurEsku;
  private InterfaceJoueur interfaceJoueurZaku;
  private InterfaceJoueur interfacejoueurAmi;
  private InterfaceJoueur interfacejoueurEnemie;
  private Joueur joueurEsku;
  private Joueur joueurZaku;
  private Joueur joueurAmi;
  private Joueur joueurEnemie;
  Equipe equipe1;
  Equipe equipe2;
  private Opposants opposants;
  private Manche.Score score;
  private AffichageEvenementsDeJeu evenementsDeJeu;
  private Tour tour;
}
