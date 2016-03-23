package ch.heigvd.res.utils;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Benjamin Schubert on 3/23/16.
 */
public class CesarOutputStream extends FilterOutputStream {
    private final byte offset;

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
