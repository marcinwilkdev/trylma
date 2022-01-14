package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class GraczImplTest {
    @Test
    public void czyPionekNaPoluTest() {
        Gracz gracz = new GraczImpl(0, 0);

        Koordynaty koordynatyPionka = new Koordynaty(13, 3);

        assert(gracz.czyPionekNaPolu(koordynatyPionka));
    }

    @Test
    public void czyPionekNieNaPoluTest() {
        Gracz gracz = new GraczImpl(0, 0);

        Koordynaty koordynatyPionka = new Koordynaty(14, 4);

        assert(!gracz.czyPionekNaPolu(koordynatyPionka));
    }

    @Test
    public void wykonajRuch() {
        Gracz gracz = new GraczImpl(0, 0);

        Koordynaty koordynatyPionka = new Koordynaty(13, 3);
        Koordynaty koordynatyPola = new Koordynaty(14, 4);

        Ruch ruch = new Ruch(koordynatyPionka, koordynatyPola);

        gracz.wykonajRuch(ruch);

        assert(!gracz.czyPionekNaPolu(koordynatyPionka));
        assert(gracz.czyPionekNaPolu(koordynatyPola));
    }

    @Test(expected = RuntimeException.class)
    public void wykonajRuchBlad() {
        Gracz gracz = new GraczImpl(0, 0);

        Koordynaty koordynatyPionka = new Koordynaty(13, 4);
        Koordynaty koordynatyPola = new Koordynaty(14, 4);

        Ruch ruch = new Ruch(koordynatyPionka, koordynatyPola);

        gracz.wykonajRuch(ruch);
    }

    @Test
    public void brakWygranej() {
        Gracz gracz = new GraczImpl(0, 0);

        assert(!gracz.sprawdzWygrana());
    }

    @Test
    public void wygrana() {
        Gracz gracz = new GraczImpl(0, 0);

        gracz.wykonajRuch(new Ruch(12, 0, 12, 16));
        gracz.wykonajRuch(new Ruch(11, 1, 11, 15));
        gracz.wykonajRuch(new Ruch(13, 1, 13, 15));
        gracz.wykonajRuch(new Ruch(10, 2, 10, 14));
        gracz.wykonajRuch(new Ruch(12, 2, 12, 14));
        gracz.wykonajRuch(new Ruch(14, 2, 14, 14));
        gracz.wykonajRuch(new Ruch(9, 3, 9, 13));
        gracz.wykonajRuch(new Ruch(11, 3, 11, 13));
        gracz.wykonajRuch(new Ruch(13, 3, 13, 13));
        gracz.wykonajRuch(new Ruch(15, 3, 15, 13));

        assert(gracz.sprawdzWygrana());
    }

    @Test
    public void getNumer() {
        Gracz gracz = new GraczImpl(1, 3);
        assertEquals(3, gracz.getNumer());
    }

    @Test
    public void getId() {
        Gracz gracz = new GraczImpl(1, 3);
        assertEquals(1, gracz.getId());
    }
}
