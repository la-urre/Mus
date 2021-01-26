package com.montaury.mus.jeu.tour.phases.dialogue;

public class Imido extends Choix {
  public Imido() {
    this(2);
  }

  public Imido(int mise) {
    super(TypeChoix.IMIDO, mise);
  }
}
