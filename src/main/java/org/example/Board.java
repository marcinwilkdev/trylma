package org.example;

import java.util.List;

/**
 * Mechanika planszy służącej do rozgrywki.
 */

public interface Board {
    /**
     * Weryfikuje ruch gracza.
     *
     * @param ruch Ruch do zweryfikowania.
     * @param gracz Gracz, który wykonuje ruch.
     * @param gracze Wszyscy gracze w rozgrywce.
     * @return Czy ruch jest poprawny.
     */
    boolean zweryfikujRuch(Ruch ruch, Gracz gracz, List<Gracz> gracze);
    /**
     * Generuje pola, na których będą stały pionki gracza o danym numerze.
     *
     * @param numerGracza Numer gracza, którego pionki mają zostać wygenerowane.
     * @return Pola, na których mają stać pionki.
     */
    Koordynaty[] generujPolaGracza(int numerGracza);
}
