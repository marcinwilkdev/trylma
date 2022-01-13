package org.example;

import java.io.*;
import java.net.Socket;

public class Klient implements Closeable {
    private final Socket socket;
    private BufferedReader bufReader;
    private PrintWriter printWriter;

    public Klient(Socket socket) {
        this.socket = socket;

        try {
            this.bufReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            System.out.println("Nie udało się stworzyć klienta.");
        }
    }

    public String readMessage() {
        try {
            return this.bufReader.readLine();
        } catch (IOException e) {
            System.out.println("Nie udało się odczytać wiadomości.");
        }

        return null;
    }

    public void writeMessage(String message) {
        this.printWriter.println(message);
        this.printWriter.flush();
    }

    @Override
    public void close() throws IOException {
        this.socket.close();
    }
}
