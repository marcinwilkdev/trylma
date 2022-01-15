package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *  Plansza, na której graficznie przedstawiana jest rozgrywka prowadzona przez graczy
 */

public class Plansza extends JPanel implements MouseListener{

    int SZEROKOSC_PLANSZY = 25;
    int WYSOKOSC_PLANSZY = 17;
    boolean[][] pola;
    Pole[] tablicaPol = new Pole[121];
    PionekNaPlanszy[] tablicaPion = new PionekNaPlanszy[60];
    int clicker = 0;
    PionekNaPlanszy holderPionka;
    Pole holderPola;
    int liczbaPionkow = 0;
    int liczbaGraczy;

    /**
     * Wartość liczbaGraczy przypisywana jest do odpowiedniego pola w klasie Plansza. Po wywołaniu
     * konstruktora wywoływane są metody, które ustawiają parametry graficzne, dodają komponenty i ustawiają je na
     * planszy.
     *
     * @param liczbaGraczy - liczba graczy biorących udział w rozgrywce
     */

    Plansza(int liczbaGraczy){
        this.liczbaGraczy = liczbaGraczy;
        ustawPlansze();
        stworzPola();
        dodajPionki(liczbaGraczy);
        narysujPola();
    }

    /**
     * Ustawiane są parametry graficzne planszy, takie jak layout, wymiary, kolor tła. Dodatkowo dodawany jest
     * mouseListener odpowiedzialny za obsługę kliknięć.
     */

    public void ustawPlansze(){
        this.setLayout(null);
        this.setBounds(0,0,900,900);
        this.setBackground(Color.decode("#98999e"));
        this.addMouseListener(this);
    }

    /**
     * Metoda dodająca i ustawiająca pionki na planszy. W zależności od ilości graczy na kolejne pola dodawane są
     * pionki.
     */

    private void dodajPionki(int liczbaGraczy){

        stworzPola();
        int i = 0;
        int idGracza = 0;

        if(idGracza == 0) {
            for (int x = 0; x < SZEROKOSC_PLANSZY; x++) {
                for (int y = 0; y < WYSOKOSC_PLANSZY; y++) {
                    if (pola[x][y] && y < 4) {
                        tablicaPion[liczbaPionkow] = new PionekNaPlanszy(x, y, 0);
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
                        tablicaPion[liczbaPionkow] = new PionekNaPlanszy(x, y, 1);
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
                        tablicaPion[liczbaPionkow] = new PionekNaPlanszy(x, y, 2);
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
                        tablicaPion[liczbaPionkow] = new PionekNaPlanszy(x, y, 3);
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
                        tablicaPion[liczbaPionkow] = new PionekNaPlanszy(x, y, 4);
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
                        tablicaPion[liczbaPionkow] = new PionekNaPlanszy(x, y, 5);
                        this.add(tablicaPion[liczbaPionkow]);
                        liczbaPionkow++;
                    }
                }
            }
        }
    }

    /**
     * Metoda przypisująca wartości true do wszystkich możliwych pól x i y ograniczonych wysokością i szerokością planszy.
     * Następnie wywoływane są metody, które ograniczają wszystkie pola o wartościach true tylko do tych pól, na których
     * prowadzona jest rozgrywka.
     */

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

    /**
     * Metoda ustawiająca na false pola, których suma współrzędnych jest nieparzysta. W rozgrywce uwzględniamy
     * tylko te pola, które mają parzystą sumę koordynatów.
     */

    private void usunacCoDrugiePola() {
        for (int x=0; x<SZEROKOSC_PLANSZY; x++) {
            for (int y=0; y<WYSOKOSC_PLANSZY; y++) {
                if ((x + y) % 2 == 1) {
                    this.pola[x][y] = false;
                }
            }
        }
    }

    /**
     * Metoda usuwająca pola będące w narożnikach planszy, które nie są wykorzystywane
     */

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

    /**
     * Metoda tworząca pola oraz zapisująca je do tablicy pól, z których korzysta GUI.
     */

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

    /**
     * Metoda zwracająca pole o odpowiednich koordynatach.
     *
     * @param x - wartość koordynatu x pola, które chcemy dostać
     * @param y - wartość koordynaty y pola, które chcemy dostać
     *
     * @return - pole o podanych koordynatach lub null, gdy takie pole nie istnieje
     */

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

    /**
     * Metoda zwracająca pionka o odpowiednich koordynatach
     *
     * @param x - wartość koordynatu x pionka, które chcemy dostać
     * @param y - wartość koordynaty y pionka, które chcemy dostać
     *
     * @return - pionek o podanych koordynatach lub null, gdy taki pionek nie istnieje
     */

    public PionekNaPlanszy PionekNaPolu(int x, int y){
        int i = 0;
        while(i<60  && tablicaPion[i]!=null){
            if(tablicaPion[i].getx() == x && tablicaPion[i].gety() == y) {
                return tablicaPion[i];
            }
            i++;
        }
        return null;
    }

    /**
     * Metoda wywoływana przy kliknięciu myszką na planszę. Jest ona używana podczas wykonywania ruchu przez gracza.
     * Ruch wykonywany jest w taki sposób - gracz klika na odpowiednie pole. Jeśli na tym polu znajduje się pionek, to
     * jest on przekazywany do pola holderPionka, a wartość zmiennej clicker powiększana jest o 1. Gdy kliknęliśmy we właściwe
     * pole i pionek został pobrany, to przy następnym kliknięciu wybieramy pole, na które chcemy przestawić pobrany pionek. Jeśli
     * to pole istnieje oraz informacja od serwera zawiera informację o wykonanym ruchu, to ruch jest uwzględniany na planszy.
     */

    @Override
    public void mouseClicked(MouseEvent e) {
        if(clicker == 0){
            if(PionekNaPolu((e.getX()-135)/25, (e.getY()-90)/37)!=null){
                holderPionka = PionekNaPolu((e.getX()-135)/25, (e.getY()-90)/37);
                System.out.println("Pole klikniete.");
                clicker++;
            }
        }
        else if(clicker == 1){
            Pole pole = getPole((e.getX()-135)/25, (e.getY()-90)/37);
            if(pole != null && (pole.getx() != holderPionka.getx() || pole.gety() != holderPionka.gety())){
                holderPola = getPole((e.getX()-135)/25, (e.getY()-90)/37);
                KlientProgram.globalWriter.println(String.format("RUCH %d %d %d %d", holderPionka.getx(), holderPionka.gety(), holderPola.getx(), holderPola.gety()));
                clicker = 0;
            }
        }
    }

    /**
     * Metoda przestawiająca pionek na odpowiednie miejsce na planszy.
     *
     * @param x1 - koordynat x pola, na którym stoi pionek
     * @param y1 - koordynat y pola, na którym stoi pionek
     * @param x2 - koordynat x pola, na które przestawiamy pionek
     * @param y2 - koordynat y pola, na które przestawiamy pionek
     */

    public void przestawPionkaNaPlanszy(int x1, int y1, int x2, int y2) {
        holderPionka = PionekNaPolu(x1, y1);
        holderPionka.x = x2;
        holderPionka.y = y2;
        holderPionka.aktualizujWspolrzedne();
    }

    /**
     * Metoda wymagana przez MouseListenera - jej wywołanie nie powoduje żadnych konsekwencji
     */

    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Metoda wymagana przez MouseListenera - jej wywołanie nie powoduje żadnych konsekwencji
     */

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Metoda wymagana przez MouseListenera - jej wywołanie nie powoduje żadnych konsekwencji
     */

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Metoda wymagana przez MouseListenera - jej wywołanie nie powoduje żadnych konsekwencji
     */

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
