package com.montaury.mus.jeu.joueur;

import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Choix;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Gehiago;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Hordago;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Idoki;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Imido;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Kanta;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Paso;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Tira;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.TypeChoix;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InterfaceJoueurOrdinateur implements InterfaceJoueur {
  private Main main;
  private final Random random = new Random();

  @Override
  public void nouvelleMain(Main main) {
    this.main = main;
  }

  @Override
  public boolean veutAllerMus() {
    return random.nextBoolean();
  }

  @Override
  public List<Carte> cartesAJeter() {
    return new ArrayList<>(main.cartes());
  }

  @Override
  public Choix faireChoixParmi(List<TypeChoix> choixPossibles) {
    var typeChoix = choixPossibles.get(random.nextInt(choixPossibles.size()));
    if (typeChoix == TypeChoix.PASO) return new Paso();
    if (typeChoix == TypeChoix.IMIDO) return new Imido();
    if (typeChoix == TypeChoix.HORDAGO) return new Hordago();
    if (typeChoix == TypeChoix.IDOKI) return new Idoki();
    if (typeChoix == TypeChoix.TIRA) return new Tira();
    if (typeChoix == TypeChoix.GEHIAGO) return new Gehiago(2);
    if (typeChoix == TypeChoix.KANTA) return new Kanta();
    return null;
  }
}
