package Serwer;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

    public class Serwer {

        public static Socket soket;
        public static Socket soket2;
        public static String[] kolory = {"Bialy","Czarny"};
        public static List<Integer> listaZalogowanychGraczy = new ArrayList<>();
        public static String fromClient;
        public static String fromClient2[] = new String[1000];
        public static ServerSocket serwer;
        public static int randomowaLiczba;
        public static boolean czyBylPierwszyRuch = false;
        public static String ostatniRuch[];

        static {
            try {
                serwer = new ServerSocket(4333);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) throws Exception {

            while (true) {
                soket = serwer.accept();
                InputStream is = soket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                fromClient = br.readLine();
                System.out.println(fromClient);
                switch (fromClient) {
                    case "ZALOGUJ":

                        if(listaZalogowanychGraczy.size()>=1){
                            System.out.println("weszlo2");
                            zalogujDrugiegoGracza();
                        }
                        if(listaZalogowanychGraczy.size()==0){
                            System.out.println("weszlo1");
                            zalogujPierwszegoGracza();
                        }

                        break;
                    case "WYLOGUJ":
                        break;
                    case "CZY_2_GRACZY":
                        sprawdzCzyGraGotowa();
                        break;
                    case "CZY_RUCH_PRZECIWNIKA":
                        sprawdzCzyPrzeciwnikSieRuszyl();
                        break;
                    case "Ruch":
                        egzekwujRuch(br);
                        break;
                    default:
                        break;
                }
            }
        }

        public static void sprawdzCzyGraGotowa() throws IOException {

            if(listaZalogowanychGraczy.size()<2){
                OutputStream os = soket.getOutputStream();
                PrintWriter pw = new PrintWriter(os, true);
                pw.println("Czekaj");
            }
            if(listaZalogowanychGraczy.size()==2){
                OutputStream os = soket.getOutputStream();
                PrintWriter pw = new PrintWriter(os, true);
                pw.println("Start");
            }

        }

        public static void egzekwujRuch( BufferedReader br) throws IOException {
            ostatniRuch = br.readLine().split(" ");
            czyBylPierwszyRuch = true;

        }

        public static void sprawdzCzyPrzeciwnikSieRuszyl() throws IOException {

            if(!czyBylPierwszyRuch){
                OutputStream os = soket.getOutputStream();
                PrintWriter pw = new PrintWriter(os, true);
                pw.println("Czekaj 0 0");
            }

            if(czyBylPierwszyRuch){
            //System.out.println(ostatniRuch[0]+ " " + ostatniRuch[1]+ " " + ostatniRuch[2]);

                if(ostatniRuch[0].equals(kolory[0])){
                    OutputStream os = soket.getOutputStream();
                    PrintWriter pw = new PrintWriter(os, true);
                    pw.println(kolory[1] +" " + ostatniRuch[1] + " " +ostatniRuch[2]);
                }
                if(ostatniRuch[0].equals(kolory[1])){
                    OutputStream os = soket.getOutputStream();
                    PrintWriter pw = new PrintWriter(os, true);
                    pw.println(kolory[0] +" " + ostatniRuch[1] + " " +ostatniRuch[2]);
                }
            }

        }

        public static void zalogujPierwszegoGracza() throws IOException{
            listaZalogowanychGraczy.add(0);
            OutputStream os = soket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            randomowaLiczba = new Random().nextInt(2);
            pw.println("POLACZONO " +  kolory[randomowaLiczba]);
            System.out.println("POLACZONO " +  kolory[randomowaLiczba]);

        }

        public static void zalogujDrugiegoGracza() throws IOException {
            listaZalogowanychGraczy.add(0);
            OutputStream os = soket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            pw.println("POLACZONO " +  kolory[1-randomowaLiczba]);
            System.out.println("POLACZONO " +  kolory[1-randomowaLiczba]);
        }
    }

