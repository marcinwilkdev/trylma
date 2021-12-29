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
}