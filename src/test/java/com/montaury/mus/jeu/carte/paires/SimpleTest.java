package com.montaury.mus.jeu.carte.paires;

import com.montaury.mus.jeu.carte.ValeurCarte;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleTest {
  @Test
  void doivent_fournir_un_bonus_de_1() {
    Simple paireSimple = new Simple(ValeurCarte.CINQ);

    int pointsBonus = paireSimple.pointsBonus();

    assertThat(pointsBonus).isEqualTo(1);
  }

  @Test
  void sont_meilleurs_qu_une_paire_simple_de_rang_inferieur() {
    Simple paireSimple = new Simple(ValeurCarte.CINQ);

    boolean meilleureOuEgale = paireSimple.estMeilleureOuEgaleA(new Simple(ValeurCarte.QUATRE));

    assertThat(meilleureOuEgale).isTrue();
  }

  @Test
  void sont_egales_a_une_paire_simple_de_meme_rang() {
    Simple paireSimple = new Simple(ValeurCarte.CINQ);

    boolean meilleureOuEgale = paireSimple.estMeilleureOuEgaleA(new Simple(ValeurCarte.CINQ));

    assertThat(meilleureOuEgale).isTrue();
  }

  @Test
  void sont_moins_bonnes_que_des_meds() {
    Simple paireSimple = new Simple(ValeurCarte.ROI);

    boolean meilleureOuEgale = paireSimple.estMeilleureOuEgaleA(new Meds(ValeurCarte.AS));

    assertThat(meilleureOuEgale).isFalse();
  }

  @Test
  void sont_moins_bonnes_que_des_doubles() {
    Simple paireSimple = new Simple(ValeurCarte.ROI);

    boolean meilleureOuEgale = paireSimple.estMeilleureOuEgaleA(new Doubles(new Simple(ValeurCarte.AS), new Simple(ValeurCarte.AS)));

    assertThat(meilleureOuEgale).isFalse();
  }
}
