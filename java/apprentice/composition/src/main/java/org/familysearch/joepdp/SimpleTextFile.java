package org.familysearch.joepdp;

import java.io.IOException;
import java.io.RandomAccessFile;

public class SimpleTextFile {
    RandomAccessFile randomAccessFile;
    public SimpleTextFile(String fileName) throws IOException {
        randomAccessFile = new RandomAccessFile(fileName, "rw");
    }
    public void close() throws IOException{
        randomAccessFile.close();
    }
    public void append(String text) throws IOException{
        randomAccessFile.seek(randomAccessFile.length());
        randomAccessFile.writeBytes(text);
    }
}
