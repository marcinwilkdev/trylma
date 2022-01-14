package org.example;

import java.io.*;
import java.net.Socket;

/**
 * Mechaniki służące do komunikacji z odpowiednim klientem.
 */

public class Klient implements Closeable {
    private final int id;
    private final Socket socket;
    private BufferedReader bufReader;
    private PrintWriter printWriter;

    /**
     * Przypisuje id i socket, a następnie tworzy instancje BufferedReader oraz
     * PrintWriter z socketa klienta służące do komunikacji z nim.
     *
     * @param id Numer id klienta odpowiadający odpowiedniemu graczowi.
     * @param socket Socket danego klienta służacy do komunikacji z nim.
     */

    public Klient(int id, Socket socket) {
        this.id = id;
        this.socket = socket;

        try {
            this.bufReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            System.out.println("Nie udało się stworzyć klienta.");
        }
    }

    /**
     * Odczytuje wiadomość przychodzącą od danego klienta poprzez
     * odczytanie linii tekstu z BufferedReader.
     *
     * @return Wiadomość przychodząca od klienta.
     */

    public String readMessage() {
        try {
            return this.bufReader.readLine();
        } catch (IOException e) {
            System.out.println("Nie udało się odczytać wiadomości.");
        }

        return null;
    }

    /**
     * Wysyła wiadomość do klienta poprzez wpisanie linii tekstu
     * do PrintWriter.
     *
     * @param message Wiadomość do wysłania do klienta.
     */

    public void writeMessage(String message) {
        this.printWriter.println(message);
        this.printWriter.flush();
    }

    /**
     * Zamyka socket przy zamknięci klienta.
     *
     * @throws IOException Nie udało się zamknąć socketu.
     */

    @Override
    public void close() throws IOException {
        this.socket.close();
    }

    /**
     * Zwraca numer id klienta.
     *
     * @return Numer id klienta.
     */

    public int getId() {
        return id;
    }
}
