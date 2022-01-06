package org.example;

public class Ruch {
    Koordynaty zPola;
    Koordynaty doPola;

    public Ruch(Koordynaty zPola, Koordynaty doPola) {
        this.zPola = zPola;
        this.doPola = doPola;
    }

    public int sumDiff() {
        int diffX = Math.abs(zPola.getX() - doPola.getX());
        int diffY = Math.abs(zPola.getY() - doPola.getY());

        return diffX + diffY;
    }

    public Koordynaty getKoordynatyPomiedzy() {
        int x = (this.zPola.getX() + this.doPola.getX()) / 2;
        int y = (this.zPola.getY() + this.doPola.getY()) / 2;

        return new Koordynaty(x, y);
    }

    public Koordynaty getzPola() {
        return zPola;
    }

    public Koordynaty getDoPola() {
        return doPola;
    }
}
