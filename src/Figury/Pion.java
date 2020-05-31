package Figury;

import Plansza.Plansza;
import Plansza.Ograniczenia;
import Plansza.Pole;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Pion extends Figura {

    public int[] mozliwosciPrzesunieciaNaPlanszyDlaBiałego = {-8,-7,-9};
    public int[] mozliwosciPrzesunieciaNaPlanszyDlaCzarnego = {8,7,9};
    public int[] mozliwosciPrzesunieciaNaPlanszy;
    public boolean czyPionNieruszany = false;
    public int wspolrzednaPrzyRuchuPodwojonym;

    public Pion(Kolor KolorFigury, int wspolrzedneFigury, String sciezkaObrazu) {
        super(KolorFigury, wspolrzedneFigury, sciezkaObrazu);
    }

    @Override
    public List<Ruch> wyznaczDozwoloneRuchy(Plansza plansza) {

        List<Ruch> listaDozwolonychRuchow = new ArrayList<>();
        if(this.kolorFigury==Kolor.Bialy)
        {
            mozliwosciPrzesunieciaNaPlanszy = mozliwosciPrzesunieciaNaPlanszyDlaBiałego;
            czyPionNieruszany = Ograniczenia.czySiodmyWiersz(this.wspolrzedneFigury);
            wspolrzednaPrzyRuchuPodwojonym = -8;

        }
        else if(this.kolorFigury == Kolor.Czarny)
        {
            mozliwosciPrzesunieciaNaPlanszy = mozliwosciPrzesunieciaNaPlanszyDlaCzarnego;
            czyPionNieruszany = Ograniczenia.czyDrugiWiersz(this.wspolrzedneFigury);
            wspolrzednaPrzyRuchuPodwojonym = 8;

        }

        for(int mozliwoscPrzesuniecia : mozliwosciPrzesunieciaNaPlanszy)
        {
            int wspolrzednaPolaDocelowego = wspolrzedneFigury+mozliwoscPrzesuniecia;
            Pole pole = plansza.zwrocPole(wspolrzednaPolaDocelowego);
            if(Math.abs(mozliwoscPrzesuniecia)==8)
            {
                if(!pole.czyPoleZajęte()){
                    listaDozwolonychRuchow.add(new Posuniecie(plansza,this,wspolrzednaPolaDocelowego));

                    // rozpatrzenie mozliwosci ruchu o 2 pola
                    wspolrzednaPolaDocelowego+=wspolrzednaPrzyRuchuPodwojonym;
                    pole = plansza.zwrocPole(wspolrzednaPolaDocelowego);
                    if(czyPionNieruszany && !pole.czyPoleZajęte()){
                        listaDozwolonychRuchow.add(new Posuniecie(plansza,this,wspolrzednaPolaDocelowego));
                    }
                }
            }
            if(Math.abs(mozliwoscPrzesuniecia)==7 || Math.abs(mozliwoscPrzesuniecia)==9){
                if(pole.czyPoleZajęte()){
                    Figura figuraOkupującaPole = pole.zwrocFigure();
                    if(figuraOkupującaPole.kolorFigury!=this.kolorFigury) {
                        listaDozwolonychRuchow.add(new Zbicie(plansza, this, wspolrzednaPolaDocelowego, figuraOkupującaPole));
                    }
                }
            }
        }

        return listaDozwolonychRuchow;
    }
}
