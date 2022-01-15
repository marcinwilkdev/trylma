package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonHolder extends JPanel implements ActionListener {

    JButton button;

    /**
     * Przycisk pozwalający na pominięcie ruchu
     */

    /**
     * Po wywołaniu konstruktora ustawiane są parametry graficzne panelu oraz tworzony jest przycisk umożliwiający
     * pomijanie ruchu. Dodawany jest do niego action listener, który pozwala na komunikacje między GUI a programem.
     */

    ButtonHolder(){
        this.setLayout(null);
        this.setBounds(20,120,250,40);
        this.setBackground(Color.decode("#98999e"));
        button = new JButton();
        button.setText("SKIP");
        button.setBounds(0,0,250,40);
        button.addActionListener(this);
        this.add(button);

    }

    /**
     * Metoda wywoływana jest wtedy, gdy naciskany jest przycisk SKIP. Informacja jest wysyłana do serwera, który umożliwia
     * wykonanie ruchu przez innego gracza.
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        KlientProgram.globalWriter.println("SKIP");
        System.out.println("SKIP");
    }
}
