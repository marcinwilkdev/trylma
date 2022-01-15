package org.example;

/**
 * Pionek wykorzystywany w logice gry.
 */

public class Pionek {
    private Koordynaty koordynaty;

    /**
     * Metoda w zależności od wartości x i y tworzy nowy obiekt klasy Koordynaty i przypisuje go do prywatnego pola
     * koordynaty.
     */

    public Pionek(int x, int y) {
        this.koordynaty = new Koordynaty(x, y);
    }

    /**
     * Metoda przypisuje koordynaty podawane w argumencie funkcji do pola koordynaty
     *
     * @param koordynaty - koordynaty, które chcemy przypisać do pola.
     */

    public void ruszPionka(Koordynaty koordynaty) {
        this.koordynaty = koordynaty;
    }

    /**
     * Metoda zwracająca aktualną wartość parametru x przechowywanego w polu koordyanty.
     *
     * @return aktualna wartość x przechowywana w koordynatach
     */

    public int getX() {
        return this.koordynaty.getX();
    }

    /**
     * Metoda zwracająca aktualną wartość parametru y przechowywanego w polu koordyanty.
     *
     * @return aktualna wartość y przechowywana w koordynatach
     */

    public int getY() {
        return this.koordynaty.getY();
    }

}
