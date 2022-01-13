package org.example;
import javax.swing.*;
import java.awt.*;

public class Pole extends JPanel{

    int x;
    int y;
    Color color;

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

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(color);
        g.fillOval(0, 0, 30, 30);

    }

    public int getx(){
        return x;
    }

    public int gety(){
        return y;
    }

}
