package org.familysearch.joepdp;

import java.io.IOException;

public class XMLFile implements Commentable {
    SimpleTextFile theFile;

    public XMLFile(String fileName) throws IOException {
        theFile = new SimpleTextFile(fileName);
    }

    public void close() throws IOException {
        theFile.close();
    }

    @Override
    public void appendComment(String comment) throws IOException {
        theFile.append("<!-- " + comment + " -->\n");
    }
}
