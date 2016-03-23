package ch.heigvd.res;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Benjamin Schubert and Sathiya Kirushnapillai
 */
public class TCPServer {
    public static void main(String... s) {
        try {
            ServerSocket server = new ServerSocket(8016);

            //noinspection InfiniteLoopStatement
            while(true) {
                Socket sSocket = server.accept();
                new Worker(sSocket).start();
            }
        } catch (IOException e) {
            System.err.println("Could not bind to socket, exiting");
            System.exit(1);
        }
    }
}
