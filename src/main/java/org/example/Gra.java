package org.example;

public class Gra {
    public static final int SZEROKOSC_PLANSZY = 25;
    public static final int WYSOKOSC_PLANSZY = 17;

    public static final int LICZBA_PIONKOW = 10;

    private boolean[][] pola;
    private Gracz[] gracze;
    private int liczbaGraczy;
    private int aktualnyGracz;
    private int runda;

    public Gra(int liczbaGraczy) {
        stworzPola();
        stworzGraczy(liczbaGraczy);

        this.aktualnyGracz = 0;
        this.runda = 0;
    }

    public void glownaPetla(Serwer serwer) {
        while(true) {
            Ruch ruch = this.pobierzRuch(serwer);

            App.logger.log(String.format("Gracz %d wykonał ruch %s.", this.aktualnyGracz, ruch));

            serwer.rozeslijRuch(this.aktualnyGracz, ruch);

            this.getAktualnyGracz().wykonajRuch(ruch);

            if(this.getAktualnyGracz().sprawdzWygrana()) {
                App.logger.log(String.format("Gracz %d wygrał. Koniec gry.", this.aktualnyGracz));

                serwer.rozeslijWygrana(this.aktualnyGracz);
                break;
            }

            this.kolejnaTura();
        }
    }

    private Ruch pobierzRuch(Serwer serwer) {
        Ruch ruch = serwer.pobierzRuchKlienta(this.aktualnyGracz);

        while(!this.zweryfikujRuch(ruch)) {
            ruch = serwer.pobierzRuchKlienta(this.aktualnyGracz);
        }

        return ruch;
    }

    public void kolejnaTura() {
        this.aktualnyGracz++;

        if(this.aktualnyGracz == this.liczbaGraczy) {
            this.aktualnyGracz = 0;
            this.runda++;
        }
    }

    public Gracz getAktualnyGracz() {
        return this.gracze[this.aktualnyGracz];
    }

    public boolean zweryfikujRuch(Ruch ruch) {
        Gracz gracz = this.getAktualnyGracz();

        if(!gracz.czyPionekNaPolu(ruch.getzPola())) {
            return false;
        }

        int sumDiff = ruch.sumDiff();

        if(sumDiff == 2) {
            return !this.czyPionekNaPolu(ruch.getDoPola());
        }

        if(sumDiff == 4) {
            Koordynaty koordynatyPomiedzy = ruch.getKoordynatyPomiedzy();

            return !this.czyPionekNaPolu(ruch.getDoPola()) && this.czyPionekNaPolu(koordynatyPomiedzy);
        }

        return false;
    }

    private boolean czyPionekNaPolu(Koordynaty koordynaty) {
        for(int i=0; i<liczbaGraczy; i++) {
            Gracz gracz = this.gracze[i];

            for(int j=0; j<LICZBA_PIONKOW; j++) {
                if(gracz.czyPionekNaPolu(koordynaty)) {
                    return true;
                }
            }
        }

        return false;
    }

    private void stworzPola() {
        boolean[][] pola = new boolean[SZEROKOSC_PLANSZY][WYSOKOSC_PLANSZY];

        for (int x=0; x<SZEROKOSC_PLANSZY; x++) {
            for (int y=0; y<WYSOKOSC_PLANSZY; y++) {
                pola[x][y] = true;
            }
        }

        this.pola = pola;

        usunacCoDrugiePola();
        usunacPolaPozaPlansza();
    }

    private void usunacCoDrugiePola() {
        for (int x=0; x<SZEROKOSC_PLANSZY; x++) {
            for (int y=0; y<WYSOKOSC_PLANSZY; y++) {
                if ((x + y) % 2 == 1) {
                    this.pola[x][y] = false;
                }
            }
        }
    }

    private void usunacPolaPozaPlansza() {
        for (int x=0; x<SZEROKOSC_PLANSZY; x++) {
            for (int y=0; y<WYSOKOSC_PLANSZY; y++) {
                if (!((y <= x + 4) && (y >= 4) && (y <= -1*x + 28)) &&
                    !((y >= -1*x + 12) && (y <= 12) && (y >= x - 12))) {
                    this.pola[x][y] = false;
                }
            }
        }
    }

    private void stworzGraczy(int liczbaGraczy){
        this.liczbaGraczy = liczbaGraczy;

        int i = 0;

        if(liczbaGraczy == 2 || liczbaGraczy == 3 || liczbaGraczy == 4 || liczbaGraczy == 6){
            gracze = new Gracz[liczbaGraczy];
            int idGracza = 0;
            while(i<liczbaGraczy){
                gracze[i] = new Gracz(idGracza);
                int k = 6/liczbaGraczy;
                if(liczbaGraczy == 4){
                    if(i == 1){
                        idGracza = idGracza + 2;
                    } else{
                        idGracza = idGracza + 1;
                    }
                } else{
                    idGracza = idGracza + k;
                }
                i++;
            }
        }
    }

    public boolean[][] getPola() {
        return pola;
    }

    public Gracz[] getGracze(){
        return gracze;
    }
}
