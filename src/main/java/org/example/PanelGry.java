package org.example;
import javax.swing.*;
import java.awt.*;


public class PanelGry extends JPanel{
    String text;
    JLabel panelTekstu;
    int id;

    PanelGry(int id){
        this.id = id;
        text = "Gracz o id = " + String.valueOf(id);
        this.setBounds(0,800,900,100);
        panelTekstu = new JLabel();
        panelTekstu.setText(text);
        panelTekstu.setLayout(null);
        this.add(panelTekstu);
        this.setBackground(Color.decode("#c2c3c4"));
    }

    public void wygrana() {
        this.panelTekstu.setText("WYGRALES");
    }

    public void rundaGracza() {this.panelTekstu.setText("TWOJA RUNDA");}

    public void clear() {this.panelTekstu.setText(" ");};
}
