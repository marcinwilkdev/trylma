package org.example;

import javax.swing.*;
import java.awt.*;

/**
 * Komponent GUI, który wyświetla numer gracza tuż przy jego narożniku. Są one generowane w zależności od numeru gracza oraz
 * od odpowiedniego numeru id.
 */

public class NumerGracza extends JPanel{

    int id;
    JLabel tekst;
    int numer;

    /**
     * Metoda, która przypisuje wartości id i numer do odpowiednich pól. Dodatkowo wywoływane są tu metody ustawiające
     * wszystkie parametry graficzne oraz wyświetlany tekst
     *
     * @param id - numer ID gracza, dla którego tworzony jest numer
     * @param numer - numer gracza, który informuje nas który z kolei jest ten gracz
     */

    NumerGracza(int id, int numer){
        this.id = id;
        this.numer = numer;
        ustawNumer();
        dodajTekst();
        rozmiescNumerIKolor();
    }

    /**
     *  Metoda ustawiająca parametry graficzne - domyślny kolor tekstu oraz kolor tła
     */

    public void ustawNumer(){
        this.setBackground(Color.decode("#98999e"));
        this.setForeground(Color.WHITE);
    }

    /**
     * Metoda tworząca instancję klasy JLabel nazwana tekst. Ten tekst dodawany jest do klasy NumerGracza.
     * Ustawiane są parametry tekstu, m.in czcionka czy jego wielkość. Na samym końcu ustawiamy tekst na numer
     * gracza, który ma być wyświetlony.
     */

    public void dodajTekst(){
        tekst = new JLabel();
        this.add(tekst);
        tekst.setForeground(Color.WHITE);
        tekst.setHorizontalAlignment(SwingConstants.CENTER);
        tekst.setVerticalAlignment(SwingConstants.CENTER);
        tekst.setFont(new Font("Arial", Font.BOLD,39));
        tekst.setText(String.valueOf(numer));
    }

    /**
     * Metoda tworząca kolisty kształt numerka oraz ustawiająca jego kolor.
     *
     * @param g - argument klasy Graphics, który wykorzystywany jest w procesie rysowania kształtów.
     */

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.decode("#303030"));
        g.fillOval(0,0,60,60);
    }

    /**
     * Metoda w zależności od numeru ID ustawia odpowiedni kolor numerka.
     */

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
        else{
            this.setBounds(0,0,60,60);
        }

    }
}
