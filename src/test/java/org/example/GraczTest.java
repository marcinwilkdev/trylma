package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GraczTest {

    @Test
    public void liczbaPionkowTest0(){
        Gracz gracz = new Gracz(0);
        assertEquals(gracz.liczPionki(), 10);
    }

    @Test
    public void liczbaPionkowTest1(){
        Gracz gracz = new Gracz(1);
        assertEquals(gracz.liczPionki(), 10);
    }

    @Test
    public void liczbaPionkowTest2(){
        Gracz gracz = new Gracz(2);
        assertEquals(gracz.liczPionki(), 10);
    }

    @Test
    public void liczbaPionkowTest3(){
        Gracz gracz = new Gracz(3);
        assertEquals(gracz.liczPionki(), 10);
    }

    @Test
    public void liczbaPionkowTest4(){
        Gracz gracz = new Gracz(4);
        assertEquals(gracz.liczPionki(), 10);
    }

    @Test
    public void liczbaPionkowTest5(){
        Gracz gracz = new Gracz(5);
        assertEquals(gracz.liczPionki(), 10);
    }

    @Test
    public void wykonajRuch(){
        Gra gra = new Gra(6);

        Pionek pionek = null;
        int i = 0;
        while(i<10){
            if(gra.getAktualnyGracz().getPionki()[i].getX()==13 && gra.getAktualnyGracz().getPionki()[i].getY()==3){
                pionek = gra.getAktualnyGracz().getPionki()[i];
            }
            i++;
        }
        assertNotNull(pionek);

        Koordynaty poczatek = new Koordynaty(13,3);
        Koordynaty koniec = new Koordynaty(14,4);
        Ruch ruch = new Ruch(poczatek,koniec);
        gra.getAktualnyGracz().wykonajRuch(ruch);

        assertEquals(koniec.getX(), pionek.getX());
        assertEquals(koniec.getY(), pionek.getY());
    }

}
