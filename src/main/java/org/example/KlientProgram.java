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

            boolean graRozpoczeta = false;
            int idGracza = -1;
            MyFrame myFrame = null;

            while(true) {
                String message = reader.readLine();
                System.out.println(message);

                String[] messageTokens = message.split(" ");

                if(messageTokens[0].equals("ID")){
                    idGracza = Integer.parseInt(messageTokens[1]);
                    myFrame = new MyFrame(idGracza,Integer.parseInt(messageTokens[2]));
                    graRozpoczeta = true;
                }

                if(messageTokens[0].equals("RUCH")) {
                    int x1 = Integer.parseInt(messageTokens[2]);
                    int y1 = Integer.parseInt(messageTokens[3]);
                    int x2 = Integer.parseInt(messageTokens[4]);
                    int y2 = Integer.parseInt(messageTokens[5]);

                    if(myFrame != null) {
                        myFrame.przestawPionkaNaPlanszy(x1, y1, x2, y2);
                    }
                }

                if(messageTokens[0].equals("RUCH") || messageTokens[0].equals("ID") || messageTokens[0].equals("SKIP")) {
                    continue;
                }

                if(messageTokens[0].equals("WYGRANA")) {
                    if(Integer.parseInt(messageTokens[1]) == idGracza) {
                        if(myFrame != null) {
                            myFrame.wygrana();
                        }
                    }
                }

                if(!graRozpoczeta) {
                    String response = inputScanner.nextLine();
                    writer.println(response);
                }
            }
        }
    }
}
