package Plansza;

import Figury.*;

import java.util.ArrayList;
import java.util.List;


public class Plansza {

    public List<Figura> kolekcjaBialychFigur = domyslnaKolecjaBialychFigur();
    public List<Figura> kolekcjaCzarnychFigur = domyslnaKolekcjaCzarnychFigur();
    public List<Figura> kolekcjaWszystkichFigurNaPlanszy = zsumujKolekcje();
    public Kolor kolorFigury;
    public List<Pole> listaPolPlanszy = domyslnaKolekcjaPolPlanszy();

    public List<Figura> zsumujKolekcje() {
        List<Figura> kolekcjaFigur = new ArrayList<>();
        kolekcjaFigur.addAll(kolekcjaBialychFigur);
        kolekcjaFigur.addAll(kolekcjaCzarnychFigur);
        return kolekcjaFigur;
    }

    public List<Pole> domyslnaKolekcjaPolPlanszy() {

         List<Pole> listaPol = new ArrayList<Pole>();
         for(Figura figura : kolekcjaBialychFigur){
             listaPol.add(new PoleZajete(figura.wspolrzedneFigury,figura));
         }
        for(Figura figura : kolekcjaCzarnychFigur){
            listaPol.add(new PoleZajete(figura.wspolrzedneFigury,figura));
        }
        for(int i=16;i<48;i++){
            listaPol.add(new PolePuste(i));
        }

         return listaPol;
    }

    public List<Figura> domyslnaKolekcjaCzarnychFigur() {
        List <Figura> listaFigur = new ArrayList<>();
        kolorFigury = Kolor.Czarny;
        listaFigur.add(new Pion(kolorFigury,8,"CzarnyPion.png"));
        listaFigur.add(new Pion(kolorFigury,9,"CzarnyPion.png"));
        listaFigur.add(new Pion(kolorFigury,10,"CzarnyPion.png"));
        listaFigur.add(new Pion(kolorFigury,11,"CzarnyPion.png"));
        listaFigur.add(new Pion(kolorFigury,12,"CzarnyPion.png"));
        listaFigur.add(new Pion(kolorFigury,13,"CzarnyPion.png"));
        listaFigur.add(new Pion(kolorFigury,14,"CzarnyPion.png"));
        listaFigur.add(new Pion(kolorFigury,15,"CzarnyPion.png"));
        listaFigur.add(new Wieża(kolorFigury,0,"CzarnaWieza.png"));
        listaFigur.add(new Wieża(kolorFigury,7,"CzarnaWieza.png"));
        listaFigur.add(new Skoczek(kolorFigury,1,"CzarnySkoczek.png"));
        listaFigur.add(new Skoczek(kolorFigury,6,"CzarnySkoczek.png"));
        listaFigur.add(new Goniec(kolorFigury,2,"CzarnyGoniec.png"));
        listaFigur.add(new Goniec(kolorFigury,5,"CzarnyGoniec.png"));
        listaFigur.add(new Krol(kolorFigury,4,"CzarnyKrol.png"));
        listaFigur.add(new Hetman(kolorFigury,3,"CzarnyHetman.png"));



        return listaFigur;
    }
    public List<Figura> domyslnaKolecjaBialychFigur(){
        List <Figura> listaFigur = new ArrayList<>();
        kolorFigury = Kolor.Bialy;
        listaFigur.add(new Pion(kolorFigury,48,"BialyPion.png"));
        listaFigur.add(new Pion(kolorFigury,49,"BialyPion.png"));
        listaFigur.add(new Pion(kolorFigury,50,"BialyPion.png"));
        listaFigur.add(new Pion(kolorFigury,51,"BialyPion.png"));
        listaFigur.add(new Pion(kolorFigury,52,"BialyPion.png"));
        listaFigur.add(new Pion(kolorFigury,53,"BialyPion.png"));
        listaFigur.add(new Pion(kolorFigury,54,"BialyPion.png"));
        listaFigur.add(new Pion(kolorFigury,55,"BialyPion.png"));
        listaFigur.add(new Wieża(kolorFigury,63,"BialaWieza.png"));
        listaFigur.add(new Wieża(kolorFigury,56,"BialaWieza.png"));
        listaFigur.add(new Skoczek(kolorFigury,57,"BialySkoczek.png"));
        listaFigur.add(new Skoczek(kolorFigury,62,"BialySkoczek.png"));
        listaFigur.add(new Goniec(kolorFigury,61,"BialyGoniec.png"));
        listaFigur.add(new Goniec(kolorFigury,58,"BialyGoniec.png"));
        listaFigur.add(new Krol(kolorFigury,60,"BialyKrol.png"));
        listaFigur.add(new Hetman(kolorFigury,59,"BialyHetman.png"));


        return listaFigur;
    }
    public Pole zwrocPole(int wspolrzednePola){
        for(Pole pole:listaPolPlanszy){
            if(pole.wspolrzednaPola==wspolrzednePola){
                return pole;
            }
        }
        return null;
    }


}
