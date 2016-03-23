package ch.heigvd.res.utils;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * @author Benjamin Schubert and Sathiya Kirushnapillai
 */
public class CesarServerSocket extends ServerSocket {
    public CesarServerSocket(int port) throws IOException {
        super(port);
    }

    public Socket accept() throws IOException {
        if(this.isClosed()) {
            throw new SocketException("Socket is closed");
        } else if(!this.isBound()) {
            throw new SocketException("Socket is not bound yet");
        } else {
            Socket s = new CesarSocket();
            this.implAccept(s);
            return s;
        }
    }
}
