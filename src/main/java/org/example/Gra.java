package org.example;

public class Gra {
    private boolean[][] pola;
    private Gracz[] gracze;

    public Gra(int liczbaGraczy) {
        stworzPola();
//        this.gracze = stworzGraczy(liczbaGraczy);
    }

    private void stworzPola() {
        boolean[][] pola = new boolean[25][17];

        for (int x=0; x<25; x++) {
            for (int y=0; y<17; y++) {
                pola[x][y] = true;
            }
        }

        this.pola = pola;

        usunacCoDrugiePola();
        usunacPolaPozaPlansza();
    }

    private void usunacCoDrugiePola() {
        for (int x=0; x<25; x++) {
            for (int y=0; y<17; y++) {
                if ((x + y) % 2 == 1) {
                    this.pola[x][y] = false;
                }
            }
        }
    }

    private void usunacPolaPozaPlansza() {
        for (int x=0; x<25; x++) {
            for (int y=0; y<17; y++) {
                if (!((y <= x + 4) && (y >= 4) && (y <= -1*x + 28)) &&
                    !((y >= -1*x + 12) && (y <= 12) && (y >= x - 12))) {
                    this.pola[x][y] = false;
                }
            }
        }
    }

    public boolean[][] getPola() {
        return pola;
    }
}
