package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

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



}
