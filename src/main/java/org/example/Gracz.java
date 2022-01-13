package org.example;

public class Gracz {
    private Pionek[] pionki;
    private int id;
    private final int przeciwneId;
    private Koordynaty[] polaWygrywajace;

    public Gracz(int id) {
        this.id = id;
        this.przeciwneId = (id + 3) % 6;
        stworzPionki();
        stworzPolaWygrywajace();
    }

    private void stworzPionki(){
        pionki = new Pionek[10];
        int i = 0;
        if(id % 2 == 0){
            for (int x=0; x<25; x++) {
                for (int y=0; y<17; y++) {
                    if ((y >= -1*x + 12) && (y <= 12) && (y >= x - 12) && (x+y) % 2 == 0) {
                        if(id == 0 && y < 4){
                            pionki[i] = new Pionek(x,y);
                            i++;
                        } else if(id == 2 && y > -1*x + 28){
                            pionki[i] = new Pionek(x,y);
                            i++;
                        } else if(id == 4 && y > x + 4){
                            pionki[i] = new Pionek(x,y);
                            i++;
                        }
                    }
                }
            }
        } else {
            for (int x=0; x<25; x++) {
                for (int y=0; y<17; y++) {
                    if ((y <= x + 4) && (y >= 4) && (y <= -1*x + 28) && (x+y) % 2 == 0) {
                        if(id == 1 && y < x - 12){
                            pionki[i] = new Pionek(x,y);
                            i++;
                        } else if(id == 3 && y > 12){
                            pionki[i] = new Pionek(x,y);
                            i++;
                        } else if(id == 5 && y < -1*x + 12){
                            pionki[i] = new Pionek(x,y);
                            i++;
                        }
                    }
                }
            }
        }

    }

    private void stworzPolaWygrywajace() {
        polaWygrywajace = new Koordynaty[10];
        int i = 0;

        if(przeciwneId % 2 == 0){
            for (int x=0; x<25; x++) {
                for (int y=0; y<17; y++) {
                    if ((y >= -1*x + 12) && (y <= 12) && (y >= x - 12) && (x+y) % 2 == 0) {
                        if(przeciwneId == 0 && y < 4){
                            polaWygrywajace[i] = new Koordynaty(x,y);
                            i++;
                        } else if(przeciwneId == 2 && y > -1*x + 28){
                            polaWygrywajace[i] = new Koordynaty(x,y);
                            i++;
                        } else if(przeciwneId == 4 && y > x + 4){
                            polaWygrywajace[i] = new Koordynaty(x,y);
                            i++;
                        }
                    }
                }
            }
        } else {
            for (int x=0; x<25; x++) {
                for (int y=0; y<17; y++) {
                    if ((y <= x + 4) && (y >= 4) && (y <= -1*x + 28) && (x+y) % 2 == 0) {
                        if(przeciwneId == 1 && y < x - 12){
                            polaWygrywajace[i] = new Koordynaty(x,y);
                            i++;
                        } else if(przeciwneId == 3 && y > 12){
                            polaWygrywajace[i] = new Koordynaty(x,y);
                            i++;
                        } else if(przeciwneId == 5 && y < -1*x + 12){
                            polaWygrywajace[i] = new Koordynaty(x,y);
                            i++;
                        }
                    }
                }
            }
        }
    }

    public void wykonajRuch(Ruch ruch) {
        Pionek pionek = this.wybierzPionek(ruch.getzPola());

        // pionek nie moze byc nullem bo weryfikacja wczesniej w Gra

        pionek.ruszPionka(ruch.getDoPola());
    }

    public boolean czyPionekNaPolu(Koordynaty koordynaty) {
        for(int i=0; i<10; i++) {
            if(pionki[i].getX() == koordynaty.getX() && pionki[i].getY() == koordynaty.getY()) {
                return true;
            }
        }

        return false;
    }

    public Pionek wybierzPionek(Koordynaty koordynaty) {
        for(Pionek pionek : this.pionki) {
            if (pionek.getX() == koordynaty.getX() && pionek.getY() == koordynaty.getY()) {
                return pionek;
            }
        }

        return null;
    }

    public int getid(){
        return id;
    }

    public int liczPionki(){
        int i = 0;
        int acc = 0;
        while(i<10){
            if(pionki[i] != null){
                acc++;
            }
            i++;
        }
        return acc;
    }

    public boolean sprawdzWygrana() {
        for(Pionek pionek : pionki) {
            boolean naPoluWygrywajacym = false;

            for(Koordynaty koordynaty : polaWygrywajace) {
                if(pionek.getX() == koordynaty.getX() && pionek.getY() == koordynaty.getY()) {
                    naPoluWygrywajacym = true;
                }
            }

            if(!naPoluWygrywajacym) {
                return false;
            }
        }

        return true;
    }
}
