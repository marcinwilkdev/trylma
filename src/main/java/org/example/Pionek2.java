package org.example;
import java.awt.event.MouseWheelEvent;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.io.*;
import java.io.Serializable;
import java.awt.Desktop;
import javax.swing.*;

public class Pionek2 extends JPanel{

    int x;
    int y;
    int id;

    public Pionek2(int k, int l, int id) {
        this.id = id;
        this.x = k;
        this.y = l;
        this.setBounds( 135 + (25*x),90 + (37*y),30,30);
        this.setBackground(Color.decode("#98999e"));
    }

    public int getx(){
        return x;
    }

    public int gety(){
        return y;
    }

    public void aktualizujWspolrzedne(){
        this.setBounds( 135 + (25*x),90 + (37*y),30,30);
    }

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


