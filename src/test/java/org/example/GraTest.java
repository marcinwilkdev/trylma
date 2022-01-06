package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class GraTest {
    @Test
    public void getPola() {
        Gra gra = new Gra(0);
        var pola = gra.getPola();

        int liczbaPol = 0;

        for(int x=0; x<25; x++) {
            for (int y=0; y<17; y++) {
                if (pola[x][y]) {
                    liczbaPol++;
                }
            }
        }

        assertEquals(121, liczbaPol);
    }

    @Test
    public void kolejnaTuraTest1(){
        Gra gra = new Gra(4);
        gra.kolejnaTura();
        gra.kolejnaTura();
        gra.kolejnaTura();
        gra.kolejnaTura();
        assertEquals(1, gra.getRunda());
        assertEquals(0, gra.getAktGracz());
    }

    @Test
    public void kolejnaTuraTest2(){
        Gra gra = new Gra(5);
        gra.kolejnaTura();
        gra.kolejnaTura();
        gra.kolejnaTura();
        assertEquals(0, gra.getRunda());
        assertEquals(3, gra.getAktGracz());

    }

    @Test
    public void getAktualnyGraczTest1(){
        Gra gra = new Gra(6);
        assertEquals(0, gra.getAktualnyGracz().getid());
        gra.kolejnaTura();
        assertEquals(1, gra.getAktualnyGracz().getid());
    }

    @Test
    public void getAktualnyGraczTest2(){
        Gra gra = new Gra(2);
        assertEquals(0, gra.getAktualnyGracz().getid());
        gra.kolejnaTura();
        assertEquals(3, gra.getAktualnyGracz().getid());
    }

    @Test
    public void ruchPozaPlansze(){
        Gra gra = new Gra(6);
        Koordynaty poczatek = new Koordynaty(9,3);
        Koordynaty koniec = new Koordynaty(8,2);
        Ruch ruch = new Ruch(poczatek,koniec);
        assert(!gra.zweryfikujRuch(ruch));
    }

    @Test
    public void ruchNaPionek(){
        Gra gra = new Gra(6);
        Koordynaty poczatek = new Koordynaty(13,3);
        Koordynaty koniec = new Koordynaty(12,2);
        Ruch ruch = new Ruch(poczatek,koniec);
        assert(!gra.zweryfikujRuch(ruch));
    }

    @Test
    public void ruchPrawidlowyDiff2(){
        Gra gra = new Gra(6);
        Koordynaty poczatek = new Koordynaty(13,3);
        Koordynaty koniec = new Koordynaty(12,4);
        Ruch ruch = new Ruch(poczatek,koniec);
        assert(gra.zweryfikujRuch(ruch));
    }

    @Test
    public void ruchPrawidlowyDiff4(){
        Gra gra = new Gra(6);
        Koordynaty poczatek = new Koordynaty(12,2);
        Koordynaty koniec = new Koordynaty(14,4);
        Ruch ruch = new Ruch(poczatek,koniec);
        assert(gra.zweryfikujRuch(ruch));
    }

    @Test
    public void ruchNieprawidlowegoGracza(){
        Gra gra = new Gra(6);
        Koordynaty poczatek = new Koordynaty(3,7);
        Koordynaty koniec = new Koordynaty(4,8);
        Ruch ruch = new Ruch(poczatek,koniec);
        assert(!gra.zweryfikujRuch(ruch));
    }

    @Test
    public void ruchZaDaleko(){
        Gra gra = new Gra(6);
        Koordynaty poczatek = new Koordynaty(13,3);
        Koordynaty koniec = new Koordynaty(13,9);
        Ruch ruch = new Ruch(poczatek,koniec);
        assert(!gra.zweryfikujRuch(ruch));
    }

    @Test
    public void czyPionekNaPoluTest1(){
        Gra gra = new Gra(6);
        Koordynaty koordynaty = new Koordynaty(13,3);
        assert(gra.czyPionekNaPolu(koordynaty));
    }

    @Test
    public void czyPionekNaPoluTest2(){
        Gra gra = new Gra(6);
        Koordynaty koordynaty = new Koordynaty(13,4);
        assert(!gra.czyPionekNaPolu(koordynaty));
    }



}