package org.example;
import javax.swing.*;
import java.awt.*;

/**
 * Pole wykorzystywane jako komponent GUI
 */

public class Pole extends JPanel{

    int x;
    int y;
    Color color;

    /**
     * Wartości x i y są przekazywane do odpowiednich pól klasy Pole. Ustawiane są parametry graficzne komponentu, takie jak
     * kolor tła czy wymiary.
     *
     * @param x - wartość koordynatu x przypisanego do pola
     * @param y - wartośc koordynatu y przypisanego do pola
     */

    Pole(int x, int y){
        this.x = x;
        this.y = y;
        this.setBackground(Color.decode("#98999e"));
        this.setBounds( 135 + (25*x),90 + (37*y),30,30);
        if(y > x+4 || y < 12 - x || y < 4 || y > 28 - x || y < x - 12 || y > 12) {
            color = Color.decode("#4f5054");
        }
        else{
            color = Color.decode("#67686b");
        }


    }

    /**
     * Metoda ustawiająca kolor pola, a następnie rysująca pole o kolistym kształcie.
     */

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(color);
        g.fillOval(0, 0, 30, 30);

    }

    /**
     * Metoda zwracająca aktualną wartość pola x
     *
     * @return - aktualna wartość pola x
     */

    public int getx(){
        return x;
    }

    /**
     * Metoda zwracająca aktualną wartość pola y
     *
     * @return - aktualna wartość pola y
     */

    public int gety(){
        return y;
    }

}
