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

public class MyFrame extends JFrame{

    int liczbaGraczy;
    int id;
    Plansza plansza;

    MyFrame(int id, int liczbaGraczy) {
        this.id = id;
        this.liczbaGraczy = liczbaGraczy;
        ustawRamke();
        stworzKomponenty();
    }

    public void ustawRamke(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(0,0,970,900);
        this.setVisible(true);
        this.setLayout(null);
    }

    public void stworzKomponenty(){
        PanelGry panelGry = new PanelGry(id);
        this.add(panelGry);
        dodajNumeryGraczy(liczbaGraczy);
        plansza = new Plansza(liczbaGraczy);
        this.add(plansza);
    }

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

}
