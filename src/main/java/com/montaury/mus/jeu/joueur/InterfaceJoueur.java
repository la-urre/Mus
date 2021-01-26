package com.montaury.mus.jeu.joueur;

import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.tour.phases.dialogue.Choix;
import com.montaury.mus.jeu.tour.phases.dialogue.TypeChoix;
import java.util.List;

public interface InterfaceJoueur {
  void nouvelleMain(Main main);

  boolean veutAllerMus();

  List<Carte> cartesAJeter();

  Choix faireChoixParmi(List<TypeChoix> choixPossibles);
}
