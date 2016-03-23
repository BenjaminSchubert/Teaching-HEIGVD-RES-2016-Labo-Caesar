package res;

import java.io.*;
import java.net.Socket;

/**
 * @author Benjamin Schubert and Sathiya Kirushnapillai
 */
public class TCPClient {
    public static void main(String... args) {
        if(args.length != 2) {
            System.err.println("[Usage] java -jar {program} host port");
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);

        try {
            play(host, port);
        } catch (IOException e) {
            System.err.println("An error occurred while speaking to the server, exiting");
            System.exit(1);
        }
    }

    private static void play(String host, int port) throws IOException {
        Socket socket = new Socket(host, port);
        BufferedReader in = new BufferedReader(new InputStreamReader(new BufferedInputStream(socket.getInputStream())));
        BufferedWriter out = new MessageWriter(new OutputStreamWriter(new BufferedOutputStream(socket.getOutputStream())));

        while(true) {
            System.out.println(in.readLine());
            out.write(System.console().readLine());
        }
    }
}
