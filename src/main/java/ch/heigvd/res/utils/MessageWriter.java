package ch.heigvd.res.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * BufferedWriter to autoflush messages and add EOL
 * 
 * @author Benjamin Schubert and Sathiya Kirushnapillai
 */
public class MessageWriter extends BufferedWriter {
    public MessageWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str) throws IOException {
        super.write(str + "\n");
        flush();
    }
}
