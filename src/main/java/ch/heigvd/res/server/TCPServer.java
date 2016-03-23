package ch.heigvd.res.server;

import ch.heigvd.res.utils.CesarServerSocket;

import java.io.IOException;
import java.net.*;

/**
 * @author Benjamin Schubert and Sathiya Kirushnapillai
 */
public class TCPServer {
    public static void main(String... args) {
        if(args.length != 2) {
            System.err.println("[Usage] runserver host port");
            System.exit(2);
        }

        InetAddress addr = null;
        try {
            addr = InetAddress.getByName(args[0]);
        } catch (UnknownHostException e) {
            System.err.println("Unkown interface");
            System.exit(2);
        }

        int port = Integer.parseInt(args[1]);

        try (ServerSocket server = new CesarServerSocket(port, 50, addr)){

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
