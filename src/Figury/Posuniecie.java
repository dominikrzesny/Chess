package Figury;

import Klasy_zarzadzajace.Gracz;
import Plansza.Plansza;
import Plansza.Pole;
import Plansza.PolePuste;
import Plansza.PoleZajete;
import Plansza.Ograniczenia;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Posuniecie extends Ruch {
    public Posuniecie(Plansza plansza, Figura figuraWykonujacaRuch, int wspolrzednaDocelowaRuchu) {
        super(plansza, figuraWykonujacaRuch, wspolrzednaDocelowaRuchu);
    }

    public void egzekwujRuch() {

        List<Pole> kopiaListyPolPlanszy = new ArrayList<Pole>(plansza.listaPolPlanszy);

        for(Pole pole : kopiaListyPolPlanszy)
        {
            if(pole.zwrocFigure()==figuraWykonujacaRuch){
                plansza.listaPolPlanszy.remove(pole);
                Pole pole3 = new PolePuste(pole.wspolrzednaPola);
                plansza.listaPolPlanszy.add(pole3);
            }

            if(pole.wspolrzednaPola==wspolrzednaDocelowaruchu){
                plansza.listaPolPlanszy.remove(pole);
                Pole pole2 = new PoleZajete(wspolrzednaDocelowaruchu,figuraWykonujacaRuch);
                plansza.listaPolPlanszy.add(pole2);
            }
        }

        figuraWykonujacaRuch.wspolrzedneFigury = wspolrzednaDocelowaruchu;

        if(Ograniczenia.kolorGraczaPosiadajacegoRuch==Kolor.Bialy){
            Ograniczenia.kolorGraczaPosiadajacegoRuch=Kolor.Czarny;
        }
        else{
            Ograniczenia.kolorGraczaPosiadajacegoRuch=Kolor.Bialy;
        }


    }

    public void wyslijRuchDoSerwera(Gracz gracz, int wspolrzednaFigury, int wspolrzednaDocelowa) {
        try{
            Socket soket = new Socket(Ograniczenia.ipSerwera, 4333);
        OutputStream os = soket.getOutputStream();
        PrintWriter pw = new PrintWriter(os, true);
        pw.println("Ruch");
            pw.println(gracz.kolorFigur+" " + wspolrzednaFigury + " " + wspolrzednaDocelowa);
        }
        catch(IOException ioe){
            System.out.println("IOException");
        }
    }

    public void czekajNaRuchPrzeciwnika(){

        while(true){

            System.out.println("wszedlem do petli while");

            try{
                Thread.sleep(200);}
            catch(InterruptedException ie){
                System.out.println("Interrupted Exception");
            }
            try{

            Socket soket = new Socket(Ograniczenia.ipSerwera, 4333);
            InputStream is = soket.getInputStream();
            BufferedReader br= new BufferedReader(
                    new InputStreamReader(is));
            OutputStream os = soket.getOutputStream();
            PrintWriter pw2 = new PrintWriter(os, true);
            pw2.println("CZY_RUCH_PRZECIWNIKA");
            String odpowiedzSerwera[] = br.readLine().split(" ");
                System.out.println(odpowiedzSerwera[0] + " " + odpowiedzSerwera[1] + " " + odpowiedzSerwera[2]);
                System.out.println(Ograniczenia.kolorGracza);
                String temp = Ograniczenia.kolorGracza.toString();
                String temp2 = odpowiedzSerwera[0];
                System.out.println(temp);
            if(temp2.equals(temp))
            {
                System.out.println("wszedlem do warunku o odpowiedzi serwera");

                Pole pole1 = plansza.zwrocPole(Integer.parseInt(odpowiedzSerwera[1]));
                Figura figura1 = pole1.zwrocFigure();
                Pole pole2 = plansza.zwrocPole(Integer.parseInt(odpowiedzSerwera[2]));
                if(pole2.czyPoleZajęte()){
                    Figura figura2 = pole2.zwrocFigure();
                    Zbicie zbicie = new Zbicie(plansza,figura1,Integer.parseInt(odpowiedzSerwera[2]),figura2);
                    zbicie.egzekwujRuch();

                    break;
                }
                else
                {
                    Posuniecie posuniecie = new Posuniecie(plansza,figura1,Integer.parseInt(odpowiedzSerwera[2]));
                    posuniecie.egzekwujRuch();
                    break;
                }

            }
            else
                {
                    continue;
                }

            }
            catch(IOException ioe){
                System.out.println("IOException");
            }

        }

    }
}
