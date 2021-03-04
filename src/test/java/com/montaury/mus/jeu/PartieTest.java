package com.montaury.mus.jeu;

import com.montaury.mus.jeu.joueur.*;
import com.montaury.mus.jeu.tour.phases.dialogue.Hordago;
import com.montaury.mus.jeu.tour.phases.dialogue.Kanta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PartieTest {

  @BeforeEach
  void setUp() {
    interfaceJoueurEsku = mock(InterfaceJoueur.class);
    interfaceJoueurZaku = mock(InterfaceJoueur.class);
    interfaceOrdi1 = mock(InterfaceJoueur.class); ;
    interfaceOrdi2 = mock(InterfaceJoueur.class);
    Joueur joueurEsku = new Joueur("J1", interfaceJoueurEsku);
    Joueur joueurZaku = new Joueur("J2", interfaceJoueurZaku);
    joueurOrdi1 =new Joueur("J2", interfaceOrdi1);
    joueurOrdi2 =new Joueur("J2", interfaceOrdi2);
    equipe1 = new Equipe(joueurEsku,joueurOrdi1,"e1");
    equipe2 = new Equipe(joueurOrdi2,joueurZaku,"e2");
    opposants = new Opposants(equipe1,equipe2);
    partie = new Partie(mock(AffichageEvenementsDeJeu.class));
  }

  @Test
  void devrait_faire_gagner_le_premier_joueur_a_3_manches() {
    when(interfaceJoueurEsku.faireChoixParmi(any())).thenReturn(new Hordago());
    when(interfaceJoueurZaku.faireChoixParmi(any())).thenReturn(new Kanta());
    when(interfaceOrdi1.faireChoixParmi(any())).thenReturn(new Kanta());
    when(interfaceOrdi2.faireChoixParmi(any())).thenReturn(new Kanta());

    Partie.Resultat resultat = partie.jouer(opposants);

    assertThat(resultat.vainqueur()).isNotNull();
    assertThat(resultat.score().resultatManches()).hasSizeGreaterThanOrEqualTo(3);
  }

  private InterfaceJoueur interfaceJoueurEsku;
  private InterfaceJoueur interfaceJoueurZaku;
  private InterfaceJoueur interfaceOrdi1;
  private InterfaceJoueur interfaceOrdi2;
  private Joueur joueurOrdi1;
  private Joueur joueurOrdi2;
  private Equipe equipe1;
  private Equipe equipe2;
  private Opposants opposants;
  private Partie partie;
}