package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class RuchTest {

    @Test
    public void ruchTest(){
        Ruch ruch = new Ruch();
        assertNull(ruch.zPola);
        assertNull(ruch.doPola);
        assertEquals(ruch.skip,true);
    }

    @Test
    public void toStringTest(){
        Ruch ruch = new Ruch(13,3,14,4);
        assertEquals("(13, 3) -> (14, 4)",ruch.toString());
    }

}
