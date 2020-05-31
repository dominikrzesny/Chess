package Plansza;

import Figury.Figura;
import Plansza.Pole;

public class PolePuste extends Pole {

    public PolePuste(int wspolrzedna) {
        super(wspolrzedna);
    }

    @Override
    public Figura zwrocFigure() {
        return null;
    }

    @Override
    public boolean czyPoleZajęte() {
        return false;
    }
}
