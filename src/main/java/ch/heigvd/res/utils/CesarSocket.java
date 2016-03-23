package ch.heigvd.res.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author Benjamin Schubert and Sathiya Kirushnapillai
 */
public class CesarSocket extends Socket {
    private InputStream in;
    private OutputStream out;

    public CesarSocket() {
        super();
    }

    public CesarSocket(String host, int port) throws IOException {
        super(host, port);
        setReceiveBufferSize(256);
        setSendBufferSize(256);
    }

    public InputStream getInputStream() throws IOException {
        if (in == null) {
            in = new CesarInputStream(super.getInputStream(), (byte) 12);
        }
        return in;
    }

    public OutputStream getOutputStream() throws IOException {
        if (out == null) {
            out = new CesarOutputStream(super.getOutputStream(), (byte) 12);
        }
        return out;
    }


}
