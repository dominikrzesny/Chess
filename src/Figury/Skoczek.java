package Figury;

import Plansza.Pole;
import Plansza.Plansza;
import Plansza.Ograniczenia;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Skoczek extends Figura {

    public int[] mozliwosciPrzesunieciaNaPlanszy = {6,10,15,17,-6,-10,-15,-17};

    public Skoczek(Kolor KolorFigury, int wspolrzedneFigury, String sciezkaObrazu) {
        super(KolorFigury, wspolrzedneFigury, sciezkaObrazu);
    }

    @Override
    public List<Ruch> wyznaczDozwoloneRuchy(Plansza plansza) {

        List<Ruch> listaDozwolonychRuchow = new ArrayList<>();
        for(int mozliwoscPrzesuniecia : mozliwosciPrzesunieciaNaPlanszy )
        {

         if((Ograniczenia.czyPierwszaKolumna(wspolrzedneFigury) && (mozliwoscPrzesuniecia==-10 || mozliwoscPrzesuniecia==-17 || mozliwoscPrzesuniecia==6 || mozliwoscPrzesuniecia==15)) ||
                 (Ograniczenia.czyDrugaKolumna(wspolrzedneFigury) && (mozliwoscPrzesuniecia==-10 || mozliwoscPrzesuniecia==6))||
            (Ograniczenia.czySiodmaKolumna(wspolrzedneFigury) && (mozliwoscPrzesuniecia==10 || mozliwoscPrzesuniecia==-6)) ||
                 (Ograniczenia.czyOsmaKolumna(wspolrzedneFigury) && (mozliwoscPrzesuniecia==10 || mozliwoscPrzesuniecia==17 || mozliwoscPrzesuniecia==-6 || mozliwoscPrzesuniecia==-15)))
         {
             continue;
         }

         int wspolrzednaPolaDocelowego = wspolrzedneFigury+mozliwoscPrzesuniecia;

         if(Ograniczenia.czyDozwolonaWspolrzedna(wspolrzednaPolaDocelowego)){
             Pole pole = plansza.zwrocPole(wspolrzednaPolaDocelowego);
             if(!pole.czyPoleZajęte()){
                 listaDozwolonychRuchow.add(new Posuniecie(plansza,this,wspolrzednaPolaDocelowego));
             }
             else {
                 Figura figura = pole.zwrocFigure();
                 if (figura.kolorFigury != this.kolorFigury) {
                     listaDozwolonychRuchow.add(new Zbicie(plansza, this, wspolrzednaPolaDocelowego, figura));
                 }
             }
         }
        }

        return listaDozwolonychRuchow;
    }
}
