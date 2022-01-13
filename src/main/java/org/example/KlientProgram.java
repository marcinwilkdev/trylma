package org.example;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class KlientProgram {

    public static BufferedReader globalReader = null;
    public static PrintWriter globalWriter = null;

    public static void main(String[] args) throws IOException {
        try(Socket s = new Socket("localhost", 8189);
            InputStream inStream = s.getInputStream(); OutputStream outStream = s.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, StandardCharsets.UTF_8));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outStream), true);
            Scanner inputScanner = new Scanner(System.in)) {

            globalReader = reader;
            globalWriter = writer;

            while(true) {
                String message = reader.readLine();
                System.out.println(message);

                String[] messageTokens = message.split(" ");

                if(messageTokens[0].equals("ID")){
                    MyFrame frame = new MyFrame(Integer.parseInt(messageTokens[1]),Integer.parseInt(messageTokens[2]));
                }

                if(messageTokens[0].equals("RUCH") || messageTokens[0].equals("ID") || messageTokens[0].equals("SKIP")) {
                    continue;
                }

                if(messageTokens[0].equals("WYGRANA")) {
                    break;
                }

                String response = inputScanner.nextLine();
                writer.println(response);
            }
        }
    }
}
