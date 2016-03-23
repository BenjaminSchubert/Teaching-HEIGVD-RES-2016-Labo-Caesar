package ch.heigvd.res.server;


import ch.heigvd.res.utils.MessageWriter;

import java.io.*;
import java.net.Socket;
import java.util.Random;

/**
 * @author Benjamin Schubert and Sathiya Kirushnapillai
 */
public class Worker extends Thread {
    private Socket socket;
    private static final int max_value = 64;

    public Worker(Socket sSocket) {
        this.socket = sSocket;
    }

    public void play(BufferedReader in, Writer out) throws IOException {
        int guessMe = new Random().nextInt(max_value);
        int value;

        out.write("The value is between 0 and " + max_value + ", yours to guess !");

        while(true) {
            out.write("Enter your number :");
            try {
                value = Integer.parseInt(in.readLine());
            } catch (NumberFormatException e) {
                out.write("Not a number, try again.");
                continue;
            }

            if(value > guessMe) {
                out.write("The number is smaller than that !");
            } else if(value < guessMe) {
                out.write("The number is bigger than that !");
            } else {
                out.write("Congrats ! You just found it, see you later.");
                return;
            }
        }
    }

    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        new BufferedInputStream(socket.getInputStream())));
                BufferedWriter out = new MessageWriter(new OutputStreamWriter(
                        new BufferedOutputStream(socket.getOutputStream())))
        ){
            play(in, out);
        }
        catch (IOException e) {
            System.err.println("Could not open streams, exiting");
        }

        try {
            socket.close();
        } catch (IOException e1) {
            // there is nothing we can do
        }
    }
}
