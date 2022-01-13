package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Plansza extends JPanel implements MouseListener{

    int SZEROKOSC_PLANSZY = 25;
    int WYSOKOSC_PLANSZY = 17;
    boolean[][] pola;
    Pole[] tablicaPol = new Pole[121];
    Pionek2[] tablicaPion = new Pionek2[60];
    int clicker = 0;
    Pionek2 holderPionka;
    Pole holderPola;
    int liczbaPionkow = 0;
    int liczbaGraczy;

    Plansza(int liczbaGraczy){
        this.liczbaGraczy = liczbaGraczy;
        ustawPlansze();
        stworzPola();
        dodajPionki(liczbaGraczy);
        narysujPola();
    }

    public void ustawPlansze(){
        this.setLayout(null);
        this.setBounds(0,0,900,900);
        this.setBackground(Color.decode("#98999e"));
        this.addMouseListener(this);
    }

    private void dodajPionki(int liczbaGraczy){

        stworzPola();
        int i = 0;
        int idGracza = 0;

        if(idGracza == 0) {
            for (int x = 0; x < SZEROKOSC_PLANSZY; x++) {
                for (int y = 0; y < WYSOKOSC_PLANSZY; y++) {
                    if (pola[x][y] && y < 4) {
                        tablicaPion[liczbaPionkow] = new Pionek2(x, y, 0);
                        this.add(tablicaPion[liczbaPionkow]);
                        liczbaPionkow++;
                    }
                }
            }

            if(liczbaGraczy == 6 || liczbaGraczy == 4){
                idGracza++;
            }
            else if(liczbaGraczy == 3){
                idGracza = idGracza + 2;
            }
            else if(liczbaGraczy == 2){
                idGracza = idGracza + 3;
            }
        }

        if(idGracza == 1) {
            for (int x = 0; x < SZEROKOSC_PLANSZY; x++) {
                for (int y = 0; y < WYSOKOSC_PLANSZY; y++) {
                    if (pola[x][y] && y < x - 12) {
                        tablicaPion[liczbaPionkow] = new Pionek2(x, y, 1);
                        this.add(tablicaPion[liczbaPionkow]);
                        liczbaPionkow++;
                    }
                }
            }

            if(liczbaGraczy == 6){
                idGracza++;
            }
            else if(liczbaGraczy == 4){
                idGracza = idGracza + 2;
            }
        }

        if(idGracza == 2) {
            for (int x = 0; x < SZEROKOSC_PLANSZY; x++) {
                for (int y = 0; y < WYSOKOSC_PLANSZY; y++) {
                    if (pola[x][y] && y > 28 - x) {
                        tablicaPion[liczbaPionkow] = new Pionek2(x, y, 2);
                        this.add(tablicaPion[liczbaPionkow]);
                        liczbaPionkow++;
                    }
                }
            }

            if(liczbaGraczy == 6){
                idGracza++;
            }
            else if(liczbaGraczy == 3){
                idGracza = idGracza + 2;
            }
        }

        if(idGracza == 3) {
            for (int x = 0; x < SZEROKOSC_PLANSZY; x++) {
                for (int y = 0; y < WYSOKOSC_PLANSZY; y++) {
                    if (pola[x][y] && y > 12) {
                        tablicaPion[liczbaPionkow] = new Pionek2(x, y, 3);
                        this.add(tablicaPion[liczbaPionkow]);
                        liczbaPionkow++;
                    }
                }
            }

            if(liczbaGraczy == 6 || liczbaGraczy == 4){
                idGracza++;
            }
            else if(liczbaGraczy == 2){
                idGracza = idGracza + 3;
            }
        }

        if(idGracza == 4) {
            for (int x = 0; x < SZEROKOSC_PLANSZY; x++) {
                for (int y = 0; y < WYSOKOSC_PLANSZY; y++) {
                    if (pola[x][y] && y > x+4) {
                        tablicaPion[liczbaPionkow] = new Pionek2(x, y, 4);
                        this.add(tablicaPion[liczbaPionkow]);
                        liczbaPionkow++;
                    }
                }
            }

            if(liczbaGraczy == 6){
                idGracza++;
            }

        }

        if(idGracza == 5) {
            for (int x = 0; x < SZEROKOSC_PLANSZY; x++) {
                for (int y = 0; y < WYSOKOSC_PLANSZY; y++) {
                    if (pola[x][y] && y <  12 - x) {
                        tablicaPion[liczbaPionkow] = new Pionek2(x, y, 5);
                        this.add(tablicaPion[liczbaPionkow]);
                        liczbaPionkow++;
                    }
                }
            }
        }
    }

    private void stworzPola() {
        pola = new boolean[SZEROKOSC_PLANSZY][WYSOKOSC_PLANSZY];
        for (int x=0; x<SZEROKOSC_PLANSZY; x++) {
            for (int y=0; y<WYSOKOSC_PLANSZY; y++) {
                pola[x][y] = true;
            }
        }
        usunacCoDrugiePola();
        usunacPolaPozaPlansza();
    }

    private void usunacCoDrugiePola() {
        for (int x=0; x<SZEROKOSC_PLANSZY; x++) {
            for (int y=0; y<WYSOKOSC_PLANSZY; y++) {
                if ((x + y) % 2 == 1) {
                    this.pola[x][y] = false;
                }
            }
        }
    }

    private void usunacPolaPozaPlansza() {
        for (int x=0; x<SZEROKOSC_PLANSZY; x++) {
            for (int y=0; y<WYSOKOSC_PLANSZY; y++) {
                if (!((y <= x + 4) && (y >= 4) && (y <= -1*x + 28)) &&
                        !((y >= -1*x + 12) && (y <= 12) && (y >= x - 12))) {
                    this.pola[x][y] = false;
                }
            }
        }
    }

    private void narysujPola(){
        int i = 0;
        for (int x=0; x<SZEROKOSC_PLANSZY; x++) {
            for (int y=0; y<WYSOKOSC_PLANSZY; y++) {
                if (this.pola[x][y] && i<121){
                    tablicaPol[i] = new Pole(x,y);
                    this.add(tablicaPol[i]);
                    i++;
                }
            }
        }
    }

    public Pole getPole(int x, int y){
        int i = 0;
        while(i<121){
            if (tablicaPol[i].getx() == x && tablicaPol[i].gety() == y && pola[x][y]) {
                return tablicaPol[i];
            }
            i++;
        }
        return null;
    }

    public Pionek2 PionekNaPolu(int x, int y){
        int i = 0;
        while(i<60  && tablicaPion[i]!=null){
            if(tablicaPion[i].getx() == x && tablicaPion[i].gety() == y) {
                return tablicaPion[i];
            }
            i++;
        }
        return null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(clicker == 0){
            if(PionekNaPolu((e.getX()-135)/25, (e.getY()-90)/37)!=null){
                holderPionka = PionekNaPolu((e.getX()-135)/25, (e.getY()-90)/37);
                clicker++;
            }
        }
        else if(clicker == 1){
            if(getPole((e.getX()-135)/25, (e.getY()-90)/37)!=null){
                holderPola = getPole((e.getX()-135)/25, (e.getY()-90)/37);
                KlientProgram.globalWriter.println(String.format("RUCH %d %d %d %d", holderPionka.getx(), holderPionka.gety(), holderPola.getx(), holderPola.gety()));
                if(true){
                    holderPionka.x = holderPola.x;
                    holderPionka.y = holderPola.y;
                    holderPionka.aktualizujWspolrzedne();
                }
            }
            clicker = 0;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
