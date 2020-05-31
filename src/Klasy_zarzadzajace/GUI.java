package Klasy_zarzadzajace;

import Figury.Kolor;
import Figury.Posuniecie;
import Plansza.Plansza;
import Plansza.Ograniczenia;
import Plansza.Pole;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class GUI {

    public JFrame oknoGlowne;
    public GUIPlansza planszaGry;
    public Plansza plansza;

    public GUI(){
        this.oknoGlowne = new JFrame("Szachy");
        this.oknoGlowne.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.oknoGlowne.setSize(new Dimension(600,600));
        utworzMenuOkna();
        this.planszaGry = new GUIPlansza();
        this.oknoGlowne.add(planszaGry,BorderLayout.CENTER);
        this.oknoGlowne.setVisible(true);
    }

    public void utworzMenuOkna(){
        JMenuItem wyjscie = new JMenuItem("Wyjscie");
        JMenuItem nowaGra = new JMenuItem("Nowa Gra");
        wyjscie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oknoGlowne.dispose();
            }
        });

        nowaGra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                polaczZSerwerem();
                if(Ograniczenia.czySerwerAktywny){
                    plansza = new Plansza();
                    planszaGry.odswiezFiguryNaPlanszy(plansza);
                    planszaGry.dodajMouseEventyDoPol(plansza);
                    Ograniczenia.kolorGraczaPosiadajacegoRuch = Kolor.Bialy;
                    if(Ograniczenia.kolorGracza==Kolor.Czarny){
                        Pole poleZajete = new Plansza().zwrocPole(1);
                        Posuniecie posunieciePrzeciwnika = new Posuniecie(plansza,poleZajete.zwrocFigure(),1);
                        Thread watekOczekiwania = new Thread(()->{
                            posunieciePrzeciwnika.czekajNaRuchPrzeciwnika();
                            planszaGry.odswiezFiguryNaPlanszy(plansza);
                        });
                        watekOczekiwania.start();
                    }
                }
            }
        });

        JMenu menu= new JMenu("Menu Glowne");
        menu.add(nowaGra);
        menu.add(wyjscie);
        JMenuBar pasekMenu = new JMenuBar();
        pasekMenu.add(menu);
        oknoGlowne.setJMenuBar(pasekMenu);

    }

    public void polaczZSerwerem() {
        try {
            Socket soket = new Socket(Ograniczenia.ipSerwera, 4333);
            OutputStream os = soket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            InputStream is = soket.getInputStream();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(is));
            pw.println("ZALOGUJ");
            String odpowiedzSerwera[] = br.readLine().split(" ");

            if(odpowiedzSerwera[0].equals("POLACZONO")){
                System.out.println("Grasz online, oczekiwanie na przeciwnika");
                if(odpowiedzSerwera[1].equals("Bialy")){
                    Ograniczenia.kolorGracza = Kolor.Bialy;
                }
                if(odpowiedzSerwera[1].equals("Czarny")){
                    Ograniczenia.kolorGracza = Kolor.Czarny;
                }

                while(true){
                    try{
                    Thread.sleep(200);}
                    catch(InterruptedException ie){
                        System.out.println("Interrupted Exception");
                    }
                    Socket soket2 = new Socket(Ograniczenia.ipSerwera, 4333);
                    InputStream is2 = soket2.getInputStream();
                    BufferedReader br2= new BufferedReader(
                            new InputStreamReader(is2));
                    OutputStream os2 = soket2.getOutputStream();
                    PrintWriter pw2 = new PrintWriter(os2, true);
                    pw2.println("CZY_2_GRACZY");

                    String odpowiedzSerwera2 = br2.readLine();
                    System.out.println(odpowiedzSerwera2);
                    if(odpowiedzSerwera2.equals("Start"))
                    {
                        Ograniczenia.czySerwerAktywny = true;
                        System.out.println("gramyyy");
                        break;

                    }
                    if(odpowiedzSerwera2.equals("Czekaj"))
                    {
                        continue;
                    }
                }
            }
            else{
                System.out.println("Grasz offline");
                Ograniczenia.czySerwerAktywny = false;
            }

        } catch (UnknownHostException h) {
            System.out.println("Nie udalo sie nawiazac polaczenia z hostem");
        } catch (IOException e) {
            System.out.println("Grasz offline");
        }
    }
}
