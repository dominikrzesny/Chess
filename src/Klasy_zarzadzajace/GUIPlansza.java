package Klasy_zarzadzajace;

import Figury.Figura;
import Figury.Kolor;
import Figury.Ruch;
import Plansza.Plansza;
import Plansza.Pole;
import Plansza.Ograniczenia;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GUIPlansza extends JPanel {

    public List<GUIPole> listaPol;
    public boolean czyFiguraZaznaczona = false;
    List<Ruch> dozwoloneRuchy;
    public Gracz gracz;

    GUIPlansza(){
        super(new GridLayout(8,8));
        listaPol = new ArrayList<>();
        for(int i=0;i<64;i++){
            GUIPole guiPole = new GUIPole(i,this);
            listaPol.add(guiPole);
            add(guiPole);
        }
        setPreferredSize(new Dimension(400,400));
        validate();
    }


    public void odswiezFiguryNaPlanszy(Plansza plansza){

        gracz = new Gracz();
        gracz.kolorFigur = Ograniczenia.kolorGracza;
        for(GUIPole guiPole : listaPol)
        {
            guiPole.removeAll();
            guiPole.revalidate();
            guiPole.repaint();


            for(Pole pole :plansza.listaPolPlanszy){
                if(pole.wspolrzednaPola==guiPole.wspolrzedna){
                    if(pole.czyPoleZajęte()){
                        for(Figura figura: plansza.kolekcjaWszystkichFigurNaPlanszy){
                            if(guiPole.wspolrzedna==figura.wspolrzedneFigury){

                                try {
                                    BufferedImage obrazFigury = ImageIO.read(new File(figura.sciezkaObrazu));
                                    Image dimg = obrazFigury.getScaledInstance(guiPole.getWidth(), guiPole.getHeight(),
                                            Image.SCALE_SMOOTH);
                                    JLabel JObrazFigury = new JLabel(new ImageIcon(dimg));
                                    guiPole.add(JObrazFigury);
                                    guiPole.validate();
                                    guiPole.repaint();

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    }
                }
            }
        }

    }

    public void zaznaczMozliwePosuniecia(List<Ruch> mozliwePosuniecia){
        for(Ruch ruch :mozliwePosuniecia){
            for(GUIPole guiPole:listaPol){
                if(ruch.wspolrzednaDocelowaruchu==guiPole.wspolrzedna){

                    guiPole.setBackground(Color.YELLOW);

                }
            }
        }
    }

    public void odznaczMozliwePosuniecia(){

            for(GUIPole guiPole:listaPol){
                guiPole.ustawKolorPola(guiPole.wspolrzedna);
            }
    }

    public void dodajMouseEventyDoPol(Plansza plansza){

        for(GUIPole guiPole:listaPol){

        guiPole.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton()==1)
                {


                    Pole pole = plansza.zwrocPole(guiPole.wspolrzedna);

                        if(czyFiguraZaznaczona){
                            for(Ruch ruch:dozwoloneRuchy){
                                    if(guiPole.wspolrzedna==ruch.wspolrzednaDocelowaruchu){
                                        try {
                                            int temp = ruch.figuraWykonujacaRuch.wspolrzedneFigury;
                                            ruch.egzekwujRuch();
                                            odswiezFiguryNaPlanszy(plansza);
                                            odznaczMozliwePosuniecia();
                                            czyFiguraZaznaczona = false;
                                            ruch.wyslijRuchDoSerwera(gracz,temp,ruch.wspolrzednaDocelowaruchu);

                                            Thread watekOczekiwaniaNaRuch = new Thread(()->{
                                            ruch.czekajNaRuchPrzeciwnika();
                                            odswiezFiguryNaPlanszy(plansza);
                                            });
                                            watekOczekiwaniaNaRuch.start();

                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                        }

                                        return;
                                    }
                            }
                        }

                        if(pole.czyPoleZajęte())
                        {

                        Figura figura = pole.zwrocFigure();

                            if(figura.kolorFigury==Ograniczenia.kolorGraczaPosiadajacegoRuch && gracz.kolorFigur==figura.kolorFigury)
                            {
                            dozwoloneRuchy = figura.wyznaczDozwoloneRuchy(plansza);
                            odznaczMozliwePosuniecia();
                            zaznaczMozliwePosuniecia(dozwoloneRuchy);
                            czyFiguraZaznaczona=true;
                            }
                        }
                        else {
                            czyFiguraZaznaczona=false;
                            odznaczMozliwePosuniecia();
                        }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //setBackground(Color.yellow);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        }
    }



}
