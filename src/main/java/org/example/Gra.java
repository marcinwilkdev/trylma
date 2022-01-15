package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Główna logika związana z przeprowadzaniem rozgrywki.
 */

public class Gra {
    private List<Gracz> gracze;
    private int liczbaGraczy;
    private int aktualnyGraczIndex;
    private int runda;

    /**
     * Tworzy graczy oraz inicjalizuje aktualnego gracza i rundę.
     *
     * @param liczbaGraczy Liczba graczy biorących udział w rozgrywce.
     */

    public Gra(int liczbaGraczy) {
        stworzGraczy(liczbaGraczy);

        this.aktualnyGraczIndex = 0;
        this.runda = 0;
    }

    /**
     * Główna pętla rozgrywki. Najpierw pobiera gracza, którego jest
     * aktualna tura. Następnie pobiera ruch poprzez serwer łączący się
     * z odpowiednim klientem. Potem serwer rozsyła ruch do reszty klientów,
     * i odpowiedni ruch jest wykonywany po stronie serwera. Potem sprawdzamy
     * czy aktualny gracz doszedł do stanu wygrywającego. Jeżeli tak to logujemy
     * o tym informację, a następnie odłączamy gracza od rozgrywki i dekrementujemy
     * liczbę graczy. Jeżeli liczba pozostałych graczy jest mniejsza lub równa 1
     * kończymy grę. Po zakończeniu tury rozpoczyna się kolejna.
     *
     * @param serwer Instancja serwera do komunikacji z klientami.
     */

    public void glownaPetla(Serwer serwer) {
        while(true) {
            Gracz aktualnyGracz = getAktualnyGracz();

            Ruch ruch = pobierzRuch(serwer, aktualnyGracz);

            serwer.rozeslijRuch(aktualnyGracz, ruch);

            aktualnyGracz.wykonajRuch(ruch);

            if(aktualnyGracz.sprawdzWygrana()) {
                App.logger.log(String.format("Gracz %d wygrał.", aktualnyGracz.getNumer()));

                serwer.rozeslijWygranaIRozlaczGracza(aktualnyGracz);
                usunAktualnegoGracza();
            }

            if(liczbaGraczy <= 1) {
                break;
            }

            kolejnaTura();
        }
    }

    /**
     * Aktualny gracz jest usuwany z rozgrywki.
     */

    // can't remove because breaks pawns checking in Board BUG
    private void usunAktualnegoGracza() {
        gracze.remove(aktualnyGraczIndex);
        liczbaGraczy--;
    }

    /**
     * Pobiera ruch gracza w pętli do momentu aż nie będzie
     * on prawidłowy. Następnie ruch jest zwracany.
     *
     * @param serwer Instancja serwera do komunikacji z klientami.
     * @param gracz Gracz, którego ruch jest pobierany.
     * @return Zweryfikowany ruch gracza.
     */

    private Ruch pobierzRuch(Serwer serwer, Gracz gracz) {
        Ruch ruch = serwer.pobierzRuchKlienta(gracz);

        while(!App.board.zweryfikujRuch(ruch, gracz, this.gracze)) {
            ruch = serwer.pobierzRuchKlienta(gracz);
        }

        App.logger.log(String.format("Gracz %d wykonał ruch %s.", this.aktualnyGraczIndex, ruch));

        return ruch;
    }

    /**
     * Przypisuje turę do kolejnego gracza i jeżeli
     * runda się zakończyła, inkrementuje zmienną z liczbą rundy.
     */

    private void kolejnaTura() {
        this.aktualnyGraczIndex++;

        if(this.aktualnyGraczIndex == this.liczbaGraczy) {
            this.aktualnyGraczIndex = 0;
            this.runda++;
        }
    }

    /**
     * Pobiera gracza, którego tura odbywa się aktualnie.
     *
     * @return Gracz, którego tura odbywa się aktualnie.
     */

    private Gracz getAktualnyGracz() {
        return this.gracze.get(this.aktualnyGraczIndex);
    }

    /**
     * Tworzy graczy i przypisuje im odpowiednie id i numery
     * w zależności od tego ilu graczy bierze udział w rozgrywce.
     *
     * @param liczbaGraczy Liczba graczy biorących udział w rozgrywce.
     */

    private void stworzGraczy(int liczbaGraczy){
        this.liczbaGraczy = liczbaGraczy;

        int idGracza = 0;

        if(liczbaGraczy == 2 || liczbaGraczy == 3 || liczbaGraczy == 4 || liczbaGraczy == 6){
            gracze = new ArrayList<>();
            int numerGracza = 0;

            while(idGracza<liczbaGraczy){
                gracze.add(new GraczImpl(idGracza, numerGracza));
                int k = 6/liczbaGraczy;
                if(liczbaGraczy == 4){
                    if(idGracza == 1){
                        numerGracza = numerGracza + 2;
                    } else{
                        numerGracza = numerGracza + 1;
                    }
                } else{
                    numerGracza = numerGracza + k;
                }
                idGracza++;
            }
        }
    }
}
