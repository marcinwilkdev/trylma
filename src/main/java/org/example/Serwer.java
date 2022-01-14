package org.example;

/**
 * Interfejs reprezentujący serwer służący do komunikacji z klientami.
 */
// not great interface, but usefull for testing Gra
public interface Serwer {
    /**
     * Rozsyła ruch wykonany przez gracza do wszysktich klientów.
     *
     * @param gracz Gracz, który wykonał ruch.
     * @param ruch Ruch, który został wykonany.
     */
    void rozeslijRuch(Gracz gracz, Ruch ruch);
    /**
     * Rozsyła informację, że dany gracz wygrał
     * do wszystkich klientów, a następnie odłącza
     * odpowiedniego klienta od serwera.
     *
     * @param gracz Gracz, który doszedł do stanu wygrywającego.
     */
    void rozeslijWygranaIRozlaczGracza(Gracz gracz);
    /**
     * Pobiera ruch od klienta odpowiadającego
     * danemu graczowi.
     *
     * @param gracz Gracz, którego ruch ma zostać pobrany.
     * @return Ruch pobrany od klienta.
     */
    Ruch pobierzRuchKlienta(Gracz gracz);
    /**
     * Czekaj na klientów którzy chcą się połączyć z rozgrywką.
     */
    void czekajNaGraczy();
    /**
     * Pobiera liczbę klientów połączonych z rozgrywką.
     *
     * @return Liczba klientów połączonych z rozgrywką.
     */
    int getLiczbaKlientow();
    /**
     * Rozsyła do każdego klienta odpowiadający
     * my numer id.
     */
    void rozeslijId();
    /**
     * Rozłącza wszystkich klientów od serwera.
     */
    void rozlaczGraczy();
}
