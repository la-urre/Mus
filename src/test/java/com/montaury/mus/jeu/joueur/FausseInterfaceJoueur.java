package com.montaury.mus.jeu.joueur;

import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.tour.phases.dialogue.Choix;
import com.montaury.mus.jeu.tour.phases.dialogue.TypeChoix;
import java.util.List;

public class FausseInterfaceJoueur implements InterfaceJoueur {
  private boolean mus;
  public Main main;

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
    return null;
  }

  @Override
  public void nouvelleMain(Main main) {
    this.main = main;
  }
}
