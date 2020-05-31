package Plansza;

import Figury.Kolor;

public class Ograniczenia {

    public static boolean czySerwerAktywny = false;
    public static Kolor kolorGraczaPosiadajacegoRuch;
    public static Kolor kolorGracza;
    public static String ipSerwera = "localhost";

    public static boolean czyDozwolonaWspolrzedna(int wspolrzedna){
        return wspolrzedna>=0 && wspolrzedna<64;
    }
    public static boolean czyPierwszaKolumna(int wspolrzedna){
        return wspolrzedna==0 || wspolrzedna==8 || wspolrzedna==16 || wspolrzedna==24 || wspolrzedna==32 || wspolrzedna==40
                || wspolrzedna==48 ||wspolrzedna==56;
    }

    public static boolean czyDrugaKolumna(int wspolrzedna){
        return wspolrzedna==1 || wspolrzedna==9 || wspolrzedna==17 || wspolrzedna==25 || wspolrzedna==33 || wspolrzedna==41
                || wspolrzedna==49 ||wspolrzedna==57;
    }

    public static boolean czySiodmaKolumna(int wspolrzedna){
        return wspolrzedna==6 || wspolrzedna==14 || wspolrzedna==22 || wspolrzedna==30 || wspolrzedna==38 || wspolrzedna==46
                || wspolrzedna==54 ||wspolrzedna==62;
    }

    public static boolean czyOsmaKolumna(int wspolrzedna){
        return wspolrzedna==7 || wspolrzedna==15 || wspolrzedna==23 || wspolrzedna==31 || wspolrzedna==39 || wspolrzedna==47
                || wspolrzedna==55 ||wspolrzedna==63;
    }

    public static boolean czyDrugiWiersz(int wspolrzedna){
        return wspolrzedna>7 && wspolrzedna<16;
    }

    public static boolean czySiodmyWiersz(int wspolrzedna){
        return wspolrzedna>47 && wspolrzedna<56;
    }

}
