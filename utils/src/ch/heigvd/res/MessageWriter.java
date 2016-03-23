package res;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author Benjamin Schubert
 */
public class MessageWriter extends BufferedWriter {
    public MessageWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str) throws IOException {
        super.write(str);
        flush();
    }
}
