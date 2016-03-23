package ch.heigvd.res.client;

import ch.heigvd.res.utils.MessageWriter;

import java.io.*;
import java.net.Socket;

/**
 * @author Benjamin Schubert and Sathiya Kirushnapillai
 */
public class TCPClient {
    public static void main(String... args) {
        if(args.length != 2) {
            System.err.println("[Usage] runclient host port");
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);

        try (Socket socket = new Socket(host, port)){
            play(socket);
        } catch (IOException e) {
            System.err.println("An error occurred while speaking to the server, exiting");
            System.exit(1);
        }
    }

    private static void play(Socket socket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new BufferedInputStream(socket.getInputStream())));
        MessageWriter out = new MessageWriter(new OutputStreamWriter(new BufferedOutputStream(socket.getOutputStream())));

        while(true) {
            System.out.println(in.readLine());
            String next = in.readLine();
            if(null != next) {
                System.out.print(next);
            } else {
                return;
            }
            out.write(System.console().readLine());
        }
    }
}
