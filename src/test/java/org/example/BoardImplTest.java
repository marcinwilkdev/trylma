package org.example;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BoardImplTest {
    private static Board instance;

    private List<Gracz> gracze;
    private Gracz gracz;

    @BeforeClass
    public static void initializeInstance() {
        instance = BoardImpl.getInstance();
    }

    @Before
    public void initializeGracze() {
        gracze = new ArrayList<>();

        gracz = new GraczImpl(0, 0);
        Gracz gracz2 = new GraczImpl(1, 3);

        gracze.add(gracz);
        gracze.add(gracz2);
    }

    private int liczPola(Koordynaty[] pola) {
        int liczbaPol = 0;

        for(Koordynaty pole : pola) {
            if(pole != null) {
                liczbaPol++;
            }
        }

        return liczbaPol;
    }

    @Test
    public void liczbaPolGracz0(){
        Koordynaty[] pola = instance.generujPolaGracza(0);
        assertEquals(10, liczPola(pola));
    }

    @Test
    public void liczbaPolGracz1(){
        Koordynaty[] pola = instance.generujPolaGracza(1);
        assertEquals(10, liczPola(pola));
    }

    @Test
    public void liczbaPolGracz2(){
        Koordynaty[] pola = instance.generujPolaGracza(2);
        assertEquals(10, liczPola(pola));
    }

    @Test
    public void liczbaPolGracz3(){
        Koordynaty[] pola = instance.generujPolaGracza(3);
        assertEquals(10, liczPola(pola));
    }

    @Test
    public void liczbaPolGracz4(){
        Koordynaty[] pola = instance.generujPolaGracza(4);
        assertEquals(10, liczPola(pola));
    }

    @Test
    public void liczbaPolGracz5(){
        Koordynaty[] pola = instance.generujPolaGracza(5);
        assertEquals(10, liczPola(pola));
    }

    @Test
    public void prawidlowyRuchSkok() {
        Ruch ruch = new Ruch(13, 3, 14, 4);

        assert(instance.zweryfikujRuch(ruch, gracz, gracze));
    }

    @Test
    public void prawidlowyRuchPrzeskok() {
        Ruch ruch = new Ruch(14, 2, 12, 4);

        assert(instance.zweryfikujRuch(ruch, gracz, gracze));
    }

    @Test
    public void niePionekGracza() {
        Ruch ruch = new Ruch(14, 4, 14, 4);

        assert(!instance.zweryfikujRuch(ruch, gracz, gracze));
    }

    @Test
    public void niedozwolonePole() {
        Ruch ruch = new Ruch(13, 3, 14, 3);

        assert(!instance.zweryfikujRuch(ruch, gracz, gracze));
    }

    @Test
    public void niedozwolonySkok() {
        Ruch ruch = new Ruch(13, 3, 15, 5);

        assert(!instance.zweryfikujRuch(ruch, gracz, gracze));
    }

    @Test
    public void pionekNaPolu() {
        Ruch ruch = new Ruch(13, 3, 14, 2);

        assert(!instance.zweryfikujRuch(ruch, gracz, gracze));
    }
}