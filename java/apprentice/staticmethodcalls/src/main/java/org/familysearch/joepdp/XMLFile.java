package org.familysearch.joepdp;

import java.io.IOException;
import java.io.RandomAccessFile;

public class XMLFile implements Commentable {
    RandomAccessFile theFile;

    public XMLFile(String fileName) throws IOException {
        theFile = SimpleTextFile.open(fileName);
    }

    public void close() throws IOException {
        SimpleTextFile.close(theFile);
    }

    @Override
    public void appendComment(String comment) throws IOException {
        SimpleTextFile.append(theFile, "<!-- " + comment + " -->\n");
    }
}
