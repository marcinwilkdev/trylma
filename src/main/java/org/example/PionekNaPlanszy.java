package org.example;
import java.awt.*;
import javax.swing.*;

/**
 * Klasa wykorzystywana jako pionek po stronie GUI
 */

public class PionekNaPlanszy extends JPanel{

    int x;
    int y;
    int id;

    /**
     * Wartości k, l oraz id przekazywane są do odpowiednich pól, a następnie wywoływana jest metoda
     * ustawiająca parametry graficzne.
     *
     * @param x - wartość koordynatu x dla odpowiedniego pionka
     * @param y - wartość koordynaty y dla odpowiedniego pionka
     * @param id - wartość ID gracza, który posiada odpowiedniego pionka
     */

    public PionekNaPlanszy(int x, int y, int id) {
        this.id = id;
        this.x = x;
        this.y = y;
        ustawPionkaNaPlanszy();
    }

    /**
     * Metoda ustawia parametry graficzne pionka - kolor tła oraz współrzędne
     */

    public void ustawPionkaNaPlanszy(){
        this.setBounds( 135 + (25*x),90 + (37*y),30,30);
        this.setBackground(Color.decode("#98999e"));
    }

    /**
     * Metoda zwraca wartość pola x
     *
     * @return wartość pola x
     */

    public int getx(){
        return x;
    }

    /**
     * Metoda zwraca wartość pola y
     *
     * @return wartość pola y
     */

    public int gety(){
        return y;
    }

    /**
     * Metoda aktualizuje współrzędne - po wykonaniu ruchu zmienia współrzędne komponentu. Są one ustawiane w zależności
     * od nowych wartości parametrów x oraz y. Jest potrzebna, bo zmiana wartości pół x i y nie oznacza natychmiastowej zmiany
     * koordynatów komponentów.
     */

    public void aktualizujWspolrzedne(){
        this.setBounds( 135 + (25*x),90 + (37*y),30,30);
    }

    /**
     * Metoda rysująca odpowiedni kształt na planszy. W zależności od wartości pola id ustawiany jest odpowiedni kolor pionka -
     * unikatowy dla każdego gracza.
     */

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        if(id == 0){
            g.setColor(Color.decode("#67cef0"));
        }
        else if(id == 1){
            g.setColor(Color.decode("#e62e43"));
        }
        else if(id == 2){
            g.setColor(Color.decode("#e8e589"));
        }
        else if(id == 3){
            g.setColor(Color.decode("#80db70"));
        }
        else if(id == 4){
            g.setColor(Color.decode("#f5a43b"));
        }
        else if(id == 5){
            g.setColor(Color.decode("#3b56db"));
        }

        g.fillOval(0, 0, 30, 30);
    }
}


