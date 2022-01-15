package org.example;

import java.util.List;

/**
 * Implementacja interfejsu board wykorzystująca tablicę dwuwymiarową
 * typu boolean jako informację jakie pola należą do planszy.
 */

public class BoardImpl implements Board {
    private static BoardImpl instance = null;

    /**
     * Szerokość planszy (liczba pól).
     */

    public static final int SZEROKOSC_PLANSZY = 25;

    /**
     * Wysokość planszy (liczba pól).
     */

    public static final int WYSOKOSC_PLANSZY = 17;

    /**
     * Liczba pionków każdego z graczy.
     */

    public static final int LICZBA_PIONKOW = 10;

    private boolean[][] pola;

    /**
     * Implementacja singleton pattern.
     * @return Pojedyncza instancja klasy.
     */

    public static BoardImpl getInstance() {
        if(BoardImpl.instance == null) {
            BoardImpl.instance = new BoardImpl();
        }

        return BoardImpl.instance;
    }

    /**
     * Tworzy pola planszy.
     */

    private BoardImpl() {
        stworzPola();
    }

    /**
     * Sprawdza czy na polu ruch.zPola() znajduje się pionek gracza.
     * Następnie sprawdza czy ruch jest skokiem czy przeskokiem pionka.
     * Jeśli tak, to sprawdza czy skok lub przeskok może zostać wykonany.
     * Jeżeli nie, zwraca fałsz.
     *
     * @param ruch Ruch do zweryfikowania.
     * @param gracz Gracz, który wykonuje ruch.
     * @param gracze Wszyscy gracze w rozgrywce.
     * @return Czy ruch jest poprawny.
     */

    public boolean zweryfikujRuch(Ruch ruch, Gracz gracz, List<Gracz> gracze) {
        if(ruch.isSkip()) {
            return true;
        }

        if(niePionekGracza(gracz, ruch.getzPola())) {
            return false;
        }

        int sumDiff = ruch.sumDiff();

        if(sumDiff == 2) {
            return sprawdzSkok(ruch.getDoPola(), gracze);
        }

        if(sumDiff == 4) {
            return sprawdzPrzeskok(ruch, gracze);
        }

        return false;
    }

    /**
     * Generuje pola gracza poprzez iterację po wszystkich polach należących
     * do planszy i wybraniu tych pól, które należą do odpowiedniego trójkąta
     * wyciętego z planszy.
     *
     * @param numerGracza Numer gracza, którego pionki mają zostać wygenerowane.
     * @return Pola, na których mają stać pionki.
     */

    public Koordynaty[] generujPolaGracza(int numerGracza) {
        Koordynaty[] pola = new Koordynaty[LICZBA_PIONKOW];
        int poleIndex = 0;

        if(numerGracza % 2 == 0){
            for (int x=0; x<25; x++) {
                for (int y=0; y<17; y++) {
                    if ((y >= -1*x + 12) && (y <= 12) && (y >= x - 12) && (x+y) % 2 == 0) {
                        if(numerGracza == 0 && y < 4){
                            pola[poleIndex] = new Koordynaty(x,y);
                            poleIndex++;
                        } else if(numerGracza == 2 && y > -1*x + 28){
                            pola[poleIndex] = new Koordynaty(x,y);
                            poleIndex++;
                        } else if(numerGracza == 4 && y > x + 4){
                            pola[poleIndex] = new Koordynaty(x,y);
                            poleIndex++;
                        }
                    }
                }
            }
        } else {
            for (int x=0; x<25; x++) {
                for (int y=0; y<17; y++) {
                    if ((y <= x + 4) && (y >= 4) && (y <= -1*x + 28) && (x+y) % 2 == 0) {
                        if(numerGracza == 1 && y < x - 12){
                            pola[poleIndex] = new Koordynaty(x,y);
                            poleIndex++;
                        } else if(numerGracza == 3 && y > 12){
                            pola[poleIndex] = new Koordynaty(x,y);
                            poleIndex++;
                        } else if(numerGracza == 5 && y < -1*x + 12){
                            pola[poleIndex] = new Koordynaty(x,y);
                            poleIndex++;
                        }
                    }
                }
            }
        }

        return pola;
    }

    /**
     * Sprawdza czy pole, na które pionek ma skoczyć jest wolne i czy należy do planszy.
     *
     * @param koordynaty Pole, na które pionek ma skoczyć.
     * @param gracze Wszyscy gracze w rozgrywce.
     * @return Czy można skoczyć na pole.
     */

    private boolean sprawdzSkok(Koordynaty koordynaty, List<Gracz> gracze) {
        return !this.czyPionekNaPolu(koordynaty, gracze) && dozwolonePole(koordynaty);
    }

    /**
     * Sprawdza czy pole, na które pionek ma skoczyć jest wolne i czy należy do planszy
     * oraz czy pomiędzy pionkiem a polem, na które ma skoczyć znajduje się inny pionek.
     *
     * @param ruch Ruch, który ma zostać wykonany.
     * @param gracze Wszyscy gracze w rozgrywce.
     * @return Czy można wykonać przeskok.
     */

    private boolean sprawdzPrzeskok(Ruch ruch, List<Gracz> gracze) {
        return !this.czyPionekNaPolu(ruch.getDoPola(), gracze) && this.czyPionekNaPolu(ruch.getKoordynatyPomiedzy(), gracze) && dozwolonePole(ruch.getDoPola());
    }

    /**
     * Sprawdza czy na danym polu nie znajduje się pionek danego gracza.
     *
     * @param gracz Gracz, którego pionki mają zostać sprawdzone.
     * @param koordynaty Pole, które ma zostać sprawdzone.
     * @return Czy na danym polu nie stoi pionek danego gracza.
     */

    private boolean niePionekGracza(Gracz gracz, Koordynaty koordynaty) {
        return !gracz.czyPionekNaPolu(koordynaty);
    }

    /**
     * Sprawdza czy dane pole należy do planszy poprzez
     * sprawdzenie czy dane koordynaty w tabeli pól
     * posiadają wartość prawda.
     *
     * @param koordynaty Pole, które ma zostać sprawdzone.
     * @return Czy dane pole należy do planszy.
     */

    private boolean dozwolonePole(Koordynaty koordynaty) {
        return this.pola[koordynaty.getX()][koordynaty.getY()];
    }

    /**
     * Sprawdza czy na danym polu znajduje się jakiś pionek
     * poprzez iterację po wszystkich graczach w grze i sprawdzenie
     * czy dany gracz posiada pionek na danym polu.
     *
     * @param koordynaty Pole, które ma zostać sprawdzone.
     * @param gracze Wszyscy gracze w rozgrywce.
     * @return Czy na danym polu znajduje się jakiś pionek.
     */

    private boolean czyPionekNaPolu(Koordynaty koordynaty, List<Gracz> gracze) {
        for(Gracz gracz : gracze) {
            for(int j=0; j<LICZBA_PIONKOW; j++) {
                if(gracz.czyPionekNaPolu(koordynaty)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Generuje pola planszy poprzez wypełenienie tablicy wartości typu boolean
     * wartościami prawda, a następnie wypełnienie wartościami fałsz koordynatów,
     * które znajdują się pomiędzy polami oraz koordynatów, które znajdują
     * się poza planszą.
     */

    private void stworzPola() {
        boolean[][] pola = new boolean[SZEROKOSC_PLANSZY][WYSOKOSC_PLANSZY];

        for (int x=0; x<SZEROKOSC_PLANSZY; x++) {
            for (int y=0; y<WYSOKOSC_PLANSZY; y++) {
                pola[x][y] = true;
            }
        }

        this.pola = pola;

        usunacCoDrugiePola();
        usunacPolaPozaPlansza();
    }

    /**
     * Wstawia wartości fałsz w koordynaty w tablicy, które
     * znajdują się między poprawnymi polami poprzez iterację
     * po wszystkich koordynatach i sprawdzenie wyniku operacji
     * modulo 2.
     */

    private void usunacCoDrugiePola() {
        for (int x=0; x<SZEROKOSC_PLANSZY; x++) {
            for (int y=0; y<WYSOKOSC_PLANSZY; y++) {
                if ((x + y) % 2 == 1) {
                    this.pola[x][y] = false;
                }
            }
        }
    }

    /**
     * Wstawia wartości fałsz w koordynaty w tablicy, które
     * znajdują się poza planszą poprzez sprawdzenie iterację
     * po wszystkich koordynatach i sprawdzenie czy dany koordynat
     * znajduje się poza dwoma trójkątami tworzącaymi planszę.
     */

    private void usunacPolaPozaPlansza() {
        for (int x=0; x<SZEROKOSC_PLANSZY; x++) {
            for (int y=0; y<WYSOKOSC_PLANSZY; y++) {
                if (!((y <= x + 4) && (y >= 4) && (y <= -1*x + 28)) &&
                        !((y >= -1*x + 12) && (y <= 12) && (y >= x - 12))) {
                    this.pola[x][y] = false;
                }
            }
        }
    }
}
