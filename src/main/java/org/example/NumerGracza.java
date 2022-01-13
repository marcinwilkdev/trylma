package org.example;

import javax.swing.*;
import java.awt.*;

public class NumerGracza extends JPanel{

    int id;
    Color color;
    JLabel tekst;
    NumerGracza(int id, int numer){

        this.id = id;
        this.setBackground(Color.decode("#98999e"));
        this.setForeground(Color.WHITE);
        tekst = new JLabel();
        this.add(tekst);
        tekst.setForeground(Color.WHITE);
        tekst.setHorizontalAlignment(SwingConstants.CENTER);
        tekst.setVerticalAlignment(SwingConstants.CENTER);
        tekst.setFont(new Font("Arial", Font.BOLD,39));
        tekst.setText(String.valueOf(numer));
        rozmiescNumerIKolor();
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.decode("#303030"));
        g.fillOval(0,0,60,60);
    }

    public void rozmiescNumerIKolor(){
        if(id == 0){
            this.setBounds(420,20,60,60);
            tekst.setForeground((Color.decode("#67cef0")));
        }
        else if(id == 1){
            this.setBounds(765,180,60,60);
            tekst.setForeground(Color.decode("#e62e43"));
        }
        else if(id == 2){
            this.setBounds(765,560,60,60);
            tekst.setForeground(Color.decode("#e8e589"));
        }
        else if(id == 3){
            this.setBounds(420,730,60,60);
            tekst.setForeground(Color.decode("#80db70"));
        }
        else if(id == 4){
            this.setBounds(75,560,60,60);
            tekst.setForeground(Color.decode("#f5a43b"));
        }
        else if(id == 5){
            this.setBounds(75,180,60,60);
            tekst.setForeground(Color.decode("#3b56db"));
        }

    }
}
