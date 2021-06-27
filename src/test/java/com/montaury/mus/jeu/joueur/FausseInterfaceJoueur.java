package com.montaury.mus.jeu.joueur;

import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Choix;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.TypeChoix;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FausseInterfaceJoueur implements InterfaceJoueur {
  private final Iterator<Choix> iterateurChoix;
  private boolean mus;
  public Main main;

  public FausseInterfaceJoueur(Choix... choix) {
    this.iterateurChoix = Arrays.stream(choix).iterator();
  }

  public void veutAllerMus(boolean mus) {
    this.mus = mus;
  }

  @Override
  public boolean veutAllerMus() {
    return mus;
  }

  @Override
  public List<Carte> cartesAJeter() {
    return null;
  }

  @Override
  public Choix faireChoixParmi(List<TypeChoix> choixPossibles) {
    return iterateurChoix.next();
  }

  @Override
  public void nouvelleMain(Main main) {
    this.main = main;
  }
}
