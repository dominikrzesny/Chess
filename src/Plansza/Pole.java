package Plansza;
import Figury.Figura;

import java.util.HashMap;
import java.util.Map;

public abstract class Pole {

    public int wspolrzednaPola;
    public Figura figura;

    /*public static Map<Integer,PolePuste> pustePolaPlanszy = potencjalnePustePolaPlanszy();

    public static Map<Integer,PolePuste> potencjalnePustePolaPlanszy(){

        Map<Integer,PolePuste> mapa = new HashMap<>();
        for(int i =0;i<64;i++){
            mapa.put(i,new PolePuste(i));
        }
        return mapa;
    };*/


    public Pole(int wspolrzedna){
        this.wspolrzednaPola = wspolrzedna;
    }

    public abstract Figura zwrocFigure();

    public abstract boolean czyPoleZajęte();
}
