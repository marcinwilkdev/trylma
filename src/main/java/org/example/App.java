package org.example;

/**
 * Część serwerowa aplikacji służącej do rozgrywki w grę Trylma (chińskie warcaby).
 * Żeby uruchomić rozgrywkę należy najpierw uruchomić serwer z pliku App.java,
 * a następnie łączyć się po kolei jako klienci przy pomocy KlientProgram.java.
 */

public class App
{
    /**
     * Pole przechowujące instancję loggera służącego do logowania informacji w całym programie.
     */

    public static final Logger logger = StdoutLogger.getInstance();

    /**
     *  Pole przechowujące planszę służącą do rozgrywki.
     */

    public static final Board board = BoardImpl.getInstance();

    /**
     * Program działa w nieskończonej pętli. Najpierw stawia serwer na odpowiednim porcie,
     * a następnie serwer czeka na graczy. Kiedy gracze się połączą zaczyna rozgrywkę od
     * rozesłania id do klientów, a następnie rozpoczyna główną pętlę rozgrywki. Po zakończeniu
     * rozgrywki rozłącza wszystkich graczy.
     *
     * @param args Nie używane.
     */

    public static void main( String[] args )
    {
        while(true) {
            Serwer serwer = new SerwerImpl(8189);

            App.logger.log("Serwer czeka na graczy.");

            serwer.czekajNaGraczy();

            int liczbaGraczy = serwer.getLiczbaKlientow();

            Gra gra = new Gra(liczbaGraczy);

            serwer.rozeslijId();

            gra.glownaPetla(serwer);

            serwer.rozlaczGraczy();
        }
    }
}
