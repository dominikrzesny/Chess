package Figury;

import Klasy_zarzadzajace.Gracz;
import Plansza.Plansza;

import java.io.IOException;

public abstract class Ruch {

    public Plansza plansza;
    public Figura figuraWykonujacaRuch;
    public int wspolrzednaDocelowaruchu;


    public Ruch(Plansza plansza,Figura figuraWykonujacaRuch, int wspolrzednaDocelowaRuchu){
        this.plansza = plansza;
        this.figuraWykonujacaRuch = figuraWykonujacaRuch;
        this.wspolrzednaDocelowaruchu = wspolrzednaDocelowaRuchu;
    }

    public abstract void egzekwujRuch() throws IOException;
    public abstract void wyslijRuchDoSerwera(Gracz gracz, int wspolrzednaFigury, int wspolrzednaDocelowa);

    public abstract void czekajNaRuchPrzeciwnika();


}
