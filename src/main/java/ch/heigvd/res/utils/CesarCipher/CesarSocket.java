package ch.heigvd.res.utils.CesarCipher;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

/**
 * Secure Socket communication implementation using CesarSocket
 *
 * @author Benjamin Schubert and Sathiya Kirushnapillai
 */
public class CesarSocket extends Socket {
    private InputStream in;
    private OutputStream out;
    private int offsetIn;
    private int offsetOut;
    private boolean handshakeDone = false;

    public CesarSocket() {
        super();
    }

    public CesarSocket(String host, int port) throws IOException {
        super(host, port);
        setReceiveBufferSize(256);
        setSendBufferSize(256);
    }

    /**
     * Connection handshake with the client and the server
     *
     * @throws IOException
     */
    private void handshake() throws IOException {
        handshakeDone = true;
        offsetOut = new Random().nextInt();
        super.getOutputStream().write(offsetOut);

        offsetIn = super.getInputStream().read();
    }

    public InputStream getInputStream() throws IOException {
        if (in == null) {
            if(!handshakeDone) {
                handshake();
            }
            in = new CesarInputStream(super.getInputStream(), (byte) offsetIn);
        }
        return in;
    }

    public OutputStream getOutputStream() throws IOException {
        if (out == null) {
            if(!handshakeDone) {
                handshake();
            }
            out = new CesarOutputStream(super.getOutputStream(), (byte) offsetOut);
        }
        return out;
    }


}
