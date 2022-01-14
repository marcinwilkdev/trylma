package org.example;

/**
 * Implementacja interfejsu Gracz wykorzystująca tablicę z pionkami i
 * tablicę z polami wygrywającymi.
 */

public class GraczImpl implements Gracz {
    private Pionek[] pionki;
    private final int id;
    private final int numer;
    private final int przeciwnyNumer;
    private Koordynaty[] polaWygrywajace;

    /**
     * Id oraz numer zostają przypisane do odpowiednich pól, a następnie
     * jest generowany numer przeciwnego gracza, który służy do wygenerowania
     * pól wygrywających. Następnie są generowane pionki oraz pola wygrywające.
     *
     * @param id Numer id identyfikujący gracza z odpowiednią instancją kliencką.
     * @param numer Numer gracza wskazujący miejsce na planszy.
     */

    public GraczImpl(int id, int numer) {
        this.id = id;
        this.numer = numer;
        this.przeciwnyNumer = (numer + 3) % 6;

        stworzPionki();
        stworzPolaWygrywajace();
    }

    /**
     * Metoda przeprowadza iterację po wszystkich pionkach danego gracza i
     * jeżeli koordynaty któregoś z nich są zgodne z argumentem metody
     * jest zwracana prawda. W przeciwnym wypadku jest zwracany fałsz.
     *
     * @param pole Miejsce, które będzie sprawdzane.
     * @return Czy na tym miejscu stoi pionek danego gracza.
     */

    public boolean czyPionekNaPolu(Koordynaty pole) {
        for(Pionek pionek : pionki) {
            if(pionek.getX() == pole.getX() && pionek.getY() == pole.getY()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Metoda przeprowadza iterację po wszystkich pionkach danego gracza i
     * sprawadza czy każdy z nich stoi na polu wygrywającym. Jeżeli tak to
     * metoda zwraca prawdę, a w przeciwnym wypadku fałsz.
     *
     * @return Czy wszystkie pionki danego gracza stoją na polach wygrywających.
     */

    public boolean sprawdzWygrana() {
        for(Pionek pionek : pionki) {
            boolean naPoluWygrywajacym = false;

            for(Koordynaty koordynaty : polaWygrywajace) {
                if(pionek.getX() == koordynaty.getX() && pionek.getY() == koordynaty.getY()) {
                    naPoluWygrywajacym = true;
                }
            }

            if(!naPoluWygrywajacym) {
                return false;
            }
        }

        return true;
    }

    /**
     * Metoda wybiera pionek stojący na miejscu ruch.getzPola() i następnie
     * zmienia jego koordynaty na ruch.doPola(). Jeżeli gracz nie posiada
     * pionka na polu ruch.getzPola() metoda wyrzuca RuntimeException
     * (logika programu nie powinna na to pozwolić).
     *
     * @param ruch Z którego pola do którego ma zostać przesunięty pionek.
     */

    public void wykonajRuch(Ruch ruch) {
        Pionek pionek = this.wybierzPionek(ruch.getzPola());

        // pionek nie moze byc nullem bo weryfikacja wczesniej w Gra

        if(pionek == null) {
            throw new RuntimeException("Pionek nie moze byc null!");
        }

        pionek.ruszPionka(ruch.getDoPola());
    }

    /**
     * Zwraca numer id gracza.
     * @return Numer id gracza.
     */

    public int getId(){
        return id;
    }

    /**
     * Zwraca numer gracza.
     * @return Numer gracza.
     */

    public int getNumer() {
        return numer;
    }

    /**
     * Tworzy pionki danego gracza wykorzystując numer gracza
     * oraz metodę generujPolaGracza() z klasy Board.
     */

    private void stworzPionki() {
        Koordynaty[] pola = App.board.generujPolaGracza(numer);

        pionki = mapujPolaNaPionki(pola);
    }

    /**
     * Tworzy pola wygrywające danego gracza wykorzystując numer
     * przeciwny do numeru gracza oraz metodę generujPolaGracza()
     * z interfejsu Board.
     */

    private void stworzPolaWygrywajace() {
        polaWygrywajace = App.board.generujPolaGracza(przeciwnyNumer);
    }

    /**
     * Dla każdego koordynatu z argumentu pola zostanie zwrócony
     * pionek z danymi koordynatami.
     *
     * @param pola Koordynaty pionków.
     * @return Zmapowane pionki.
     */

    private Pionek[] mapujPolaNaPionki(Koordynaty[] pola) {
        Pionek[] pionki = new Pionek[pola.length];

        for(int i=0; i<10; i++) {
            Koordynaty pole = pola[i];
            pionki[i] = new Pionek(pole.getX(), pole.getY());
        }

        return pionki;
    }

    /**
     * Zwraca pionek znajdujący się w miejscu koordynaty.
     * @param koordynaty Koordynaty do wyboru pionka.
     * @return Pionek gracza, który znajduje się na danym miejscu.
     */

    private Pionek wybierzPionek(Koordynaty koordynaty) {
        for(Pionek pionek : this.pionki) {
            if (pionek.getX() == koordynaty.getX() && pionek.getY() == koordynaty.getY()) {
                return pionek;
            }
        }

        return null;
    }
}
