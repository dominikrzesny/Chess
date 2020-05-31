package Figury;

import Plansza.Plansza;

import Plansza.Pole;
import Plansza.Ograniczenia;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Wieża extends Figura {

    public int[] mozliwosciPrzesunieciaNaPlanszy = {-1,8,-8,1};

    public Wieża(Kolor KolorFigury, int wspolrzedneFigury,String sciezkaObrazu) {
        super(KolorFigury, wspolrzedneFigury, sciezkaObrazu );
    }

    @Override
    public List<Ruch> wyznaczDozwoloneRuchy(Plansza plansza) {
        List<Ruch> listaDozwolonychRuchow = new ArrayList<>();

        for(int mozliwoscPrzesuniecia:mozliwosciPrzesunieciaNaPlanszy){
            int wspolrzednaPolaDocelowego=wspolrzedneFigury;
            while(Ograniczenia.czyDozwolonaWspolrzedna(wspolrzednaPolaDocelowego)){

                if((Ograniczenia.czyPierwszaKolumna(wspolrzednaPolaDocelowego)&& mozliwoscPrzesuniecia==-1) ||
                        (Ograniczenia.czyOsmaKolumna(wspolrzednaPolaDocelowego)&& mozliwoscPrzesuniecia==1))
                {
                    break;
                }

                wspolrzednaPolaDocelowego+=mozliwoscPrzesuniecia;
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
                        break;
                    }
                }
            }
        }

        return listaDozwolonychRuchow;
    }
}
