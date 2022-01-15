package org.example;
import javax.swing.*;
import java.awt.event.MouseWheelEvent;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.io.*;
import java.io.Serializable;
import java.awt.Desktop;

/**
 * GUI, z którego uczestnik gry korzysta podczas rozgrywki.
 */

public class MyFrame extends JFrame{

    int liczbaGraczy;
    int id;
    Plansza plansza;
    PanelGry panelGry;
    PanelNumeru panelNumeru;

    /**
     * Ustawia parametry GUI po jej stworzeniu oraz dodaje do niej stworzone
     * w klasie MyFrameBuilder komponenty.
     */

    public void uzupelnij(){
        ustawRamke();
        dodajKomponenty();
    }

    /**
     * Metoda wywołująca metodę przestawPionkaNaPlanszy na planszy.
     *
     * @param x1 - koordynat x pola, z którego przestawiany jest pionek
     * @param y1 - koordynat y pola, z którego przestawiany jest pionek
     * @param x2 - koordynat x pola, na które przestawiany jest pionek
     * @param y2 - koordynat y pola, na które przestawiany jest pionek
     */

    public void przestawPionkaNaPlanszy(int x1, int y1, int x2, int y2) {
        plansza.przestawPionkaNaPlanszy(x1, y1, x2, y2);
    }

    /**
     * Metoda wywołująca metodę rundaGracza w komponencie panelGry, który
     * informuje gracza o tym, czy aktualnie jest jego runda
     */

    public void rundaGracza(){ panelGry.rundaGracza(); }

    /**
     * Metoda wywołująca metodę rundaGracza w komponencie panelGry, który
     * usuwa wiadomość o jego rundzie i informuje gracza o tym, że musi czekać na swoją
     * rundę
     */

    public void clear(){ panelGry.clear(); }

    /**
     * Metoda wywołująca metodę rundaGracza w komponencie panelGry, który
     * informuje gracza o wygranej
     */

    public void wygrana() {
        panelGry.wygrana();
    }

    /**
     * Metoda ustawiająca parametry GUI, m.in możliwość rozszerzania okna, widoczność okna, layout
     * czy wymiary GUI
     */

    public void ustawRamke(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(0,0,900,850);
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(null);
    }

    /**
     * Metoda dodająca stworzone w klasie MyFrameBuilder komponenty do stworzonego GUI
     */

    public void dodajKomponenty(){


        this.add(panelGry);
        this.add(panelNumeru);
        dodajNumeryGraczy(liczbaGraczy);
        this.add(plansza);


    }

    /**
     * Metoda dodająca do GUI numery przy narożniku każdego z graczy, który bierze udział
     * w rozgrywce.
     *
     * @param liczbaGraczy - liczba graczy biorących udział w rozgrywce
     */
    public void dodajNumeryGraczy(int liczbaGraczy){

        if(liczbaGraczy == 2){
            NumerGracza numer1 = new NumerGracza(0,1);
            NumerGracza numer2 = new NumerGracza(3,2);
            this.add(numer1);
            this.add(numer2);
        }
        else if(liczbaGraczy == 3){
            NumerGracza numer1 = new NumerGracza(0,1);
            NumerGracza numer2 = new NumerGracza(2,2);
            NumerGracza numer3 = new NumerGracza(4,3);
            this.add(numer1);
            this.add(numer2);
            this.add(numer3);
        }
        else if(liczbaGraczy == 4){
            NumerGracza numer1 = new NumerGracza(0,1);
            NumerGracza numer2 = new NumerGracza(1,2);
            NumerGracza numer3 = new NumerGracza(3,3);
            NumerGracza numer4 = new NumerGracza(4,4);
            this.add(numer1);
            this.add(numer2);
            this.add(numer3);
            this.add(numer4);
        }
        else if(liczbaGraczy == 6){
            NumerGracza numer1 = new NumerGracza(0,1);
            NumerGracza numer2 = new NumerGracza(1,2);
            NumerGracza numer3 = new NumerGracza(2,3);
            NumerGracza numer4 = new NumerGracza(3,4);
            NumerGracza numer5 = new NumerGracza(4,5);
            NumerGracza numer6 = new NumerGracza(5,6);
            this.add(numer1);
            this.add(numer2);
            this.add(numer3);
            this.add(numer4);
            this.add(numer5);
            this.add(numer6);
        }
    }

    /**
     * Klasa budująca MyFrame - służy do stworzenia GUI ze stworzonych w niej elementów
     */

    public static class MyFrameBuilder{
        int liczbaGraczy;
        int id;
        Plansza plansza;
        PanelGry panelGry;
        PanelNumeru panelNumeru;

        /**
         * Metoda przypisująca instancji klasy MyFrameBuilder odpowiednie ID gracza
         *
         * @param id - numer ID gracza, którego GUI jest tworzone
         * @return Ta sama instancja klasy MyFrameBuilder, ale ze zaktualizowawnym numerem ID
         */

        public MyFrameBuilder budujId(int id){
            this.id = id;
            return this;
        }

        /**
         * Metoda przypisująca instancji klasy MyFrameBuilder odpowiednią liczbę graczy
         *
         * @param liczbaGraczy - liczba graczy biorących udział w rozgrywce
         * @return Ta sama instancja klasy MyFrameBuilder, ale ze zaktualizowawną liczbą graczy
         */

        public MyFrameBuilder budujLiczbeGraczy(int liczbaGraczy){
            this.liczbaGraczy = liczbaGraczy;
            return this;
        }

        /**
         * Metoda przypisująca instancji klasy MyFrameBuilder odpowiednią planszę
         *
         * @param plansza - plansza, która ma być dodana do GUI
         * @return Ta sama instancja klasy MyFrameBuilder, ale z dodaną/zaktualizowaną planszą
         */

        public MyFrameBuilder budujPlansze(Plansza plansza){
            this.plansza = plansza;
            return this;
        }

        /**
         * Metoda przypisująca instancji klasy MyFrameBuilder odpowiedni panel gry
         *
         * @param panelGry - panel gry, który dodawany jest do GUI
         * @return Ta sama instancja klasy MyFrameBuilder, ale ze zaktualizowawnym panelem gry
         */

        public MyFrameBuilder budujPanelGry(PanelGry panelGry){
            this.panelGry = panelGry;
            return this;
        }

        /**
         * Metoda przypisująca instancji klasy MyFrameBuilder odpowiedni panel numeru
         *
         * @param panelNumeru - panel gry, który dodawany jest do GUI
         * @return Ta sama instancja klasy MyFrameBuilder, ale ze zaktualizowawnym panelem numeru
         */

        public MyFrameBuilder budujPanelNumeru(PanelNumeru panelNumeru){
            this.panelNumeru = panelNumeru;
            return this;
        }

        /**
         * Metoda "budująca" odpowiednie GUI z elementów, które są wcześniej przekazywane do tej klasy odpowiednimi metodami.
         *
         * @return - stworzone GUI, z którego korzystać będzie odpowiedni gracz
         */

        public MyFrame build(){
            MyFrame myFrame = new MyFrame();
            myFrame.id = this.id;
            myFrame.liczbaGraczy = this.liczbaGraczy;
            myFrame.panelGry = this.panelGry;
            myFrame.plansza = this.plansza;
            myFrame.panelNumeru = this.panelNumeru;
            myFrame.uzupelnij();
            return myFrame;
        }
    }

}
