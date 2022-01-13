package org.example;

public class Pionek {
    private Koordynaty koordynaty;

    public Pionek(int x, int y) {
        this.koordynaty = new Koordynaty(x, y);
    }

    public void ruszPionka(Koordynaty koordynaty) {
        this.koordynaty = koordynaty;
    }

    public int getX() {
        return this.koordynaty.getX();
    }

    public int getY() {
        return this.koordynaty.getY();
    }

}
