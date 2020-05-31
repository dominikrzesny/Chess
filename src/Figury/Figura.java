package Figury;

import Figury.Ruch;
import Plansza.Plansza;
import java.util.List;

public abstract class Figura {

    public int wspolrzedneFigury;
    public Kolor kolorFigury;
    public String sciezkaObrazu;

    public Figura(Kolor KolorFigury, int wspolrzedneFigury, String sciezkaObrazu){
        this.kolorFigury = KolorFigury;
        this.wspolrzedneFigury = wspolrzedneFigury;
        this.sciezkaObrazu = sciezkaObrazu;
    }

    public abstract List<Ruch> wyznaczDozwoloneRuchy(Plansza plansza);

}
