package ch.heigvd.res;


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

    public void run() {
        BufferedReader in;
        BufferedWriter out;
        try {
            in = new BufferedReader(new InputStreamReader(new BufferedInputStream(socket.getInputStream())));
            out = new MessageWriter(new OutputStreamWriter(new BufferedOutputStream(socket.getOutputStream())));
        } catch (IOException e) {
            System.err.println("Could not open streams, exiting");
            return;
        }

        try {
            int guessMe = new Random().nextInt(max_value);
            int value;

            out.write("The value is between 0 and " + max_value + ", yours to guess !\n");

            while(true) {
                try {
                    value = Integer.parseInt(in.readLine());
                } catch (NumberFormatException e) {
                    out.write("Not a number, try again.\n");
                    continue;
                }

                if(value > guessMe) {
                    out.write("The number is smaller than that !\n");
                } else if(value < guessMe) {
                    out.write("The number is bigger than that !\n");
                } else {
                    out.write("Congrats ! You just found it, see you later\n");
                    socket.close();
                    return;
                }
            }
        } catch (IOException e) {
            // Nothing to do, just the connection that went off
        }
    }
}
