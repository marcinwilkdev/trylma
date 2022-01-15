package org.example;

public class Koordynaty {
    int x;
    int y;

    /**
     * Wartości x oraz y zostają przypisane do odpowiednich pól.
     *
     * @param x - wartość koordynatu x
     * @param y - wartość koordynatu y
     */

    public Koordynaty(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Metoda zwracająca wartość pola x
     *
     * @return wartość pola x
     */

    public int getX() {
        return x;
    }

    /**
     * Metoda zwracająca wartość pola y
     *
     * @return wartość pola y
     */

    public int getY() {
        return y;
    }
}
