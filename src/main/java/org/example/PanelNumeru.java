package org.example;

import javax.swing.*;
import java.awt.*;

/**
 * Komponent GUI, w którym wyświetlany jest numer gracza, dla któego tworzone jest GUI.
 */

public class PanelNumeru extends JPanel {

    String text;
    JLabel panelTekstu;
    int id;

    /**
     * Wartość id jest przypisywana do odpowiedniego pola oraz wywoływane są metody ustawiające numer oraz
     * pozostałe parametry graficzne/
     *
     * @param id - numer ID gracza, dla którego tworzone jest GUI
     */

    PanelNumeru(int id){
        this.id = id;
        ustawPanelNumeru();
        ustawTekst();
    }

    /**
     * Metoda ustawiająca parametry graficzne komponentu - wymiary oraz kolor tła.
     */

    public void ustawPanelNumeru(){
        this.setBounds(20,20,250,40);
        this.setBackground(Color.decode("#c2c3c4"));
    }

    /**
     * Metoda tworząca instancję klasy JLabel, w której wyświetlany jest tekst. Ustawiany jest on na
     * "JESTES GRACZEM " wraz z odpowiednim numerem gracza.
     */

    public void ustawTekst(){
        text = "JESTES GRACZEM " + String.valueOf(id+1);
        panelTekstu = new JLabel();
        panelTekstu.setText(text);
        panelTekstu.setLayout(null);
        panelTekstu.setFont(new Font("Arial", Font.BOLD,20));
        this.add(panelTekstu);
    }



}
