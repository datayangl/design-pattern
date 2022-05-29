package design.pattern.structure.decorator;

import java.io.IOException;

public class FilterInputStream {
    protected volatile InputStream in;

    public FilterInputStream(InputStream in) {
        this.in = in;
    }

    public int read() throws IOException {
        return in.read();
    }
}
