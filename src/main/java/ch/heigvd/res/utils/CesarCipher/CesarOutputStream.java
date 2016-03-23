package ch.heigvd.res.utils.CesarCipher;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * An outputstream implementing the Cesar Cipher protocol
 *
 * @author  Benjamin Schubert and Sathiya Kirushnapillai
 */
public class CesarOutputStream extends FilterOutputStream {
    private final byte offset;

    /**
     * @param out: outpustream to wrap.
     * @param offset: offset to use in the Cesar Cipher
     */
    public CesarOutputStream(OutputStream out, byte offset) {
        super(out);
        this.offset = offset;
    }

    @Override
    public void write(int b) throws IOException {
        super.write(b + offset);
    }

    @Override
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        for(int i = off; i < len + off; i++) {
            b[i] += 256;
        }
        super.write(b, off, len);
    }
}
