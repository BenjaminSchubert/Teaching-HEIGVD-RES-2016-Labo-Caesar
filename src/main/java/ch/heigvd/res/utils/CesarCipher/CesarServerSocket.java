package ch.heigvd.res.utils.CesarCipher;


import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * A ServerSocket implementation using the Cesar Cipher as
 * secure protocol around TCP
 *
 * @author Benjamin Schubert and Sathiya Kirushnapillai
 */
public class CesarServerSocket extends ServerSocket {
    public CesarServerSocket(int port, int backlog, InetAddress addr) throws IOException {
        super(port, backlog, addr);
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
