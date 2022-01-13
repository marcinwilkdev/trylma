package org.example;

public class App
{
    public static final Logger logger = StdoutLogger.getInstance();

    public static void main( String[] args )
    {
        while(true) {
            Serwer serwer = new Serwer(8189);

            App.logger.log("Serwer czeka na graczy.");

            serwer.czekajNaGraczy();

            int liczbaGraczy = serwer.getLiczbaKlientow();

            Gra gra = new Gra(liczbaGraczy);

            serwer.rozeslijId();

            gra.glownaPetla(serwer);
        }
    }
}
