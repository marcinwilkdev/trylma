package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.event.MouseEvent;

public class PlanszaTest {

    @Test
    public void getPoleTest(){
        Plansza plansza = new Plansza(6);
        assertEquals(15,plansza.getPole(15,3).getx());
        assertEquals(3,plansza.getPole(15,3).gety());
    }

    @Test
    public void getPoleNullTest(){
        Plansza plansza = new Plansza(6);
        Pole pole = plansza.getPole(2,3);
        assertEquals(pole,null);
    }

    @Test
    public void PionekNaPoluTest(){
        Plansza plansza = new Plansza(6);
        assertEquals(13,plansza.PionekNaPolu(13,3).getx());
        assertEquals(3,plansza.PionekNaPolu(13,3).gety());
    }

    @Test
    public void PionekNaPoluNullTest(){
        Plansza plansza = new Plansza(6);
        PionekNaPlanszy pionek = plansza.PionekNaPolu(17,3);
        assertEquals(pionek,null);
    }

    @Test
    public void mouseClickedTest(){
        MyFrame myFrame = new MyFrame.MyFrameBuilder()
                .budujId(0)
                .budujLiczbeGraczy(2)
                .budujPanelGry(new PanelGry(0))
                .budujPlansze(new Plansza(2))
                .budujPanelNumeru(new PanelNumeru(0))
                .budujButtonHolderBuilder(new ButtonHolder())
                .build();

        MouseEvent mouseEvent = new MouseEvent(myFrame.plansza,0,0,0,465,206,0,false);
        myFrame.plansza.mouseClicked(mouseEvent);
        MouseEvent mouseEvent2 = new MouseEvent(myFrame.plansza,0,0,0,490,238,0,false);
        myFrame.plansza.mouseClicked(mouseEvent2);

    }
}
