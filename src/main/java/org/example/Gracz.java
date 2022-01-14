package org.example;

/**
 * Mechanika wykonywania ruchów i sprawdzania wygranej danego gracza.
 */

public interface Gracz {
    /**
     * Sprawdza czy na danym polu znajduje się pionek gracza.
     *
     * @param pole Miejsce, które będzie sprawdzane.
     * @return Czy na tym miejscu stoi pionek danego gracza.
     */
    boolean czyPionekNaPolu(Koordynaty pole);
    /**
     * Wykonuje ruch pionkiem.
     *
     * @param ruch Z którego pola do którego ma zostać przesunięty pionek.
     */
    void wykonajRuch(Ruch ruch);
    /**
     * Sprawdza czy gracz doszedł do stanu wygrywającego.
     *
     * @return Czy gracz doszedł do stanu wygrywającego.
     */
    boolean sprawdzWygrana();
    /**
     * Zwraca numer id gracza.
     *
     * @return Numer id gracza.
     */
    int getId();
    /**
     * Zwraca numer gracza.
     *
     * @return Numer gracza.
     */
    int getNumer();
}
