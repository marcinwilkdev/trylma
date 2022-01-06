package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class PionekTest {

    @Test
    public void liczPionki2(){
        Gra gra = new Gra(2);
        Gracz[] gracze = gra.getGracze();
        assertEquals(gracze[0].getid(),0);
        assertEquals(gracze[1].getid(),3);
    }

    @Test
    public void liczPionki3(){
        Gra gra = new Gra(3);
        Gracz[] gracze = gra.getGracze();
        assertEquals(gracze[0].getid(),0);
        assertEquals(gracze[1].getid(),2);
        assertEquals(gracze[2].getid(),4);
    }

    @Test
    public void liczPionki4(){
        Gra gra = new Gra(4);
        Gracz[] gracze = gra.getGracze();
        assertEquals(gracze[0].getid(),0);
        assertEquals(gracze[1].getid(),1);
        assertEquals(gracze[2].getid(),3);
        assertEquals(gracze[3].getid(),4);
    }

    @Test
    public void liczPionki6(){
        Gra gra = new Gra(6);
        Gracz[] gracze = gra.getGracze();
        assertEquals(gracze[0].getid(),0);
        assertEquals(gracze[1].getid(),1);
        assertEquals(gracze[2].getid(),2);
        assertEquals(gracze[3].getid(),3);
        assertEquals(gracze[4].getid(),4);
        assertEquals(gracze[5].getid(),5);
    }


}
