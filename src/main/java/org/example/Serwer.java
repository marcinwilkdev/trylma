package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Serwer {
    private final int port;
    private final List<Klient> klienci;

    public Serwer(int port) {
        this.port = port;
        this.klienci = new ArrayList<>();
    }

    public void czekajNaGraczy() {
        try(ServerSocket s = new ServerSocket(this.port)) {
            while(this.getLiczbaKlientow() < 6) {
                Socket socket = s.accept();
                this.dodajKlienta(new Klient(socket));

                App.logger.log(String.format("Gracz się połączył z portu %d", socket.getLocalPort()));

                int liczbaKlientow = this.getLiczbaKlientow();

                if (this.przeprowadzGlosowanie(liczbaKlientow)) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean przeprowadzGlosowanie(int liczbaKlientow) {
        if(liczbaKlientow == 2 || liczbaKlientow == 3 || liczbaKlientow == 4 || liczbaKlientow == 6) {
            App.logger.log("Głosowanie nad rozpoczęciem gry.");

            this.rozeslijDoWszystkich("GLOSOWANIE");

            int liczbaCheciRozpoczecia = this.uzyskajWynikGlosowania(liczbaKlientow);

            if(liczbaCheciRozpoczecia == liczbaKlientow) {
                App.logger.log("Wszyscy gracze się zgodzili. Gra rozpoczyna się.");

                return true;
            }

            App.logger.log("Nie wszyscy gracze się zgodzili. Oczekiwanie na kolejnych graczy.");

            return false;
        }

        return false;
    }

    private int uzyskajWynikGlosowania(int liczbaKlientow) {
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

    public Ruch pobierzRuchKlienta(int nrKlienta) {
        Klient klient = this.klienci.get(nrKlienta);

        klient.writeMessage("TURA");

        String message = klient.readMessage();

        return this.messageIntoRuch(message);
    }

    public void rozeslijId() {
        int liczbaKlientow = this.getLiczbaKlientow();

        for(int i=0; i<liczbaKlientow; i++) {
            Klient klient = this.klienci.get(i);

            String message = String.format("ID %d %d", i, liczbaKlientow);

            klient.writeMessage(message);
        }
    }

    public void rozeslijRuch(int nrGracza, Ruch ruch) {
        String message = this.ruchIntoMessage(nrGracza, ruch);

        this.rozeslijDoWszystkich(message);
    }

    public void rozeslijWygrana(int nrGracza) {
        String message = String.format("WYGRANA %d", nrGracza);

        this.rozeslijDoWszystkich(message);
        this.rozlaczGracza(nrGracza);
    }

    private void rozeslijDoWszystkich(String message) {
        for(Klient klient : this.klienci) {
            if(klient != null) {
                klient.writeMessage(message);
            }
        }
    }

    public void rozlaczGracza(int idGracza) {
        try {
            this.klienci.get(idGracza).close();
            this.klienci.set(idGracza, null);
        } catch (IOException e) {
            App.logger.log("Nie udało się zamknąć klienta.");
        }
    }

    public void rozlaczGraczy() {
        for(Klient klient : this.klienci) {
            try {
                if(klient != null) {
                    klient.close();
                }
            } catch (IOException e) {
                App.logger.log("Nie udało się zamknąć klienta.");
            }
        }
    }

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

    private String ruchIntoMessage(int nrGracza, Ruch ruch) {
        int x1 = ruch.getzPola().getX();
        int y1 = ruch.getzPola().getY();
        int x2 = ruch.getDoPola().getX();
        int y2 = ruch.getDoPola().getY();

        return String.format("RUCH %d %d %d %d %d", nrGracza, x1, y1, x2, y2);
    }

    private void dodajKlienta(Klient klient) {
        this.klienci.add(klient);
    }

    public int getLiczbaKlientow() {
        return this.klienci.size();
    }
}
