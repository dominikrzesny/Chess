package Figury;

import Plansza.Plansza;
import Plansza.Ograniczenia;
import Plansza.Pole;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Krol extends Figura {

    public int[] mozliwosciPrzesunieciaNaPlanszy = {-1,1,8,-8,-7,7,-9,9};

    public Krol(Kolor KolorFigury, int wspolrzedneFigury, String sciezkaObrazu) {
        super(KolorFigury, wspolrzedneFigury, sciezkaObrazu);
    }

    @Override
    public List<Ruch> wyznaczDozwoloneRuchy(Plansza plansza) {

        List<Ruch> listaDozwolonychRuchow = new ArrayList<>();

        for(int mozliwoscPrzesuniecia : mozliwosciPrzesunieciaNaPlanszy){
            int wspolrzednaPolaDocelowego;

            if((Ograniczenia.czyPierwszaKolumna(wspolrzedneFigury)&& mozliwoscPrzesuniecia==-9) ||
                    (Ograniczenia.czyPierwszaKolumna(wspolrzedneFigury)&& mozliwoscPrzesuniecia==7) ||
                    (Ograniczenia.czyPierwszaKolumna(wspolrzedneFigury)&& mozliwoscPrzesuniecia==-1) ||
                    (Ograniczenia.czyOsmaKolumna(wspolrzedneFigury)&& mozliwoscPrzesuniecia==-7) ||
                    (Ograniczenia.czyOsmaKolumna(wspolrzedneFigury)&& mozliwoscPrzesuniecia==1) ||
                    (Ograniczenia.czyOsmaKolumna(wspolrzedneFigury)&& mozliwoscPrzesuniecia==9))
            {
               continue;
            }

            wspolrzednaPolaDocelowego = mozliwoscPrzesuniecia + wspolrzedneFigury;

            if(Ograniczenia.czyDozwolonaWspolrzedna(wspolrzednaPolaDocelowego)){

                Pole pole = plansza.zwrocPole(wspolrzednaPolaDocelowego);
                if(!pole.czyPoleZajęte()){
                    listaDozwolonychRuchow.add(new Posuniecie(plansza,this,wspolrzednaPolaDocelowego));
                }
                else{
                    Figura figuraOkupujacaPole = pole.zwrocFigure();
                    if(figuraOkupujacaPole.kolorFigury != this.kolorFigury){
                        listaDozwolonychRuchow.add(new Zbicie(plansza,this,wspolrzednaPolaDocelowego,figuraOkupujacaPole));
                    }
                }

            }

        }

        return listaDozwolonychRuchow;
    }
}
