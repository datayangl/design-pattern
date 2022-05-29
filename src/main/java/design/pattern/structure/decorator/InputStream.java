package design.pattern.structure.decorator;

import java.io.IOException;

public abstract class InputStream {
    public int read() throws IOException{
        return -1;
    }

    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    public int read(byte[] b, int off, int len) throws IOException {
        //...
        return -1;
    }

    public long skip(long n) throws IOException {
        //...
        return 0L;
    }

    public int available() throws IOException {
        return 0;
    }

    public void close() throws IOException {}

    public synchronized void mark(int readLimit) {}

    public synchronized void reset() throws IOException {
        throw new IOException("mark/reset not supported");
    }

    public boolean markSupported() {
        return false;
    }
}
