package com.montaury.mus.jeu;

import com.montaury.mus.jeu.equipe.Equipe;
import com.montaury.mus.jeu.joueur.AffichageEvenementsDeJeu;
import com.montaury.mus.jeu.joueur.InterfaceJoueur;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.equipe.Opposants;
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
    partie = new Partie(mock(AffichageEvenementsDeJeu.class));
  }

  @Test
  void devrait_faire_gagner_le_premier_joueur_a_3_manches() {
    when(interfaceJoueur1.faireChoixParmi(any())).thenReturn(new Hordago());
    when(interfaceJoueur2.faireChoixParmi(any())).thenReturn(new Kanta());
    when(interfaceJoueur3.faireChoixParmi(any())).thenReturn(new Kanta());
    when(interfaceJoueur4.faireChoixParmi(any())).thenReturn(new Kanta());

    Partie.Resultat resultat = partie.jouer(opposants);

    assertThat(resultat.vainqueur()).isNotNull();
    assertThat(resultat.score().resultatManches()).hasSizeGreaterThanOrEqualTo(3);
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
  private Partie partie;
}