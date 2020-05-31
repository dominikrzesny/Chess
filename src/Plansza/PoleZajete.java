package Plansza;

import Figury.Figura;


public class PoleZajete extends Pole {
    public PoleZajete(int wspolrzedna, Figura figura) {
        super(wspolrzedna);
        this.figura = figura;
    }

    @Override
    public Figura zwrocFigure() {
        return this.figura;
    }

    @Override
    public boolean czyPoleZajęte() {
        return true;
    }
}
