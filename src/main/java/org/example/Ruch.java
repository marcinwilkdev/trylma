package org.example;

public class Ruch {
    Koordynaty zPola;
    Koordynaty doPola;
    boolean skip;

    public Ruch(int x1, int y1, int x2, int y2) {
        this.zPola = new Koordynaty(x1, y1);
        this.doPola = new Koordynaty(x2, y2);
        this.skip = false;
    }

    public Ruch(Koordynaty zPola, Koordynaty doPola) {
        this.zPola = zPola;
        this.doPola = doPola;
    }

    public Ruch() {
        this.zPola = null;
        this.doPola = null;
        this.skip = true;
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

    public boolean isSkip() {
        return skip;
    }

    public Koordynaty getzPola() {
        return zPola;
    }

    public Koordynaty getDoPola() {
        return doPola;
    }

    @Override
    public String toString() {
        if(skip) {
            return "(skip)";
        }

        Koordynaty zPola = this.getzPola();
        Koordynaty doPola = this.getDoPola();

        return String.format("(%d, %d) -> (%d, %d)", zPola.getX(), zPola.getY(), doPola.getX(), doPola.getY());
    }
}
