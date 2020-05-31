package Figury;
import Plansza.Pole;
import Plansza.Plansza;
import Plansza.Ograniczenia;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Goniec extends Figura {

    public int[] mozliwosciPrzesunieciaNaPlanszy = {-9,-7,7,9};

    public Goniec(Kolor KolorFigury, int wspolrzedneFigury, String sciezkaObrazu) {
        super(KolorFigury, wspolrzedneFigury, sciezkaObrazu);

    }

    @Override
    public List<Ruch> wyznaczDozwoloneRuchy(Plansza plansza) {

        List<Ruch> listaDozwolonychRuchow = new ArrayList<>();

        for(int mozliwoscPrzesuniecia:mozliwosciPrzesunieciaNaPlanszy){
            int wspolrzednaPolaDocelowego=wspolrzedneFigury;



            while(Ograniczenia.czyDozwolonaWspolrzedna(wspolrzednaPolaDocelowego)){

                if((Ograniczenia.czyPierwszaKolumna(wspolrzednaPolaDocelowego)&& mozliwoscPrzesuniecia==-9) ||
                        (Ograniczenia.czyPierwszaKolumna(wspolrzednaPolaDocelowego)&& mozliwoscPrzesuniecia==7) ||
                        (Ograniczenia.czyOsmaKolumna(wspolrzednaPolaDocelowego)&& mozliwoscPrzesuniecia==-7) ||
                        (Ograniczenia.czyOsmaKolumna(wspolrzednaPolaDocelowego)&& mozliwoscPrzesuniecia==9))
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
