package Klasy_zarzadzajace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUIPole extends JPanel {
    int wspolrzedna;

    GUIPole(int wspolrzedna, GUIPlansza guiPlansza) {
        super(new GridBagLayout());
        this.wspolrzedna=wspolrzedna;
        setPreferredSize(new Dimension(50,50));
        ustawKolorPola(wspolrzedna);
    }

    public void ustawKolorPola(int wspolrzedna) {
        if (wspolrzedna % 2 == 0 && (wspolrzedna < 8 || (wspolrzedna > 15 && wspolrzedna < 24) || (wspolrzedna > 31 && wspolrzedna < 40) || (wspolrzedna > 47 && wspolrzedna < 56))) {
            setBackground(Color.WHITE);
        }
        if (wspolrzedna % 2 == 1 && (wspolrzedna < 8 || (wspolrzedna > 15 && wspolrzedna < 24) || (wspolrzedna > 31 && wspolrzedna < 40) || (wspolrzedna > 47 && wspolrzedna < 56))) {
            setBackground(Color.GRAY);
        }

        if (wspolrzedna % 2 == 1 && ((wspolrzedna > 7 && wspolrzedna < 16) || (wspolrzedna > 23 && wspolrzedna < 32) || (wspolrzedna > 39 && wspolrzedna < 48) || wspolrzedna > 55)) {
            setBackground(Color.WHITE);
        }
        if (wspolrzedna % 2 == 0 && ((wspolrzedna > 7 && wspolrzedna < 16) || (wspolrzedna > 23 && wspolrzedna < 32) || (wspolrzedna > 39 && wspolrzedna < 48) || wspolrzedna > 55)) {
            setBackground(Color.GRAY);
        }
        validate();
    }

}
