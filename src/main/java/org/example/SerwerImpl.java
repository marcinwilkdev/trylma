package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementacja interfejsu serwer wykorzystująca ServerSocket
 * do komunikacji z klientami i przechowująca klientów w liście.
 */

public class SerwerImpl implements Serwer {
    private final int port;
    private List<Klient> klienci;

    /**
     * Przypisuje zmienną port i inicjalizuje pustą listę
     * klientów.
     * @param port Numer portu, na którym serwer będzie nasłuchiwał.
     */

    public SerwerImpl(int port) {
        this.port = port;
        this.klienci = new ArrayList<>();
    }

    /**
     * Przyjmuje połączenia poszczególnych klientów poprzez socket.accept()
     * do czasu aż ich liczba nie przekroczy 6 oraz przeprowadza głosowanie
     * za rozpoczęciem rozgrywki jeżeli połączyła się odpowiednia liczba
     * klientów.
     */

    public void czekajNaGraczy() {
        try(ServerSocket s = new ServerSocket(this.port)) {
            int klientId = 0;

            while(this.getLiczbaKlientow() < 6) {
                Socket socket = s.accept();
                this.dodajKlienta(new Klient(klientId, socket));
                klientId++;

                App.logger.log(String.format("Gracz się połączył z portu %d", socket.getLocalPort()));

                if (this.przeprowadzGlosowanie()) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pobiera ruch danego gracza od odpowiadającego mu klienta poprzez
     * wysłanie wiadomości TURA, a następnie odebranie wiadomości zwrotnej
     * i sparsowanie jej w ruch.
     *
     * @param gracz Gracz, którego ruch ma zostać pobrany.
     * @return Ruch, który został odebrany od klienta.
     */

    public Ruch pobierzRuchKlienta(Gracz gracz) {
        Optional<Klient> klient = this.klienci.stream().filter(k -> k.getId() == gracz.getId()).findFirst();

        if(klient.isPresent()) {
            Klient k = klient.get();

            k.writeMessage("TURA");

            String message = k.readMessage();

            return this.messageIntoRuch(message);
        }

        return null;
    }

    /**
     * Rozsyła wszystkim klientom odpowiadające im numery id
     * poprzez iterację po liście klientów i wysłanie wiadomości
     * typu ID do każdego. Wiadomość dodatkowo przechowuje informację
     * o liczbie graczy w rozgrywce.
     */

    public void rozeslijId() {
        int liczbaKlientow = this.getLiczbaKlientow();

        // change to player number
        for(int i=0; i<liczbaKlientow; i++) {
            Klient klient = this.klienci.get(i);

            String message = String.format("ID %d %d", i, liczbaKlientow);

            klient.writeMessage(message);
        }
    }

    /**
     * Rozsyła do wszystkich graczy wiadomość typu RUCH
     * z informacją na temat ruchu odpowiedniego gracza.
     *
     * @param gracz Gracz, który wykonał ruch.
     * @param ruch Ruch, który został wykonany.
     */

    public void rozeslijRuch(Gracz gracz, Ruch ruch) {
        int numerGracza = gracz.getNumer();

        String message = this.ruchIntoMessage(numerGracza, ruch);

        this.rozeslijDoWszystkich(message);
    }

    /**
     * Rozsyła do wszystkich graczy wiadomość typu WYGRANA z
     * informacją o tym, który gracz doszedł do stanu wygrywającego,
     * a następnie rozłącza odpowiednieg klienta z serwerem.
     *
     * @param gracz Gracz, który doszedł do stanu wygrywającego.
     */

    public void rozeslijWygranaIRozlaczGracza(Gracz gracz) {
        int numerGracza = gracz.getNumer();
        int idGracza = gracz.getId();

        String message = String.format("WYGRANA %d", numerGracza);

        this.rozeslijDoWszystkich(message);
        this.rozlaczGracza(idGracza);
    }

    /**
     * Rozłącza połączenia wszystkich klientów z serwerem poprzez
     * iterację po wszsytkich klientach w liście i zamknięcie
     * połączenia każdego z nich.
     */

    public void rozlaczGraczy() {
        for(Klient klient : this.klienci) {
            try {
                klient.close();
            } catch (IOException e) {
                App.logger.log("Nie udało się zamknąć klienta.");
            }
        }

        klienci = new ArrayList<>();
    }

    /**
     * Przeprowadza głosowanie nad rozpoczęciem rozgrywki w momencie gdy
     * odpowiednia liczba klientów jest podpięta do serwera poprzez wysłanie
     * do wszystkich klientów wiadomości typu GLOSOWANIE i następnie zliczenie
     * liczby odpowiedzi głosujących za rozpoczęciem. Jeżeli liczba głosów za jest
     * równa liczbie klientów to jest zwracana prawda, w przeciwnym wypadku
     * fałsz. Przebieg głosowanie jest na bieżąco logowany.
     *
     * @return Czy zostało zagłosowane za rozpoczęciem rozgrywki.
     */

    private boolean przeprowadzGlosowanie() {
        int liczbaKlientow = getLiczbaKlientow();

        if(liczbaKlientow == 2 || liczbaKlientow == 3 || liczbaKlientow == 4 || liczbaKlientow == 6) {
            App.logger.log("Głosowanie nad rozpoczęciem gry.");

            this.rozeslijDoWszystkich("GLOSOWANIE");

            int liczbaCheciRozpoczecia = this.uzyskajWynikGlosowania();

            if(liczbaCheciRozpoczecia == liczbaKlientow) {
                App.logger.log("Wszyscy gracze się zgodzili. Gra rozpoczyna się.");

                return true;
            }

            App.logger.log("Nie wszyscy gracze się zgodzili. Oczekiwanie na kolejnych graczy.");

            return false;
        }

        return false;
    }

    /**
     * Uzyskuje wynik głosowania poprzez iterację po wszystkich klientach
     * i zliczenie ilu z nich wysłało wiadomość typu START.
     * Przebieg głosowania jest na bieżąco logowany/
     *
     * @return Liczba wiadomości typu START.
     */

    private int uzyskajWynikGlosowania() {
        int liczbaKlientow = getLiczbaKlientow();
        int liczbaCheciRozpoczecia = 0;

        for (int i=0; i<liczbaKlientow; i++) {
            Klient klient = this.klienci.get(i);

            String message = klient.readMessage();

            if(message.trim().equals("START")) {
                App.logger.log(String.format("Gracz %d zagłosował za rozpoczęciem gry.", i));

                liczbaCheciRozpoczecia++;
            } else {
                App.logger.log(String.format("Gracz %d zagłosował przeciwko rozpoczęciu gry.", i));
            }
        }

        return liczbaCheciRozpoczecia;
    }

    /**
     * Rozsyła wiadomość do wszystkich połączonych klientów.
     *
     * @param message Wiadomość do rozesłania.
     */

    private void rozeslijDoWszystkich(String message) {
        for(Klient klient : this.klienci) {
            klient.writeMessage(message);
        }
    }

    /**
     * Rozłącza połączenie gracza z serwerem poprzez zamknięcie
     * jego połączenia. Następnie usuwa odpowiedniego klienta
     * z listy połączonych klientów.
     *
     * @param idGracza Id gracza, którego odpowiadający klient ma być rozłączony z serwerem.
     */

    private void rozlaczGracza(int idGracza) {
        try {
            Optional<Klient> klient = klienci
                    .stream()
                    .filter(k -> k.getId() == idGracza)
                    .findFirst();

            if(klient.isPresent()) {
                klienci.removeIf(k -> k.getId() == idGracza);
                klient.get().close();
            }
        } catch (IOException e) {
            App.logger.log("Nie udało się zamknąć klienta.");
        }
    }

    /**
     * Parsuje ruch z wiadomości otrzymanej od klienta.
     * Jeżeli wiadomość jest błędna zwraca null, jeżeli wiadomość
     * jest typu SKIP zwraca ruch typu skip. W przeciwnym wypadku
     * zwraca ruch stworzony z koordynatów przekazanych w wiadomości.
     *
     * @param message Wiadomość do sparsowania.
     * @return Ruch sparsowany z wiadomości.
     */

    private Ruch messageIntoRuch(String message) {
        String[] tokeny = message.split(" ");

        if(tokeny.length == 0) {
            return null;
        }

        if(tokeny[0].equals("SKIP")) {
            return new Ruch();
        }

        if(tokeny.length != 5) {
            return null;
        }

        int[] koordynaty = new int[4];

        if(!tokeny[0].equals("RUCH")) {
            return null;
        }

        for(int i=1; i<5; i++) {
            try {
                int koordynat = Integer.parseInt(tokeny[i]);

                koordynaty[i - 1] = koordynat;
            } catch (NumberFormatException e) {
                return null;
            }
        }

        return new Ruch(koordynaty[0], koordynaty[1], koordynaty[2], koordynaty[3]);
    }

    /**
     * Tworzy wiadomość do wysłania z odpowiedniego ruchu poprzez
     * wyciągnięcie z ruchu odpowiednich koordynatów i utworzenie
     * z nich wiadomości.
     *
     * @param nrGracza Numer gracza, który wykonał ruch.
     * @param ruch Ruch wykonany przez gracza.
     * @return Wiadomość utworzona z ruchu.
     */

    private String ruchIntoMessage(int nrGracza, Ruch ruch) {
        int x1 = ruch.getzPola().getX();
        int y1 = ruch.getzPola().getY();
        int x2 = ruch.getDoPola().getX();
        int y2 = ruch.getDoPola().getY();

        return String.format("RUCH %d %d %d %d %d", nrGracza, x1, y1, x2, y2);
    }

    /**
     * Dodaje klienta do listy wszystkich połączonych klientów.
     *
     * @param klient Klient do dodania.
     */

    private void dodajKlienta(Klient klient) {
        this.klienci.add(klient);
    }

    /**
     * Zwraca liczbę połączonych klientów.
     *
     * @return Liczba połączonych klientów.
     */

    public int getLiczbaKlientow() {
        return this.klienci.size();
    }
}
