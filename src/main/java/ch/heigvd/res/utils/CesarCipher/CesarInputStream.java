package ch.heigvd.res.utils.CesarCipher;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * An inputstream implementing the Cesar Cipher protocol
 *
 * @author  Benjamin Schubert and Sathiya Kirushnapillai
 */
public class CesarInputStream extends FilterInputStream {
    private final byte offset;

    /**
     * @param int: intputstream to wrap.
     * @param offset: offset to use in the Cesar Cipher
     */
    protected CesarInputStream(InputStream in, byte offset) {
        super(in);
        this.offset = offset;
    }

    @Override
    public int read() throws IOException {
        return (super.read() - offset) % 256;
    }

    @Override
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int retVal = super.read(b, off, len);
        for(int i = 0; i < retVal; i++) {
            b[i] = (byte) (b[i] - offset);
        }
        return retVal;
    }
}
