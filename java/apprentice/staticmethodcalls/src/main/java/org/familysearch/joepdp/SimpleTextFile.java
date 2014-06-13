package org.familysearch.joepdp;

import java.io.IOException;
import java.io.RandomAccessFile;

public class SimpleTextFile {
    public static RandomAccessFile open(String fileName) throws IOException {
        return new RandomAccessFile(fileName, "rw");
    }
    public static void close(RandomAccessFile randomAccessFile) throws IOException{
        randomAccessFile.close();
    }
    public static void append(RandomAccessFile randomAccessFile, String text) throws IOException{
        randomAccessFile.seek(randomAccessFile.length());
        randomAccessFile.writeBytes(text);
    }
}
