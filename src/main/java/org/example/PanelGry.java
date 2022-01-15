package org.example;
import javax.swing.*;
import java.awt.*;

/**
 * Komponent GUI, w którym wyświetlane są informacje nt. rozgrywki. Może poinformować gracza o wygranej, na bieżąco
 * informuje o tym, czy w danej rundzie gracz ma wykonać ruch czy też nie.
 */

public class PanelGry extends JPanel{
    String text;
    JLabel panelTekstu;
    int id;

    /**
     * Wartość id jest przypisywana do odpowiedniego pola klasy PanelGry. Dodatkowo wywoływane są metody ustawiające
     * parametry graficzne czy początkowo wyświetlany tekst.
     *
     * @param id - numer ID gracza, dla którego tworzone jest GUI
     */

    PanelGry(int id){
        this.id = id;
        ustawPanelGry();
        ustawTekst();
    }

    /**
     * Metoda ustawiająca parametry graficzne komponentu, czyli wymiary oraz tło
     */

    public void ustawPanelGry(){
        this.setBounds(20,70,250,40);
        this.setBackground(Color.decode("#c2c3c4"));
    }

    /**
     * Metoda tworząca instancję klasy JLabel, w której wyświetlany jest tekst. Ustawiany jest on jako początkowy na
     * "CZEKAJ NA SWÓJ RUCH", ustawiane są odpowiedni parametry tekstu. Sama ramka dodawana jest na końcu
     * do głównego komponentu.
     */

    public void ustawTekst(){
        text = "CZEKAJ NA SWOJ RUCH";
        panelTekstu = new JLabel();
        panelTekstu.setText(text);
        panelTekstu.setLayout(null);
        panelTekstu.setFont(new Font("Arial", Font.BOLD,20));
        this.add(panelTekstu);
    }

    /**
     * Metoda ustawiająca tekst na "WYGRALES" - informacja o wygranej gracza, dla którego tworzone jest
     * GUI.
     */

    public void wygrana() {
        this.panelTekstu.setText("WYGRALES");
    }

    /**
     * Metoda ustawiająca tekst na "TWOJA RUNDA" - informacja o rundzie gracza, dla którego tworzone jest
     * GUI.
     */

    public void rundaGracza() {this.panelTekstu.setText("TWOJA RUNDA");}

    /**
     * Metoda przywracająca tekst pierwotny.
     */

    public void clear() {this.panelTekstu.setText("CZEKAJ NA SWOJ RUCH");};
}
