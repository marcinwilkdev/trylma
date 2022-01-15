package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
public class MyFrameTest {

    @Test
    public void ustawieniaTest2(){
        MyFrame myFrame = new MyFrame.MyFrameBuilder()
                .budujId(0)
                .budujLiczbeGraczy(2)
                .budujPanelGry(new PanelGry(0))
                .budujPlansze(new Plansza(2))
                .budujPanelNumeru(new PanelNumeru(0))
                .budujButtonHolderBuilder(new ButtonHolder())
                .build();
        assertEquals(myFrame.id,0);
        assertEquals(myFrame.liczbaGraczy,2);
        assertEquals(myFrame.isVisible(),true);
        assertEquals(myFrame.isResizable(),false);
    }

    @Test
    public void ustawieniaTest3(){
        MyFrame myFrame = new MyFrame.MyFrameBuilder()
                .budujId(0)
                .budujLiczbeGraczy(3)
                .budujPanelGry(new PanelGry(0))
                .budujPlansze(new Plansza(3))
                .budujPanelNumeru(new PanelNumeru(0))
                .budujButtonHolderBuilder(new ButtonHolder())
                .build();
        assertEquals(myFrame.id,0);
        assertEquals(myFrame.liczbaGraczy,3);
    }

    @Test
    public void ustawieniaTest4(){
        MyFrame myFrame = new MyFrame.MyFrameBuilder()
                .budujId(1)
                .budujLiczbeGraczy(4)
                .budujPanelGry(new PanelGry(1))
                .budujPlansze(new Plansza(4))
                .budujPanelNumeru(new PanelNumeru(1))
                .budujButtonHolderBuilder(new ButtonHolder())
                .build();
        assertEquals(myFrame.id,1);
        assertEquals(myFrame.liczbaGraczy,4);
    }

    @Test
    public void ustawieniaTest6(){
        MyFrame myFrame = new MyFrame.MyFrameBuilder()
                .budujId(5)
                .budujLiczbeGraczy(6)
                .budujPanelGry(new PanelGry(5))
                .budujPlansze(new Plansza(6))
                .budujPanelNumeru(new PanelNumeru(5))
                .budujButtonHolderBuilder(new ButtonHolder())
                .build();
        assertEquals(myFrame.id,5);
        assertEquals(myFrame.liczbaGraczy,6);
    }

    @Test
    public void przestawPionkaTest(){
        MyFrame myFrame = new MyFrame.MyFrameBuilder()
                .budujId(0)
                .budujLiczbeGraczy(2)
                .budujPanelGry(new PanelGry(0))
                .budujPlansze(new Plansza(2))
                .budujPanelNumeru(new PanelNumeru(0))
                .budujButtonHolderBuilder(new ButtonHolder())
                .build();
        myFrame.przestawPionkaNaPlanszy(13,3,14,4);
        assertEquals(14,myFrame.plansza.PionekNaPolu(14,4).getx());
        assertEquals(4,myFrame.plansza.PionekNaPolu(14,4).gety());
    }

    @Test
    public void rundaGraczaTest(){
        MyFrame myFrame = new MyFrame.MyFrameBuilder()
                .budujId(0)
                .budujLiczbeGraczy(2)
                .budujPanelGry(new PanelGry(0))
                .budujPlansze(new Plansza(2))
                .budujPanelNumeru(new PanelNumeru(0))
                .budujButtonHolderBuilder(new ButtonHolder())
                .build();
        myFrame.rundaGracza();
        assertEquals(myFrame.panelGry.panelTekstu.getText(),"TWOJA RUNDA");
    }

    @Test
    public void clearTest(){
        MyFrame myFrame = new MyFrame.MyFrameBuilder()
                .budujId(0)
                .budujLiczbeGraczy(2)
                .budujPanelGry(new PanelGry(0))
                .budujPlansze(new Plansza(2))
                .budujPanelNumeru(new PanelNumeru(0))
                .budujButtonHolderBuilder(new ButtonHolder())
                .build();
        myFrame.rundaGracza();
        myFrame.clear();
        assertEquals(myFrame.panelGry.panelTekstu.getText(),"CZEKAJ NA SWOJ RUCH");
    }

    @Test
    public void wygranaTest() {
        MyFrame myFrame = new MyFrame.MyFrameBuilder()
                .budujId(0)
                .budujLiczbeGraczy(2)
                .budujPanelGry(new PanelGry(0))
                .budujPlansze(new Plansza(2))
                .budujPanelNumeru(new PanelNumeru(0))
                .budujButtonHolderBuilder(new ButtonHolder())
                .build();
        myFrame.rundaGracza();
        myFrame.wygrana();
        assertEquals(myFrame.panelGry.panelTekstu.getText(), "WYGRALES");
    }

}
